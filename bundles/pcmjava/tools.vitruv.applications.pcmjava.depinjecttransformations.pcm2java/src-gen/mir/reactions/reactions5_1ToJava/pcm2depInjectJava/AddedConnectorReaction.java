package mir.reactions.reactions5_1ToJava.pcm2depInjectJava;

import mir.routines.pcm2depInjectJava.RoutinesFacade;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Extension;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractReactionRealization;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.change.echange.EChange;
import tools.vitruv.framework.change.echange.compound.CreateAndInsertNonRoot;
import tools.vitruv.framework.change.echange.feature.reference.InsertEReference;
import tools.vitruv.framework.userinteraction.UserInteracting;

@SuppressWarnings("all")
class AddedConnectorReaction extends AbstractReactionRealization {
  public AddedConnectorReaction(final UserInteracting userInteracting) {
    super(userInteracting);
  }
  
  public void executeReaction(final EChange change) {
    InsertEReference<ComposedStructure, AssemblyConnector> typedChange = ((CreateAndInsertNonRoot<ComposedStructure, AssemblyConnector>)change).getInsertChange();
    ComposedStructure affectedEObject = typedChange.getAffectedEObject();
    EReference affectedFeature = typedChange.getAffectedFeature();
    AssemblyConnector newValue = typedChange.getNewValue();
    mir.routines.pcm2depInjectJava.RoutinesFacade routinesFacade = new mir.routines.pcm2depInjectJava.RoutinesFacade(this.executionState, this);
    mir.reactions.reactions5_1ToJava.pcm2depInjectJava.AddedConnectorReaction.ActionUserExecution userExecution = new mir.reactions.reactions5_1ToJava.pcm2depInjectJava.AddedConnectorReaction.ActionUserExecution(this.executionState, this);
    userExecution.callRoutine1(affectedEObject, affectedFeature, newValue, routinesFacade);
  }
  
  public static Class<? extends EChange> getExpectedChangeType() {
    return CreateAndInsertNonRoot.class;
  }
  
  private boolean checkChangeProperties(final EChange change) {
    InsertEReference<ComposedStructure, AssemblyConnector> relevantChange = ((CreateAndInsertNonRoot<ComposedStructure, AssemblyConnector>)change).getInsertChange();
    if (!(relevantChange.getAffectedEObject() instanceof ComposedStructure)) {
    	return false;
    }
    if (!relevantChange.getAffectedFeature().getName().equals("connectors__ComposedStructure")) {
    	return false;
    }
    if (!(relevantChange.getNewValue() instanceof AssemblyConnector)) {
    	return false;
    }
    return true;
  }
  
  public boolean checkPrecondition(final EChange change) {
    if (!(change instanceof CreateAndInsertNonRoot)) {
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
    
    public void callRoutine1(final ComposedStructure affectedEObject, final EReference affectedFeature, final AssemblyConnector newValue, @Extension final RoutinesFacade _routinesFacade) {
      _routinesFacade.addConnector(newValue);
    }
  }
}
