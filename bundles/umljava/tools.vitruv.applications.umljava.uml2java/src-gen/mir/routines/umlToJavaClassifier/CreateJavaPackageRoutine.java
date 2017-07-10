package mir.routines.umlToJavaClassifier;

import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.List;
import mir.routines.umlToJavaClassifier.RoutinesFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Namespace;
import org.emftext.language.java.containers.impl.ContainersFactoryImpl;
import tools.vitruv.applications.umljava.util.uml.UmlClassifierAndPackageUtil;
import tools.vitruv.domains.java.util.JavaPersistenceHelper;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class CreateJavaPackageRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private CreateJavaPackageRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final org.eclipse.uml2.uml.Package uPackage, final org.eclipse.uml2.uml.Package uSuperPackage, final org.emftext.language.java.containers.Package jSuperPackage, final org.emftext.language.java.containers.Package jPackage) {
      return jPackage;
    }
    
    public EObject getElement2(final org.eclipse.uml2.uml.Package uPackage, final org.eclipse.uml2.uml.Package uSuperPackage, final org.emftext.language.java.containers.Package jSuperPackage, final org.emftext.language.java.containers.Package jPackage) {
      return uPackage;
    }
    
    public EObject getCorrepondenceSourceJSuperPackage(final org.eclipse.uml2.uml.Package uPackage, final org.eclipse.uml2.uml.Package uSuperPackage) {
      return uSuperPackage;
    }
    
    public void updateJPackageElement(final org.eclipse.uml2.uml.Package uPackage, final org.eclipse.uml2.uml.Package uSuperPackage, final org.emftext.language.java.containers.Package jSuperPackage, final org.emftext.language.java.containers.Package jPackage) {
      if ((uPackage instanceof Model)) {
        jPackage.setName("");
      } else {
        Namespace _namespace = uPackage.getNamespace();
        boolean _tripleNotEquals = (_namespace != null);
        if (_tripleNotEquals) {
          EList<String> _namespaces = jPackage.getNamespaces();
          List<String> _umlParentNamespaceAsStringList = UmlClassifierAndPackageUtil.getUmlParentNamespaceAsStringList(uPackage);
          Iterables.<String>addAll(_namespaces, _umlParentNamespaceAsStringList);
        }
        String _name = uPackage.getName();
        jPackage.setName(_name);
      }
      String _buildJavaFilePath = JavaPersistenceHelper.buildJavaFilePath(jPackage);
      this.persistProjectRelative(uPackage, jPackage, _buildJavaFilePath);
    }
  }
  
  public CreateJavaPackageRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final org.eclipse.uml2.uml.Package uPackage, final org.eclipse.uml2.uml.Package uSuperPackage) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.umlToJavaClassifier.CreateJavaPackageRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.umlToJavaClassifier.RoutinesFacade(getExecutionState(), this);
    this.uPackage = uPackage;this.uSuperPackage = uSuperPackage;
  }
  
  private org.eclipse.uml2.uml.Package uPackage;
  
  private org.eclipse.uml2.uml.Package uSuperPackage;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine CreateJavaPackageRoutine with input:");
    getLogger().debug("   Package: " + this.uPackage);
    getLogger().debug("   Package: " + this.uSuperPackage);
    
    org.emftext.language.java.containers.Package jSuperPackage = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceJSuperPackage(uPackage, uSuperPackage), // correspondence source supplier
    	org.emftext.language.java.containers.Package.class,
    	(org.emftext.language.java.containers.Package _element) -> true, // correspondence precondition checker
    	null);
    registerObjectUnderModification(jSuperPackage);
    org.emftext.language.java.containers.Package jPackage = ContainersFactoryImpl.eINSTANCE.createPackage();
    notifyObjectCreated(jPackage);
    userExecution.updateJPackageElement(uPackage, uSuperPackage, jSuperPackage, jPackage);
    
    addCorrespondenceBetween(userExecution.getElement1(uPackage, uSuperPackage, jSuperPackage, jPackage), userExecution.getElement2(uPackage, uSuperPackage, jSuperPackage, jPackage), "");
    
    postprocessElements();
  }
}
