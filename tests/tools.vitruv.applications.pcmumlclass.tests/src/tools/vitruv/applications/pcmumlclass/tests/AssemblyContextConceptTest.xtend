package tools.vitruv.applications.pcmumlclass.tests

import org.eclipse.uml2.uml.Property
import org.palladiosimulator.pcm.core.composition.AssemblyContext
import org.palladiosimulator.pcm.core.composition.CompositionFactory
import org.palladiosimulator.pcm.repository.Repository
import tools.vitruv.applications.pcmumlclass.TagLiterals
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertTrue
import java.nio.file.Path

/**
 * This test class tests the reactions and routines that are supposed to synchronize a pcm::AssemblyContext 
 * in a pcm::ComposedProvidingRequiringEntity (CPRE) with a uml::Property in an uml::Class (the implementation class to the CPRE). 
 * <br><br>
 * Related files: PcmAssemblyContext.reactions, UmlAssemblyContextProperty.reactions
 */
class AssemblyContextConceptTest extends LegacyPcmUmlClassApplicationTest {

	static val PROPERTY_NAME = "testAssemblyContextField"

	def void checkAssemblyContextConcept(
		AssemblyContext pcmAssemblyContext,
		Property umlAssemblyContextProperty
	) {
		assertNotNull(pcmAssemblyContext)
		assertNotNull(umlAssemblyContextProperty)
		assertTrue(
			corresponds(pcmAssemblyContext, umlAssemblyContextProperty, TagLiterals.ASSEMBLY_CONTEXT__PROPERTY))
		assertTrue(
			corresponds(pcmAssemblyContext.parentStructure__AssemblyContext, umlAssemblyContextProperty.owner,
				TagLiterals.IPRE__IMPLEMENTATION))
		assertTrue(
			corresponds( pcmAssemblyContext.encapsulatedComponent__AssemblyContext, umlAssemblyContextProperty.type,
				TagLiterals.IPRE__IMPLEMENTATION))
		assertTrue(pcmAssemblyContext.entityName == umlAssemblyContextProperty.name)
	}

	def protected checkAssemblyContextConcept(AssemblyContext pcmAssemblyContext) {
		val umlAssemblyContextProperty = helper.getModifiableCorr(pcmAssemblyContext, Property,
			TagLiterals.ASSEMBLY_CONTEXT__PROPERTY)
		checkAssemblyContextConcept(pcmAssemblyContext, umlAssemblyContextProperty)
	}

	def protected checkAssemblyContextConcept(Property umlAssemblyContextProperty) {
		val pcmAssemblyContext = helper.getModifiableCorr(umlAssemblyContextProperty, AssemblyContext,
			TagLiterals.ASSEMBLY_CONTEXT__PROPERTY)
		checkAssemblyContextConcept(pcmAssemblyContext, umlAssemblyContextProperty)
	}

	/**
	 * Initialize a pcm::Repository with two CompositeComponents and synchronize them with their uml-counterparts.
	 */
	def private Repository createRepository_2Components() {

		val pcmRepository = helper.createRepository
		helper.createComponent(pcmRepository)
		helper.createComponent_2(pcmRepository)

		userInteraction.addNextTextInput(LegacyPcmUmlClassApplicationTestHelper.UML_MODEL_FILE)
		resourceAt(Path.of(LegacyPcmUmlClassApplicationTestHelper.PCM_MODEL_FILE)).startRecordingChanges => [
			contents += pcmRepository
		]
		propagate
		assertModelExists(LegacyPcmUmlClassApplicationTestHelper.PCM_MODEL_FILE)
		assertModelExists(LegacyPcmUmlClassApplicationTestHelper.UML_MODEL_FILE)

		return pcmRepository.clearResourcesAndReloadRoot
	}

	@Test
	def void testCreateAssemblyContextConcept_PCM() {
		var pcmRepository = createRepository_2Components()
		var pcmComponent = helper.getPcmComponent(pcmRepository)

		var pcmAssemblyContext = CompositionFactory.eINSTANCE.createAssemblyContext
		pcmAssemblyContext.entityName = PROPERTY_NAME
		// TODO setting the same component as container and encapsulated doesn't seem to trigger change event
		pcmAssemblyContext.encapsulatedComponent__AssemblyContext = helper.getPcmComponent_2(pcmRepository)
		pcmComponent.assemblyContexts__ComposedStructure += pcmAssemblyContext

		propagate
		pcmRepository = pcmRepository.clearResourcesAndReloadRoot

		pcmAssemblyContext = helper.getPcmComponent(pcmRepository).assemblyContexts__ComposedStructure.head
		assertNotNull(pcmAssemblyContext)
		checkAssemblyContextConcept(pcmAssemblyContext)
	}

	@Test
	def void testCreateAssemblyContextConcept_UML() {
		var pcmRepository = createRepository_2Components()
		var umlComponent = helper.getUmlComponentImpl(pcmRepository)
		startRecordingChanges(umlComponent)

		var umlAssemblyContextProperty = umlComponent.createOwnedAttribute(PROPERTY_NAME,
			helper.getUmlComponentImpl_2(pcmRepository))

		propagate
		umlAssemblyContextProperty.clearResourcesAndReloadRoot
		pcmRepository = pcmRepository.clearResourcesAndReloadRoot

		umlAssemblyContextProperty = helper.getUmlComponentImpl(pcmRepository).ownedAttributes.findFirst [
			it.name == PROPERTY_NAME
		]
		assertNotNull(umlAssemblyContextProperty)
		checkAssemblyContextConcept(umlAssemblyContextProperty)
	}

}
