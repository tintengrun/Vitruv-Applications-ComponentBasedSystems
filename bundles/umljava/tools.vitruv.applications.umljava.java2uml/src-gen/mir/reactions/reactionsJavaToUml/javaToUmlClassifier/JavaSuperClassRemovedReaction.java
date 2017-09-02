package mir.reactions.reactionsJavaToUml.javaToUmlClassifier;

import mir.routines.javaToUmlClassifier.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.emftext.language.java.types.TypeReference;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.feature.reference.ReplaceSingleValuedEReference;

@SuppressWarnings("all")
class JavaSuperClassRemovedReaction extends AbstractReactionRealization {
  public void executeReaction(final EChange change) {
    ReplaceSingleValuedEReference<org.emftext.language.java.classifiers.Class, org.emftext.language.java.types.TypeReference> typedChange = (ReplaceSingleValuedEReference<org.emftext.language.java.classifiers.Class, org.emftext.language.java.types.TypeReference>)change;
    org.emftext.language.java.classifiers.Class affectedEObject = typedChange.getAffectedEObject();
    EReference affectedFeature = typedChange.getAffectedFeature();
    org.emftext.language.java.types.TypeReference oldValue = typedChange.getOldValue();
    org.emftext.language.java.types.TypeReference newValue = typedChange.getNewValue();
    mir.routines.javaToUmlClassifier.RoutinesFacade routinesFacade = new mir.routines.javaToUmlClassifier.RoutinesFacade(this.executionState, this);
    mir.reactions.reactionsJavaToUml.javaToUmlClassifier.JavaSuperClassRemovedReaction.ActionUserExecution userExecution = new mir.reactions.reactionsJavaToUml.javaToUmlClassifier.JavaSuperClassRemovedReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, oldValue, newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return ReplaceSingleValuedEReference.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    ReplaceSingleValuedEReference<org.emftext.language.java.classifiers.Class, org.emftext.language.java.types.TypeReference> relevantChange = (ReplaceSingleValuedEReference<org.emftext.language.java.classifiers.Class, org.emftext.language.java.types.TypeReference>)change;
    if (!(relevantChange.getAffectedEObject() instanceof org.emftext.language.java.classifiers.Class)) {
    	return false;
    }
    if (!relevantChange.getAffectedFeature().getName().equals("extends")) {
    	return false;
    }
    if (relevantChange.isFromNonDefaultValue() && !(relevantChange.getOldValue() instanceof org.emftext.language.java.types.TypeReference)) {
    	return false;
    }
    if (relevantChange.isToNonDefaultValue() && !(relevantChange.getNewValue() instanceof org.emftext.language.java.types.TypeReference)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof ReplaceSingleValuedEReference)) {
    	return false;
    }
    getLogger().trace("Passed change type check of reaction " + this.getClass().getName());
    if (!checkChangeProperties(change)) {
    	return false;
    }
    getLogger().trace("Passed change properties check of reaction " + this.getClass().getName());
    ReplaceSingleValuedEReference<org.emftext.language.java.classifiers.Class, org.emftext.language.java.types.TypeReference> typedChange = (ReplaceSingleValuedEReference<org.emftext.language.java.classifiers.Class, org.emftext.language.java.types.TypeReference>)change;
    org.emftext.language.java.classifiers.Class affectedEObject = typedChange.getAffectedEObject();
    EReference affectedFeature = typedChange.getAffectedFeature();
    org.emftext.language.java.types.TypeReference oldValue = typedChange.getOldValue();
    org.emftext.language.java.types.TypeReference newValue = typedChange.getNewValue();
    if (!checkUserDefinedPrecondition(affectedEObject, affectedFeature, oldValue, newValue)) {
    	return false;
    }
    getLogger().trace("Passed complete precondition check of reaction " + this.getClass().getName());
    return true;
  }
  
  private boolean checkUserDefinedPrecondition(final org.emftext.language.java.classifiers.Class affectedEObject, final EReference affectedFeature, final TypeReference oldValue, final TypeReference newValue) {
    return (newValue == null);
  }
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public void callRoutine1(final org.emftext.language.java.classifiers.Class affectedEObject, final EReference affectedFeature, final TypeReference oldValue, final TypeReference newValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.clearUmlSuperClassifiers(affectedEObject);
    }
  }
}
