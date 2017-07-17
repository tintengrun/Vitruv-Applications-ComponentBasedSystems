package mir.routines.java2PcmClassifier;

import java.io.IOException;
import mir.routines.java2PcmClassifier.RoutinesFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.Repository;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class AddDataTypeInRepositoryRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private AddDataTypeInRepositoryRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final Repository pcmRepository, final CompositeDataType pcmDataType) {
      return pcmRepository;
    }
    
    public void update0Element(final Repository pcmRepository, final CompositeDataType pcmDataType) {
      EList<DataType> _dataTypes__Repository = pcmRepository.getDataTypes__Repository();
      _dataTypes__Repository.add(pcmDataType);
    }
  }
  
  public AddDataTypeInRepositoryRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final Repository pcmRepository, final CompositeDataType pcmDataType) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.java2PcmClassifier.AddDataTypeInRepositoryRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.java2PcmClassifier.RoutinesFacade(getExecutionState(), this);
    this.pcmRepository = pcmRepository;this.pcmDataType = pcmDataType;
  }
  
  private Repository pcmRepository;
  
  private CompositeDataType pcmDataType;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine AddDataTypeInRepositoryRoutine with input:");
    getLogger().debug("   Repository: " + this.pcmRepository);
    getLogger().debug("   CompositeDataType: " + this.pcmDataType);
    
    // val updatedElement userExecution.getElement1(pcmRepository, pcmDataType);
    userExecution.update0Element(pcmRepository, pcmDataType);
    
    postprocessElements();
  }
}
