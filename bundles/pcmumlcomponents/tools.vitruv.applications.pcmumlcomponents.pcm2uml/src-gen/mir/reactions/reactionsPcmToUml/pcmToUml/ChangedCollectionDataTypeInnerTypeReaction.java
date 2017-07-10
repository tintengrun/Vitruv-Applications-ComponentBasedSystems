package mir.reactions.reactionsPcmToUml.pcmToUml;

import mir.routines.pcmToUml.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.DataType;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.reference.ReplaceSingleValuedEReference;

@SuppressWarnings("all")
class ChangedCollectionDataTypeInnerTypeReaction extends AbstractReactionRealization {
  public void executeReaction(final EChange change) {
    ReplaceSingleValuedEReference<CollectionDataType, DataType> typedChange = (ReplaceSingleValuedEReference<CollectionDataType, DataType>)change;
    CollectionDataType affectedEObject = typedChange.getAffectedEObject();
    EReference affectedFeature = typedChange.getAffectedFeature();
    DataType oldValue = typedChange.getOldValue();
    DataType newValue = typedChange.getNewValue();
    mir.routines.pcmToUml.RoutinesFacade routinesFacade = new mir.routines.pcmToUml.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsPcmToUml.pcmToUml.ChangedCollectionDataTypeInnerTypeReaction.ActionUserExecution userExecution = new mir.reactions.reactionsPcmToUml.pcmToUml.ChangedCollectionDataTypeInnerTypeReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, oldValue, newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return ReplaceSingleValuedEReference.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    ReplaceSingleValuedEReference<CollectionDataType, DataType> relevantChange = (ReplaceSingleValuedEReference<CollectionDataType, DataType>)change;
    if (!(relevantChange.getAffectedEObject() instanceof CollectionDataType)) {
    	return false;
    }
    if (!relevantChange.getAffectedFeature().getName().equals("innerType_CollectionDataType")) {
    	return false;
    }
    if (relevantChange.isFromNonDefaultValue() && !(relevantChange.getOldValue() instanceof DataType)) {
    	return false;
    }
    if (relevantChange.isToNonDefaultValue() && !(relevantChange.getNewValue() instanceof DataType)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof ReplaceSingleValuedEReference)) {
    	return false;
    }
    getLogger().debug("Passed change type check of reaction " + this.getClass().getName());
    if (!checkChangeProperties(change)) {
    	return false;
    }
    getLogger().debug("Passed change properties check of reaction " + this.getClass().getName());
    getLogger().debug("Passed complete precondition check of reaction " + this.getClass().getName());
    return true;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final CollectionDataType affectedEObject, final EReference affectedFeature, final DataType oldValue, final DataType newValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.changeCollectionDataTypeInnerType(affectedEObject, newValue);
    }
  }
}
