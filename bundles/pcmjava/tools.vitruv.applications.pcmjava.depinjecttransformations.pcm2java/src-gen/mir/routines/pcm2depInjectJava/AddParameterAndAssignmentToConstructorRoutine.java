package mir.routines.pcm2depInjectJava;

import java.io.IOException;
import mir.routines.pcm2depInjectJava.RoutinesFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.impl.ParametersFactoryImpl;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import tools.vitruv.applications.pcmjava.util.pcm2java.Pcm2JavaUtils;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class AddParameterAndAssignmentToConstructorRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private AddParameterAndAssignmentToConstructorRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final NamedElement parameterCorrespondenceSource, final Constructor constructor, final NamespaceClassifierReference typeReference, final Field fieldToBeAssigned, final String parameterName, final OrdinaryParameter newParameter) {
      return newParameter;
    }
    
    public void update0Element(final NamedElement parameterCorrespondenceSource, final Constructor constructor, final NamespaceClassifierReference typeReference, final Field fieldToBeAssigned, final String parameterName, final OrdinaryParameter newParameter) {
      EList<Parameter> _parameters = constructor.getParameters();
      _parameters.add(newParameter);
      final Statement asssignment = Pcm2JavaUtils.createAssignmentFromParameterToField(fieldToBeAssigned, newParameter);
      EList<Statement> _statements = constructor.getStatements();
      _statements.add(asssignment);
    }
    
    public void updateNewParameterElement(final NamedElement parameterCorrespondenceSource, final Constructor constructor, final NamespaceClassifierReference typeReference, final Field fieldToBeAssigned, final String parameterName, final OrdinaryParameter newParameter) {
      newParameter.setName(parameterName);
      NamespaceClassifierReference _copy = EcoreUtil.<NamespaceClassifierReference>copy(typeReference);
      newParameter.setTypeReference(_copy);
    }
    
    public EObject getElement2(final NamedElement parameterCorrespondenceSource, final Constructor constructor, final NamespaceClassifierReference typeReference, final Field fieldToBeAssigned, final String parameterName, final OrdinaryParameter newParameter) {
      return parameterCorrespondenceSource;
    }
    
    public EObject getElement3(final NamedElement parameterCorrespondenceSource, final Constructor constructor, final NamespaceClassifierReference typeReference, final Field fieldToBeAssigned, final String parameterName, final OrdinaryParameter newParameter) {
      return constructor;
    }
  }
  
  public AddParameterAndAssignmentToConstructorRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final NamedElement parameterCorrespondenceSource, final Constructor constructor, final NamespaceClassifierReference typeReference, final Field fieldToBeAssigned, final String parameterName) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.pcm2depInjectJava.AddParameterAndAssignmentToConstructorRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.pcm2depInjectJava.RoutinesFacade(getExecutionState(), this);
    this.parameterCorrespondenceSource = parameterCorrespondenceSource;this.constructor = constructor;this.typeReference = typeReference;this.fieldToBeAssigned = fieldToBeAssigned;this.parameterName = parameterName;
  }
  
  private NamedElement parameterCorrespondenceSource;
  
  private Constructor constructor;
  
  private NamespaceClassifierReference typeReference;
  
  private Field fieldToBeAssigned;
  
  private String parameterName;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine AddParameterAndAssignmentToConstructorRoutine with input:");
    getLogger().debug("   NamedElement: " + this.parameterCorrespondenceSource);
    getLogger().debug("   Constructor: " + this.constructor);
    getLogger().debug("   NamespaceClassifierReference: " + this.typeReference);
    getLogger().debug("   Field: " + this.fieldToBeAssigned);
    getLogger().debug("   String: " + this.parameterName);
    
    OrdinaryParameter newParameter = ParametersFactoryImpl.eINSTANCE.createOrdinaryParameter();
    notifyObjectCreated(newParameter);
    userExecution.updateNewParameterElement(parameterCorrespondenceSource, constructor, typeReference, fieldToBeAssigned, parameterName, newParameter);
    
    addCorrespondenceBetween(userExecution.getElement1(parameterCorrespondenceSource, constructor, typeReference, fieldToBeAssigned, parameterName, newParameter), userExecution.getElement2(parameterCorrespondenceSource, constructor, typeReference, fieldToBeAssigned, parameterName, newParameter), "");
    
    // val updatedElement userExecution.getElement3(parameterCorrespondenceSource, constructor, typeReference, fieldToBeAssigned, parameterName, newParameter);
    userExecution.update0Element(parameterCorrespondenceSource, constructor, typeReference, fieldToBeAssigned, parameterName, newParameter);
    
    postprocessElements();
  }
}
