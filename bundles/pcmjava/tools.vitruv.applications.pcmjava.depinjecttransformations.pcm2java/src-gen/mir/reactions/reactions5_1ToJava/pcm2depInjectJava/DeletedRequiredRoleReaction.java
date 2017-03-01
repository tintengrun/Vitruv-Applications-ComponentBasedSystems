package mir.reactions.reactions5_1ToJava.pcm2depInjectJava;

import mir.routines.pcm2depInjectJava.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.palladiosimulator.pcm.core.entity.InterfaceRequiringEntity;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.compound.RemoveAndDeleteNonRoot;
import tools.vitruv.framework.change.echange.feature.reference.RemoveEReference;
import tools.vitruv.framework.userinteraction.UserInteracting;

@SuppressWarnings("all")
class DeletedRequiredRoleReaction extends AbstractReactionRealization {
  public DeletedRequiredRoleReaction(final UserInteracting userInteracting) {
    super(userInteracting);
  }
  
  public void executeReaction(final EChange change) {
    RemoveEReference<InterfaceRequiringEntity, OperationRequiredRole> typedChange = ((RemoveAndDeleteNonRoot<InterfaceRequiringEntity, OperationRequiredRole>)change).getRemoveChange();
    InterfaceRequiringEntity affectedEObject = typedChange.getAffectedEObject();
    EReference affectedFeature = typedChange.getAffectedFeature();
    OperationRequiredRole oldValue = typedChange.getOldValue();
    mir.routines.pcm2depInjectJava.RoutinesFacade routinesFacade = new mir.routines.pcm2depInjectJava.RoutinesFacade(this.executionState, this);
    mir.reactions.reactions5_1ToJava.pcm2depInjectJava.DeletedRequiredRoleReaction.ActionUserExecution userExecution = new mir.reactions.reactions5_1ToJava.pcm2depInjectJava.DeletedRequiredRoleReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, oldValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return RemoveAndDeleteNonRoot.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    RemoveEReference<InterfaceRequiringEntity, OperationRequiredRole> relevantChange = ((RemoveAndDeleteNonRoot<InterfaceRequiringEntity, OperationRequiredRole>)change).getRemoveChange();
    if (!(relevantChange.getAffectedEObject() instanceof InterfaceRequiringEntity)) {
    	return false;
    }
    if (!relevantChange.getAffectedFeature().getName().equals("requiredRoles_InterfaceRequiringEntity")) {
    	return false;
    }
    if (!(relevantChange.getOldValue() instanceof OperationRequiredRole)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof RemoveAndDeleteNonRoot)) {
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
    
    public void callRoutine1(final InterfaceRequiringEntity affectedEObject, final EReference affectedFeature, final OperationRequiredRole oldValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.removeRequiredRole(oldValue, ((RepositoryComponent) affectedEObject));
    }
  }
}
