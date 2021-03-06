/**
 * Copyright (c) 2013 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ac.soton.fmusim.components.diagram.parsers;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.tooling.runtime.parsers.ExpressionLabelParserBase;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

import ac.soton.fmusim.components.ComponentsPackage;
import ac.soton.fmusim.components.Connector;
import ac.soton.fmusim.components.diagram.expressions.ComponentsOCLFactory;

/**
 * @generated
 */
public class ConnectorLabelExpressionLabelParser extends
		ExpressionLabelParserBase {
	/**
	 * @generated
	 */
	public ConnectorLabelExpressionLabelParser() {
	}

	/**
	 * @generated
	 */
	@Override
	protected String getExpressionBody() {
		return ComponentsOCLFactory.getExpression(8,
				ComponentsPackage.eINSTANCE.getConnector(), null).body();
	}

	/**
	 * @generated
	 */
	public String getEditString(IAdaptable element, int flags) {
		EObject target = (EObject) element.getAdapter(EObject.class);
		Object result = ComponentsOCLFactory.getExpression(9,
				ComponentsPackage.eINSTANCE.getConnector(), null).evaluate(
				target);
		return String.valueOf(result);
	}

	/**
	 * @generated
	 */
	public IParserEditStatus isValidEditString(IAdaptable element,
			String editString) {
		return ParserEditStatus.EDITABLE_STATUS;
	}

	/**
	 * @generated
	 */
	public ICommand getParseCommand(IAdaptable element, final String newString,
			int flags) {
		final EObject target = (EObject) element.getAdapter(EObject.class);
		if (!validateValues(target, newString)) {
			return UnexecutableCommand.INSTANCE;
		}
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(target);
		if (editingDomain == null) {
			return UnexecutableCommand.INSTANCE;
		}
		IFile affectedFile = WorkspaceSynchronizer.getFile(target.eResource());
		return new AbstractTransactionalCommand(
				editingDomain,
				"Set Values", affectedFile == null ? null : Collections.singletonList(affectedFile)) { //$NON-NLS-1$ 
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				return new CommandResult(updateValues(target, newString));
			}
		};
	}

	/**
	 * @generated
	 */
	public IContentAssistProcessor getCompletionProcessor(IAdaptable element) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean validateValues(EObject target, String newString) {
		// TODO implement as needed
		return true;
	}

	/**
	 * Updates the edited value of Connector i.e. name.
	 * @generated NOT
	 */
	private IStatus updateValues(EObject target, String newString)
			throws ExecutionException {
		if (target instanceof Connector) {
			((Connector) target).setName(newString);
			return Status.OK_STATUS;
		}
		throw new ExecutionException(
				"updateValues() received an invalid target type: expected Connector");
	}

}
