package mir.effects.pcm2java;

import com.google.common.base.Objects;
import edu.kit.ipd.sdq.vitruvius.casestudies.pcmjava.responses.pcm2java.Pcm2JavaHelper;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.AbstractEffectRealization;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.CorrespondenceFailHandlerFactory;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.ResponseExecutionState;
import edu.kit.ipd.sdq.vitruvius.dsls.response.runtime.structure.CallHierarchyHaving;
import edu.kit.ipd.sdq.vitruvius.framework.contracts.datatypes.Blackboard;
import edu.kit.ipd.sdq.vitruvius.framework.contracts.datatypes.TransformationResult;
import edu.kit.ipd.sdq.vitruvius.framework.contracts.interfaces.UserInteracting;
import java.io.IOException;
import mir.effects.pcm2java.EffectsFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.impl.MembersFactoryImpl;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

@SuppressWarnings("all")
public class CreateSEFFEffect extends AbstractEffectRealization {
  public CreateSEFFEffect(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
    super(responseExecutionState, calledBy);
  }
  
  private ServiceEffectSpecification seff;
  
  private boolean isSeffSet;
  
  public void setSeff(final ServiceEffectSpecification seff) {
    this.seff = seff;
    this.isSeffSet = true;
  }
  
  public boolean allParametersSet() {
    return isSeffSet;
  }
  
  private EObject getCorrepondenceSourceInterfaceMethod(final ServiceEffectSpecification seff) {
    Signature _describedService__SEFF = seff.getDescribedService__SEFF();
    return _describedService__SEFF;
  }
  
  private EObject getCorrepondenceSourceComponentClass(final ServiceEffectSpecification seff) {
    BasicComponent _basicComponent_ServiceEffectSpecification = seff.getBasicComponent_ServiceEffectSpecification();
    return _basicComponent_ServiceEffectSpecification;
  }
  
  protected void executeEffect() throws IOException {
    getLogger().debug("Called effect CreateSEFFEffect with input:");
    getLogger().debug("   ServiceEffectSpecification: " + this.seff);
    
    org.emftext.language.java.classifiers.Class componentClass = initializeRetrieveElementState(
    	() -> getCorrepondenceSourceComponentClass(seff), // correspondence source supplier
    	(org.emftext.language.java.classifiers.Class _element) -> true, // correspondence precondition checker
    	() -> null, // tag supplier
    	org.emftext.language.java.classifiers.Class.class,
    	CorrespondenceFailHandlerFactory.createCustomUserDialogHandler(true, "Could not find a corresponding class for the SEFF's component. No corresponding method for the SEFF created."));
    if (isAborted()) return;
    InterfaceMethod interfaceMethod = initializeRetrieveElementState(
    	() -> getCorrepondenceSourceInterfaceMethod(seff), // correspondence source supplier
    	(InterfaceMethod _element) -> true, // correspondence precondition checker
    	() -> null, // tag supplier
    	InterfaceMethod.class,
    	CorrespondenceFailHandlerFactory.createDoNothingHandler(true));
    if (isAborted()) return;
    ClassMethod classMethod = initializeCreateElementState(
    	() -> getCorrepondenceSourceClassMethod(seff), // correspondence source supplier
    	() -> MembersFactoryImpl.eINSTANCE.createClassMethod(), // element creation supplier
    	() -> null, // tag supplier
    	ClassMethod.class);
    if (isAborted()) return;
    preProcessElements();
    new mir.effects.pcm2java.CreateSEFFEffect.EffectUserExecution(getExecutionState(), this).executeUserOperations(
    	seff, componentClass, interfaceMethod, classMethod);
    postProcessElements();
  }
  
  private EObject getCorrepondenceSourceClassMethod(final ServiceEffectSpecification seff) {
    return seff;
  }
  
  private static class EffectUserExecution {
    private Blackboard blackboard;
    
    private UserInteracting userInteracting;
    
    private TransformationResult transformationResult;
    
    @Extension
    private EffectsFacade effectFacade;
    
    public EffectUserExecution(final ResponseExecutionState responseExecutionState, final CallHierarchyHaving calledBy) {
      this.blackboard = responseExecutionState.getBlackboard();
      this.userInteracting = responseExecutionState.getUserInteracting();
      this.transformationResult = responseExecutionState.getTransformationResult();
      this.effectFacade = new EffectsFacade(responseExecutionState, calledBy);
    }
    
    private void executeUserOperations(final ServiceEffectSpecification seff, final org.emftext.language.java.classifiers.Class componentClass, final InterfaceMethod interfaceMethod, final ClassMethod classMethod) {
      final Signature signature = seff.getDescribedService__SEFF();
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(signature, null));
      if (!_notEquals) {
        _and = false;
      } else {
        _and = (signature instanceof OperationSignature);
      }
      final boolean sigIsOpSig = _and;
      if ((!sigIsOpSig)) {
        return;
      }
      Pcm2JavaHelper.initializeClassMethod(classMethod, interfaceMethod, true);
      ClassMethod correspondingClassMethod = Pcm2JavaHelper.findMethodInClass(componentClass, classMethod);
      boolean _equals = Objects.equal(null, correspondingClassMethod);
      if (_equals) {
        EList<Member> _members = componentClass.getMembers();
        _members.add(classMethod);
        correspondingClassMethod = classMethod;
      } else {
        String _name = interfaceMethod.getName();
        correspondingClassMethod.setName(_name);
      }
    }
  }
}
