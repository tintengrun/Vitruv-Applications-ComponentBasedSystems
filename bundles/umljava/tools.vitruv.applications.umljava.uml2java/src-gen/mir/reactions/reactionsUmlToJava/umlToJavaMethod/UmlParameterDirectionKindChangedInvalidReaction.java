package mir.reactions.reactionsUmlToJava.umlToJavaMethod;

import mir.routines.umlToJavaMethod.RoutinesFacade;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.xtext.xbase.lib.Extension;
import tools.vitruv.applications.umljava.uml2java.UmlToJavaHelper;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.attribute.ReplaceSingleValuedEAttribute;

@SuppressWarnings("all")
class UmlParameterDirectionKindChangedInvalidReaction extends AbstractReactionRealization {
  public void executeReaction(final EChange change) {
    ReplaceSingleValuedEAttribute<org.eclipse.uml2.uml.Parameter, org.eclipse.uml2.uml.ParameterDirectionKind> typedChange = (ReplaceSingleValuedEAttribute<org.eclipse.uml2.uml.Parameter, org.eclipse.uml2.uml.ParameterDirectionKind>)change;
    org.eclipse.uml2.uml.Parameter affectedEObject = typedChange.getAffectedEObject();
    EAttribute affectedFeature = typedChange.getAffectedFeature();
    org.eclipse.uml2.uml.ParameterDirectionKind oldValue = typedChange.getOldValue();
    org.eclipse.uml2.uml.ParameterDirectionKind newValue = typedChange.getNewValue();
    mir.routines.umlToJavaMethod.RoutinesFacade routinesFacade = new mir.routines.umlToJavaMethod.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsUmlToJava.umlToJavaMethod.UmlParameterDirectionKindChangedInvalidReaction.ActionUserExecution userExecution = new mir.reactions.reactionsUmlToJava.umlToJavaMethod.UmlParameterDirectionKindChangedInvalidReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, oldValue, newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return ReplaceSingleValuedEAttribute.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    ReplaceSingleValuedEAttribute<org.eclipse.uml2.uml.Parameter, org.eclipse.uml2.uml.ParameterDirectionKind> relevantChange = (ReplaceSingleValuedEAttribute<org.eclipse.uml2.uml.Parameter, org.eclipse.uml2.uml.ParameterDirectionKind>)change;
    if (!(relevantChange.getAffectedEObject() instanceof org.eclipse.uml2.uml.Parameter)) {
    	return false;
    }
    if (!relevantChange.getAffectedFeature().getName().equals("direction")) {
    	return false;
    }
    if (relevantChange.isFromNonDefaultValue() && !(relevantChange.getOldValue() instanceof org.eclipse.uml2.uml.ParameterDirectionKind)) {
    	return false;
    }
    if (relevantChange.isToNonDefaultValue() && !(relevantChange.getNewValue() instanceof org.eclipse.uml2.uml.ParameterDirectionKind)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof ReplaceSingleValuedEAttribute)) {
    	return false;
    }
    getLogger().trace("Passed change type check of reaction " + this.getClass().getName());
    if (!checkChangeProperties(change)) {
    	return false;
    }
    getLogger().trace("Passed change properties check of reaction " + this.getClass().getName());
    ReplaceSingleValuedEAttribute<org.eclipse.uml2.uml.Parameter, org.eclipse.uml2.uml.ParameterDirectionKind> typedChange = (ReplaceSingleValuedEAttribute<org.eclipse.uml2.uml.Parameter, org.eclipse.uml2.uml.ParameterDirectionKind>)change;
    org.eclipse.uml2.uml.Parameter affectedEObject = typedChange.getAffectedEObject();
    EAttribute affectedFeature = typedChange.getAffectedFeature();
    org.eclipse.uml2.uml.ParameterDirectionKind oldValue = typedChange.getOldValue();
    org.eclipse.uml2.uml.ParameterDirectionKind newValue = typedChange.getNewValue();
    if (!checkUserDefinedPrecondition(affectedEObject, affectedFeature, oldValue, newValue)) {
    	return false;
    }
    getLogger().trace("Passed complete precondition check of reaction " + this.getClass().getName());
    return true;
  }
  
  private boolean checkUserDefinedPrecondition(final Parameter affectedEObject, final EAttribute affectedFeature, final ParameterDirectionKind oldValue, final ParameterDirectionKind newValue) {
    return ((newValue != ParameterDirectionKind.RETURN_LITERAL) && (newValue != ParameterDirectionKind.IN_LITERAL));
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final Parameter affectedEObject, final EAttribute affectedFeature, final ParameterDirectionKind oldValue, final ParameterDirectionKind newValue, @Extension final RoutinesFacade _routinesFacade) {
      UmlToJavaHelper.showMessage(this.userInteracting, (("The ParameterDirectionKind " + newValue) + " is not supported"));
    }
  }
}
