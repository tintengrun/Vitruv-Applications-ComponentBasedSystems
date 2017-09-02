package mir.reactions.reactionsJavaToPcm.ejbjava2pcm;

import mir.routines.ejbjava2pcm.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.parameters.Parameter;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;

@SuppressWarnings("all")
class CreateParameterInInterfaceMethodReaction extends AbstractReactionRealization {
  public void executeReaction(final EChange change) {
    InsertEReference<org.emftext.language.java.members.InterfaceMethod, org.emftext.language.java.parameters.Parameter> typedChange = (InsertEReference<org.emftext.language.java.members.InterfaceMethod, org.emftext.language.java.parameters.Parameter>)change;
    org.emftext.language.java.members.InterfaceMethod affectedEObject = typedChange.getAffectedEObject();
    EReference affectedFeature = typedChange.getAffectedFeature();
    org.emftext.language.java.parameters.Parameter newValue = typedChange.getNewValue();
    mir.routines.ejbjava2pcm.RoutinesFacade routinesFacade = new mir.routines.ejbjava2pcm.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsJavaToPcm.ejbjava2pcm.CreateParameterInInterfaceMethodReaction.ActionUserExecution userExecution = new mir.reactions.reactionsJavaToPcm.ejbjava2pcm.CreateParameterInInterfaceMethodReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return InsertEReference.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    InsertEReference<org.emftext.language.java.members.InterfaceMethod, org.emftext.language.java.parameters.Parameter> relevantChange = (InsertEReference<org.emftext.language.java.members.InterfaceMethod, org.emftext.language.java.parameters.Parameter>)change;
    if (!(relevantChange.getAffectedEObject() instanceof org.emftext.language.java.members.InterfaceMethod)) {
    	return false;
    }
    if (!relevantChange.getAffectedFeature().getName().equals("parameters")) {
    	return false;
    }
    if (!(relevantChange.getNewValue() instanceof org.emftext.language.java.parameters.Parameter)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof InsertEReference)) {
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
    
    public void callRoutine1(final InterfaceMethod affectedEObject, final EReference affectedFeature, final Parameter newValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.createdParameterInInterfaceMethod(affectedEObject, newValue);
    }
  }
}
