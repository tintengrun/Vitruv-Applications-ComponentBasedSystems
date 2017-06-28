package mir.routines.umlToJavaMethod;

import java.io.IOException;
import mir.routines.umlToJavaMethod.RoutinesFacade;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.VisibilityKind;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import tools.vitruv.applications.umljava.util.java.JavaModifierUtil;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;

@SuppressWarnings("all")
public class ChangeJavaElementVisibilityRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private ChangeJavaElementVisibilityRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final NamedElement uElem, final AnnotableAndModifiable jElem) {
      return jElem;
    }
    
    public void update0Element(final NamedElement uElem, final AnnotableAndModifiable jElem) {
      VisibilityKind _visibility = uElem.getVisibility();
      JavaModifierUtil.setJavaVisibility(jElem, _visibility);
    }
    
    public EObject getCorrepondenceSourceJElem(final NamedElement uElem) {
      return uElem;
    }
  }
  
  public ChangeJavaElementVisibilityRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final NamedElement uElem) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.umlToJavaMethod.ChangeJavaElementVisibilityRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.umlToJavaMethod.RoutinesFacade(getExecutionState(), this);
    this.uElem = uElem;
  }
  
  private NamedElement uElem;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine ChangeJavaElementVisibilityRoutine with input:");
    getLogger().debug("   NamedElement: " + this.uElem);
    
    AnnotableAndModifiable jElem = getCorrespondingElement(
    	userExecution.getCorrepondenceSourceJElem(uElem), // correspondence source supplier
    	AnnotableAndModifiable.class,
    	(AnnotableAndModifiable _element) -> true, // correspondence precondition checker
    	null);
    if (jElem == null) {
    	return;
    }
    registerObjectUnderModification(jElem);
    // val updatedElement userExecution.getElement1(uElem, jElem);
    userExecution.update0Element(uElem, jElem);
    
    postprocessElements();
  }
}
