package mir.routines.umlToPcm;

import java.io.IOException;
import mir.routines.umlToPcm.RoutinesFacade;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import tools.vitruv.extensions.dslsruntime.reactions.AbstractRepairRoutineRealization;
import tools.vitruv.extensions.dslsruntime.reactions.ReactionExecutionState;
import tools.vitruv.extensions.dslsruntime.reactions.structure.CallHierarchyHaving;
import tools.vitruv.framework.userinteraction.UserInteractionType;

@SuppressWarnings("all")
public class ChangeProvidedInterfaceRoutine extends AbstractRepairRoutineRealization {
  private RoutinesFacade actionsFacade;
  
  private ChangeProvidedInterfaceRoutine.ActionUserExecution userExecution;
  
  private static class ActionUserExecution extends AbstractRepairRoutineRealization.UserExecution {
    public ActionUserExecution(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy) {
      super(reactionExecutionState);
    }
    
    public EObject getElement1(final InterfaceRealization interfaceRealization, final Interface umlInterface, final OperationProvidedRole pcmRole, final OperationInterface pcmInterface) {
      return pcmRole;
    }
    
    public void update0Element(final InterfaceRealization interfaceRealization, final Interface umlInterface, final OperationProvidedRole pcmRole, final OperationInterface pcmInterface) {
      EList<NamedElement> _suppliers = interfaceRealization.getSuppliers();
      int _length = ((Object[])Conversions.unwrapArray(_suppliers, Object.class)).length;
      boolean _equals = (_length == 0);
      if (_equals) {
        pcmRole.setProvidedInterface__OperationProvidedRole(null);
      } else {
        EList<NamedElement> _suppliers_1 = interfaceRealization.getSuppliers();
        int _length_1 = ((Object[])Conversions.unwrapArray(_suppliers_1, Object.class)).length;
        boolean _equals_1 = (_length_1 == 1);
        if (_equals_1) {
          pcmRole.setProvidedInterface__OperationProvidedRole(pcmInterface);
        } else {
          this.userInteracting.showMessage(UserInteractionType.MODAL, "Further interfaces will not be provided in the PCM");
        }
      }
    }
    
    public EObject getCorrepondenceSourcePcmInterface(final InterfaceRealization interfaceRealization, final Interface umlInterface, final OperationProvidedRole pcmRole) {
      return umlInterface;
    }
    
    public EObject getCorrepondenceSourcePcmRole(final InterfaceRealization interfaceRealization, final Interface umlInterface) {
      return interfaceRealization;
    }
  }
  
  public ChangeProvidedInterfaceRoutine(final ReactionExecutionState reactionExecutionState, final CallHierarchyHaving calledBy, final InterfaceRealization interfaceRealization, final Interface umlInterface) {
    super(reactionExecutionState, calledBy);
    this.userExecution = new mir.routines.umlToPcm.ChangeProvidedInterfaceRoutine.ActionUserExecution(getExecutionState(), this);
    this.actionsFacade = new mir.routines.umlToPcm.RoutinesFacade(getExecutionState(), this);
    this.interfaceRealization = interfaceRealization;this.umlInterface = umlInterface;
  }
  
  private InterfaceRealization interfaceRealization;
  
  private Interface umlInterface;
  
  protected void executeRoutine() throws IOException {
    getLogger().debug("Called routine ChangeProvidedInterfaceRoutine with input:");
    getLogger().debug("   InterfaceRealization: " + this.interfaceRealization);
    getLogger().debug("   Interface: " + this.umlInterface);
    
    OperationProvidedRole pcmRole = getCorrespondingElement(
    	userExecution.getCorrepondenceSourcePcmRole(interfaceRealization, umlInterface), // correspondence source supplier
    	OperationProvidedRole.class,
    	(OperationProvidedRole _element) -> true, // correspondence precondition checker
    	null);
    if (pcmRole == null) {
    	return;
    }
    registerObjectUnderModification(pcmRole);
    OperationInterface pcmInterface = getCorrespondingElement(
    	userExecution.getCorrepondenceSourcePcmInterface(interfaceRealization, umlInterface, pcmRole), // correspondence source supplier
    	OperationInterface.class,
    	(OperationInterface _element) -> true, // correspondence precondition checker
    	null);
    if (pcmInterface == null) {
    	return;
    }
    registerObjectUnderModification(pcmInterface);
    // val updatedElement userExecution.getElement1(interfaceRealization, umlInterface, pcmRole, pcmInterface);
    userExecution.update0Element(interfaceRealization, umlInterface, pcmRole, pcmInterface);
    
    postprocessElements();
  }
}