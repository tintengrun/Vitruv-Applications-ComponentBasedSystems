package mir.routines.pcm2java;

import com.google.common.collect.Iterables;
import java.io.IOException;
import mir.routines.pcm2java.RoutinesFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.impl.ImportsFactoryImpl;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.impl.MembersFactoryImpl;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.palladiosimulator.pcm.core.entity.InterfaceRequiringEntity;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import tools.vitruv.applications.pcmjava.util.pcm2java.Pcm2JavaHelper;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class AddRequiredRoleRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private AddRequiredRoleRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getCorrepondenceSourceRequiredInterface(final OperationRequiredRole requiredRole) {
      OperationInterface _requiredInterface__OperationRequiredRole = requiredRole.getRequiredInterface__OperationRequiredRole();
      return _requiredInterface__OperationRequiredRole;
    }
    
    public EObject getElement1(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport) {
      return requiredInterfaceImport;
    }
    
    public void update0Element(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport, final Field requiredInterfaceField) {
      EList<Member> _members = javaClass.getMembers();
      _members.add(requiredInterfaceField);
      EList<Member> _members_1 = javaClass.getMembers();
      Iterable<Constructor> _filter = Iterables.<Constructor>filter(_members_1, Constructor.class);
      boolean _isNullOrEmpty = IterableExtensions.isNullOrEmpty(_filter);
      if (_isNullOrEmpty) {
        Pcm2JavaHelper.addConstructorToClass(javaClass);
      }
    }
    
    public EObject getCorrepondenceSourceJavaClass(final OperationRequiredRole requiredRole, final Interface requiredInterface) {
      InterfaceRequiringEntity _requiringEntity_RequiredRole = requiredRole.getRequiringEntity_RequiredRole();
      return _requiringEntity_RequiredRole;
    }
    
    public EObject getElement4(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport, final Field requiredInterfaceField) {
      return requiredRole;
    }
    
    public EObject getElement5(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport, final Field requiredInterfaceField) {
      return javaClass;
    }
    
    public EObject getElement2(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport) {
      return requiredRole;
    }
    
    public EObject getElement3(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport, final Field requiredInterfaceField) {
      return requiredInterfaceField;
    }
    
    public void updateRequiredInterfaceFieldElement(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport, final Field requiredInterfaceField) {
      final NamespaceClassifierReference typeRef = Pcm2JavaHelper.createNamespaceClassifierReference(requiredInterface);
      final String requiredRoleName = requiredRole.getEntityName();
      NamespaceClassifierReference _copy = EcoreUtil.<NamespaceClassifierReference>copy(typeRef);
      Pcm2JavaHelper.createPrivateField(requiredInterfaceField, _copy, requiredRoleName);
    }
    
    public void updateRequiredInterfaceImportElement(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport) {
      Pcm2JavaHelper.addImportToCompilationUnitOfClassifier(requiredInterfaceImport, javaClass, requiredInterface);
    }
    
    public void callRoutine1(final OperationRequiredRole requiredRole, final Interface requiredInterface, final org.emftext.language.java.classifiers.Class javaClass, final ClassifierImport requiredInterfaceImport, final Field requiredInterfaceField, @Extension final RoutinesFacade _routinesFacade) {
      final NamespaceClassifierReference typeRef = Pcm2JavaHelper.createNamespaceClassifierReference(requiredInterface);
      final String requiredRoleName = requiredRole.getEntityName();
      EList<Member> _members = javaClass.getMembers();
      Iterable<Constructor> _filter = Iterables.<Constructor>filter(_members, Constructor.class);
      for (final Constructor ctor : _filter) {
        _routinesFacade.addParameterAndAssignmentToConstructor(requiredRole, ctor, typeRef, requiredInterfaceField, requiredRoleName);
      }
    }
  }
  
  public AddRequiredRoleRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final OperationRequiredRole requiredRole) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.pcm2java.AddRequiredRoleRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.pcm2java.RoutinesFacade(getExecutionState(), this);
    this.requiredRole = requiredRole;
  }
  
  private OperationRequiredRole requiredRole;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine AddRequiredRoleRoutine with input:");
    getLogger().debug("   OperationRequiredRole: " + this.requiredRole);
    
    Interface requiredInterface = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceRequiredInterface(requiredRole), // correspondence source supplier
    	Interface.class,
    	(Interface _element) -> true, // correspondence precondition checker
    	null);
    if (requiredInterface == null) {
    	return;
    }
    registerObjectUnderModification(requiredInterface);
    org.emftext.language.java.classifiers.Class javaClass = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceJavaClass(requiredRole, requiredInterface), // correspondence source supplier
    	org.emftext.language.java.classifiers.Class.class,
    	(org.emftext.language.java.classifiers.Class _element) -> true, // correspondence precondition checker
    	null);
    if (javaClass == null) {
    	return;
    }
    registerObjectUnderModification(javaClass);
    ClassifierImport requiredInterfaceImport = ImportsFactoryImpl.eINSTANCE.createClassifierImport();
    userExecution.updateRequiredInterfaceImportElement(requiredRole, requiredInterface, javaClass, requiredInterfaceImport);
    
    addCorrespondenceBetween(userExecution.getElement1(requiredRole, requiredInterface, javaClass, requiredInterfaceImport), userExecution.getElement2(requiredRole, requiredInterface, javaClass, requiredInterfaceImport), "");
    
    Field requiredInterfaceField = MembersFactoryImpl.eINSTANCE.createField();
    userExecution.updateRequiredInterfaceFieldElement(requiredRole, requiredInterface, javaClass, requiredInterfaceImport, requiredInterfaceField);
    
    addCorrespondenceBetween(userExecution.getElement3(requiredRole, requiredInterface, javaClass, requiredInterfaceImport, requiredInterfaceField), userExecution.getElement4(requiredRole, requiredInterface, javaClass, requiredInterfaceImport, requiredInterfaceField), "");
    
    // val updatedElement userExecution.getElement5(requiredRole, requiredInterface, javaClass, requiredInterfaceImport, requiredInterfaceField);
    userExecution.update0Element(requiredRole, requiredInterface, javaClass, requiredInterfaceImport, requiredInterfaceField);
    
    userExecution.callRoutine1(requiredRole, requiredInterface, javaClass, requiredInterfaceImport, requiredInterfaceField, actionsFacade);
    
    postprocessElements();
  }
}