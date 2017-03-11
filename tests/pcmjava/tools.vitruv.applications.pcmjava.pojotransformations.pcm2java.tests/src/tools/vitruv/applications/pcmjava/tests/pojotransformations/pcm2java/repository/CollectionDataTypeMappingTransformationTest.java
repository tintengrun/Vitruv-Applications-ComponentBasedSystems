package tools.vitruv.applications.pcmjava.tests.pojotransformations.pcm2java.repository;

import org.junit.Test;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.PrimitiveTypeEnum;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

import tools.vitruv.applications.pcmjava.tests.pojotransformations.pcm2java.PCM2JaMoPPTransformationTest;
import tools.vitruv.applications.pcmjava.tests.util.PCM2JaMoPPTestUtils;

public class CollectionDataTypeMappingTransformationTest extends PCM2JaMoPPTransformationTest {

    @Test
    public void testAddCollectionDataTypeWithoutInnerType() throws Throwable {
        final Repository repo = this.createAndSyncRepository(this.resourceSet, PCM2JaMoPPTestUtils.REPOSITORY_NAME);

        this.testUserInteractor.addNextSelections(0);
        final CollectionDataType collectionDataType = this.addCollectionDatatypeAndSync(repo,
                PCM2JaMoPPTestUtils.COLLECTION_DATA_TYPE_NAME, null);

        this.assertDataTypeCorrespondence(collectionDataType);
    }

    @Test
    public void testAddCollectionDataTypeWithPrimitiveTypeStringAsInnerType() throws Throwable {
        final PrimitiveTypeEnum pte = PrimitiveTypeEnum.STRING;
        this.testAddCollectionDataTypeWithPrimitiveInnerType(pte);
    }

    @Test
    public void testAddCollectionDataTypeWithPrimitiveTypeIntAsInnerType() throws Throwable {
        this.testAddCollectionDataTypeWithPrimitiveInnerType(PrimitiveTypeEnum.INT);
    }

    @Test
    public void testAddCollectionDataTypeWithComplexInnerType() throws Throwable {
        final Repository repo = this.createAndSyncRepository(this.resourceSet, PCM2JaMoPPTestUtils.REPOSITORY_NAME);
        final CompositeDataType compositeDataType = this.createAndSyncCompositeDataType(repo);

        this.testUserInteractor.addNextSelections(0);
        final CollectionDataType collectionDataType = this.addCollectionDatatypeAndSync(repo,
                PCM2JaMoPPTestUtils.COLLECTION_DATA_TYPE_NAME, compositeDataType);

        this.assertDataTypeCorrespondence(collectionDataType);
    }

    protected void testAddCollectionDataTypeWithPrimitiveInnerType(final PrimitiveTypeEnum pte) throws Throwable {
        final Repository repo = this.createAndSyncRepository(this.resourceSet, PCM2JaMoPPTestUtils.REPOSITORY_NAME);
        final PrimitiveDataType primitiveType = RepositoryFactory.eINSTANCE.createPrimitiveDataType();
        primitiveType.setType(pte);
        primitiveType.setRepository__DataType(repo);

        this.testUserInteractor.addNextSelections(0);
        final CollectionDataType collectionDataType = this.addCollectionDatatypeAndSync(repo,
                PCM2JaMoPPTestUtils.COLLECTION_DATA_TYPE_NAME, primitiveType);

        this.assertDataTypeCorrespondence(collectionDataType);
    }
    
}