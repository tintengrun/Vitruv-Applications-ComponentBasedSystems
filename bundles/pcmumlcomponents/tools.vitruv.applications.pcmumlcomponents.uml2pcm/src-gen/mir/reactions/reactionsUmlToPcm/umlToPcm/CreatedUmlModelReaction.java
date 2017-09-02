package mir.reactions.reactionsUmlToPcm.umlToPcm;

import mir.routines.umlToPcm.RoutinesFacade;
import org.eclipse.uml2.uml.Model;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.compound.CreateAndInsertRoot;
import tools.vitruv.framework.change.echange.root.InsertRootEObject;

@SuppressWarnings("all")
class CreatedUmlModelReaction extends AbstractReactionRealization {
  public void executeReaction(final EChange change) {
    InsertRootEObject<org.eclipse.uml2.uml.Model> typedChange = ((CreateAndInsertRoot<org.eclipse.uml2.uml.Model>)change).getInsertChange();
    org.eclipse.uml2.uml.Model newValue = typedChange.getNewValue();
    mir.routines.umlToPcm.RoutinesFacade routinesFacade = new mir.routines.umlToPcm.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsUmlToPcm.umlToPcm.CreatedUmlModelReaction.ActionUserExecution userExecution = new mir.reactions.reactionsUmlToPcm.umlToPcm.CreatedUmlModelReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return CreateAndInsertRoot.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    InsertRootEObject<org.eclipse.uml2.uml.Model> relevantChange = ((CreateAndInsertRoot<org.eclipse.uml2.uml.Model>)change).getInsertChange();
    if (!(relevantChange.getNewValue() instanceof org.eclipse.uml2.uml.Model)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof CreateAndInsertRoot)) {
    	return false;
    }
    getLogger().trace("Passed change type check of reaction " + this.getClass().getName());
    if (!checkChangeProperties(change)) {
    	return false;
    }
    getLogger().trace("Passed change properties check of reaction " + this.getClass().getName());
    getLogger().trace("Passed complete precondition check of reaction " + this.getClass().getName());
    return true;
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final Model newValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.createPcmRepository(newValue);
    }
  }
}
