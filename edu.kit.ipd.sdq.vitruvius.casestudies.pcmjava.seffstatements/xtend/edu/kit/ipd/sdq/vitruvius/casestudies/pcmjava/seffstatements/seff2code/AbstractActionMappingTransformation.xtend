package edu.kit.ipd.sdq.vitruvius.casestudies.pcmjava.seffstatements.seff2code

import org.palladiosimulator.pcm.seff.AbstractAction
import edu.kit.ipd.sdq.vitruvius.casestudies.pcmjava.transformations.DefaultEObjectMappingTransformation
import org.eclipse.emf.ecore.EObject
import org.emftext.language.java.members.Method
import org.apache.log4j.Logger

/**
 * The class is responsible to map a abstract action to code.
 * In order to map any AbstractAction we create ToDo markers in the code 
 */
class AbstractActionMappingTransformation extends DefaultEObjectMappingTransformation {

	val private static Logger logger = Logger.getLogger(AbstractActionMappingTransformation.simpleName)

	val public static String ABSTRACT_ACTION_MARKER = "SEFF2CODE"

	override getClassOfMappedEObject() {
		return AbstractAction
	}

	override setCorrespondenceForFeatures() {
	}

	override createEObject(EObject eObject) {
		handleCreateOrRemoveEObject("Create", eObject)
	}

	override removeEObject(EObject eObject) {
		handleCreateOrRemoveEObject("Delete", eObject)
	}

	def handleCreateOrRemoveEObject(String action, EObject eObject) {
		val abstractAction = eObject as AbstractAction
		val comment = action.getCommentFromAbstractAction(abstractAction)
		val method = getMethodForAbstractAction(abstractAction)
		// the method is the corresponding object for the AbstractAction
		method.comments.add(comment)
		return method.toArray
	}

	def String getCommentFromAbstractAction(String actionString, AbstractAction action) {
		return ABSTRACT_ACTION_MARKER + " " + actionString + " " + action.class.simpleName
	}

	def Method getMethodForAbstractAction(AbstractAction action) {
		val predecssor = action.predecessor_AbstractAction
		while (null != predecssor) {
			val methods = correspondenceInstance.getCorrespondingEObjectsByType(action, Method)
			if (!methods.nullOrEmpty) {
				return methods.get(0)
			}

		}
		logger.warn("could not find corresponding method for Abstract Action: " + action)
		return null
	}

}