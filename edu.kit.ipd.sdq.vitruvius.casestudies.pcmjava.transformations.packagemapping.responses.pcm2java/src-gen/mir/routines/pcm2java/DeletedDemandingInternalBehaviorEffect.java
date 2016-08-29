package mir.routines.pcm2java;

import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.AbstractEffectRealization;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.ResponseExecutionState;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.structure.CallHierarchyHaving;
import edu.kit.ipd.sdq.vitruvius.framework.change.echange.feature.reference.RemoveEReference;

import java.io.IOException;
import mir.routines.pcm2java.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.emftext.language.java.members.ClassMethod;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.seff.ResourceDemandingInternalBehaviour;

@SuppressWarnings("all")
public class DeletedDemandingInternalBehaviorEffect extends AbstractEffectRealization {
  public DeletedDemandingInternalBehaviorEffect(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy, final RemoveEReference<BasicComponent, ResourceDemandingInternalBehaviour> change) {
    super(responseExecutionState, calledBy);
    				this.change = change;
  }
  
  private RemoveEReference<BasicComponent, ResourceDemandingInternalBehaviour> change;
  
  private EObject getElement0(final RemoveEReference<BasicComponent, ResourceDemandingInternalBehaviour> change, final ClassMethod javaMethod) {
    return javaMethod;
  }
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine DeletedDemandingInternalBehaviorEffect with input:");
    getLogger().debug("   RemoveEReference: " + this.change);
    
    ClassMethod javaMethod = getCorrespondingElement(
    	getCorrepondenceSourceJavaMethod(change), // correspondence source supplier
    	ClassMethod.class,
    	(ClassMethod _element) -> true, // correspondence precondition checker
    	null);
    if (javaMethod == null) {
    	return;
    }
    initializeRetrieveElementState(javaMethod);
    deleteObject(getElement0(change, javaMethod));
    
    preprocessElementStates();
    postprocessElementStates();
  }
  
  private EObject getCorrepondenceSourceJavaMethod(final RemoveEReference<BasicComponent, ResourceDemandingInternalBehaviour> change) {
    ResourceDemandingInternalBehaviour _oldValue = change.getOldValue();
    return _oldValue;
  }
  
  private static class EffectUserExecution extends AbstractEffectRealization.UserExecution {
    @Extension
    private RoutinesFacade effectFacade;
    
    public EffectUserExecution(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
      super(responseExecutionState);
      this.effectFacade = new mir.routines.pcm2java.RoutinesFacade(responseExecutionState, calledBy);
    }
  }
}
