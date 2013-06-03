/*
 * Copyright (c) 2013 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ac.soton.fmusim.components.diagram.part;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import ac.soton.fmusim.components.ui.wizardmenu.*;
import ac.soton.fmusim.components.FMUComponent;
import ac.soton.fmusim.components.diagram.edit.parts.FMUComponentEditPart;

/**
 * @generated NOT
 */
public class CustomAction extends AbstractHandler {
	/**
	 * @generated NOT
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		StructuredSelection stselec = (StructuredSelection) selection;
		Object element = stselec.getFirstElement();

		if (element instanceof FMUComponentEditPart) {
			FMUComponentEditPart editpart = (FMUComponentEditPart) element;
			FMUComponent comp = (FMUComponent) editpart
					.resolveSemanticElement();
			String path = comp.getPath();
			if (path != null) {
				WizardDialog dial = new WizardDialog(new Shell(), new ac.soton.fmusim.components.ui.wizardmenu.WizardStart());
				dial.open();
			}
			TransactionalEditingDomain editingDomain = editpart
					.getEditingDomain();
			/*org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog loadResourceDialog = new org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog(
					shell, editingDomain);
			loadResourceDialog.open();*/
		}
		return null;
	}

}
