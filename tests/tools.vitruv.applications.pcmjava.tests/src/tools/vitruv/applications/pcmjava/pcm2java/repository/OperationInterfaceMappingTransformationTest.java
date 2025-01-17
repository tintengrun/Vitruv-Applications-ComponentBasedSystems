package tools.vitruv.applications.pcmjava.pcm2java.repository;

import org.emftext.language.java.classifiers.Interface;
import org.junit.jupiter.api.Test;

import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.Repository;

import tools.vitruv.applications.pcmjava.pcm2java.Pcm2JavaTestUtils;
import tools.vitruv.applications.pcmjava.pcm2java.Pcm2JavaTransformationTest;

public class OperationInterfaceMappingTransformationTest extends Pcm2JavaTransformationTest {

	@Test
	public void testAddInterface() throws Throwable {
		final Repository repo = this.createAndSyncRepository(Pcm2JavaTestUtils.REPOSITORY_NAME);

		final OperationInterface opInterface = this.addInterfaceToReposiotryAndSync(repo,
				Pcm2JavaTestUtils.INTERFACE_NAME);

		this.assertOperationInterfaceCorrespondences(opInterface);
	}

	@Test
	public void testRenameInterface() throws Throwable {
		final Repository repo = this.createAndSyncRepository(Pcm2JavaTestUtils.REPOSITORY_NAME);
		OperationInterface opInterface = this.addInterfaceToReposiotryAndSync(repo, Pcm2JavaTestUtils.INTERFACE_NAME);

		opInterface = this.renameInterfaceAndSync(opInterface);

		this.assertOperationInterfaceCorrespondences(opInterface);
	}

	@SuppressWarnings("unchecked")
	private void assertOperationInterfaceCorrespondences(final OperationInterface opInterface) throws Throwable {
		this.assertCorrespondencesAndCompareNames(opInterface, 1,
				new java.lang.Class[] { Interface.class },
				new String[] { opInterface.getEntityName() });
	}
}
