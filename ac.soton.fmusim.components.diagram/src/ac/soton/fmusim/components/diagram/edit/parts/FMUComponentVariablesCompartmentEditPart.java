/**
 * Copyright (c) 2013 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ac.soton.fmusim.components.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import ac.soton.fmusim.components.FMUComponent;
import ac.soton.fmusim.components.diagram.edit.policies.FMUComponentVariablesCompartmentCanonicalEditPolicy;
import ac.soton.fmusim.components.diagram.edit.policies.FMUComponentVariablesCompartmentItemSemanticEditPolicy;
import ac.soton.fmusim.components.diagram.part.ComponentsDiagramEditor;
import ac.soton.fmusim.components.diagram.part.ComponentsPaletteFactory;
import ac.soton.fmusim.components.diagram.part.ComponentsVisualIDRegistry;
import ac.soton.fmusim.components.diagram.part.Messages;
import ac.soton.fmusim.components.ui.wizardmenu.DisplayFMUInfo;
import ac.soton.fmusim.components.ui.wizardmenu.FMUVariable;
import ac.soton.fmusim.components.ui.wizardmenu.WizardStart;

/**
 * @generated
 */
public class FMUComponentVariablesCompartmentEditPart extends
		ListCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7001;


	/**
	 * @generated NOT
	 */
	@Override
	public void performRequest(Request req) {
	    if(req.getType() == RequestConstants.REQ_OPEN) {
	    	Object element = this.getParent();
	    	
	    	//Get FMUComponent's FMU File's path
			if (element instanceof FMUComponentEditPart) {
				FMUComponentEditPart editpart = (FMUComponentEditPart) element;
				FMUComponent original = (FMUComponent) editpart.resolveSemanticElement();
				ComponentsPaletteFactory cpf = new ComponentsPaletteFactory();
				
				EditDomain ed = editpart.getViewer().getEditDomain();
				
				FMUComponent comp = EcoreUtil.copy(original);
				
				cpf.fillPalette(ed.getPaletteViewer().getPaletteRoot(), comp);
				
				String path = comp.getPath();
				
				//Retrieve FMUComponent's ports
				List<FMUVariable> internals = new ArrayList<FMUVariable>();
				List<FMUVariable> inports = new ArrayList<FMUVariable>();
				List<FMUVariable> outports = new ArrayList<FMUVariable>();

				for(int i = 0; i<comp.getInputs().size();i++){
					inports.add(new FMUVariable(comp.getInputs().get(i).getName().toString()));
				}
				for(int j = 0; j<comp.getOutputs().size();j++){
					outports.add(new FMUVariable(comp.getOutputs().get(j).getName().toString()));
				}
				for(int k = 0; k<comp.getVariables().size();k++){
					internals.add(new FMUVariable(comp.getVariables().get(k).getName().toString()));
				}
						
				FMUVariable[] FMUInternals = internals.toArray(new FMUVariable[internals.size()]);
				FMUVariable[] FMUInports = inports.toArray(new FMUVariable[inports.size()]);
				FMUVariable[] FMUOutports = outports.toArray(new FMUVariable[outports.size()]);
				
				List<FMUVariable[]> checkedLists = new ArrayList<FMUVariable[]>();
				checkedLists.add(FMUInternals);
				checkedLists.add(FMUInports);
				checkedLists.add(FMUOutports);
				
				
				
			
				
				
				
				//Open WizardStart at page 2
				if (path != null) {
					WizardStart ws = new WizardStart();
					ws.setFilePath(path);
					WizardDialog dial = new WizardDialog(new Shell(), ws);
					dial.create();
					DisplayFMUInfo dfi = (DisplayFMUInfo) dial.getCurrentPage().getNextPage();
					dfi.setChecker(checkedLists);
					dial.showPage(dfi);
					dial.open();
				}
			}
	    }
	}
	
	/**
	 * @generated
	 */
	public FMUComponentVariablesCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected boolean hasModelChildrenChanged(Notification evt) {
		return false;
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return Messages.FMUComponentVariablesCompartmentEditPart_title;
	}

	/**
	 * @generated
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new ResizableCompartmentEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new FMUComponentVariablesCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicyWithCustomReparent(
						ComponentsVisualIDRegistry.TYPED_INSTANCE));
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new FMUComponentVariablesCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

}
