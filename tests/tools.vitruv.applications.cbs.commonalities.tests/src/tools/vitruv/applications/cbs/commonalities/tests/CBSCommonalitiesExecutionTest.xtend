package tools.vitruv.applications.cbs.commonalities.tests

import org.junit.jupiter.api.BeforeEach
import tools.vitruv.applications.cbs.commonalities.tests.util.DomainModelsProvider
import tools.vitruv.testutils.LegacyVitruvApplicationTest
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.emf.ecore.resource.ResourceSet
import org.junit.jupiter.api.AfterEach
import tools.vitruv.applications.cbs.commonalities.tests.util.VitruvApplicationTestAdapter
import org.eclipse.emf.common.util.URI
import java.nio.file.Path
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import tools.vitruv.applications.cbs.commonalities.CbsCommonalitiesApplication
import tools.vitruv.change.propagation.ChangePropagationMode
import tools.vitruv.applications.util.temporary.java.JavaSetup
import org.junit.jupiter.api.^extension.ExtendWith
import tools.vitruv.testutils.RegisterMetamodelsInStandalone

@ExtendWith(RegisterMetamodelsInStandalone)
abstract class CBSCommonalitiesExecutionTest extends LegacyVitruvApplicationTest {	
	def <T> T getModels(DomainModelsProvider<T> modelsProvider) {
		return modelsProvider.getModels(vitruvApplicationTestAdapter)
	}
	
	@BeforeEach
	def setupChangePropagationMode() {
		virtualModel.changePropagationMode = ChangePropagationMode.TRANSITIVE_EXCEPT_LEAVES
	}
	
	@BeforeEach
	def protected setupJaMoPP() {
		JavaSetup.resetClasspathAndRegisterStandardLibrary()
		JavaSetup.prepareFactories()
	}
	
	override protected getChangePropagationSpecifications() {
		new CbsCommonalitiesApplication().changePropagationSpecifications
	}

	@Accessors(PROTECTED_GETTER)
	val VitruvApplicationTestAdapter vitruvApplicationTestAdapter = new VitruvApplicationTestAdapter() {
		/**
		 * Returns a resource from the runtime test project
		 */
		override getResourceAt(String modelPathInProject) {
			validationResourceSet.getResource(getUri(Path.of(modelPathInProject)), true);
		}

		/**
		 * Returns a test resource from the test specification project (this project) rather than the runtime project
		 */
		override getTestResource(String resourcePathInExecutingProject) {
			validationResourceSet.getResource(URI.createURI(resourcePathInExecutingProject), true)
		}
		
		override <T extends EObject> T at(Class<T> type, URI uri) {
			type.cast(validationResourceSet.getEObject(uri, true))
		}

		override createAndSynchronizeModel(String modelPathInProject, EObject rootElement) {
			val resource = resourceAt(Path.of(modelPathInProject)).startRecordingChanges => [
				contents += rootElement
			]
			propagate()
			// This is a necessary hack, because the transformations recreate elements, such that the resulting models are the same but new UUIDs are assigned.
			// Starting recording again reloads the UUIDs
			startRecordingChanges(resource)
		}
	}

	@Accessors(PROTECTED_GETTER)
	var ResourceSet validationResourceSet

	@BeforeEach
	def protected void setupResourceSets() {
		validationResourceSet = new ResourceSetImpl()
	}

	@AfterEach
	def protected void cleanupResourceSets() {
		validationResourceSet.resources.forEach[unload]
		validationResourceSet.resources.clear
		validationResourceSet = null
	}
}
