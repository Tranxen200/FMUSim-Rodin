package ac.soton.fmusim.components.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.tools.CreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import ac.soton.fmusim.components.Component;
import ac.soton.fmusim.components.ComponentDiagram;
import ac.soton.fmusim.components.FMUComponent;
import ac.soton.fmusim.components.diagram.providers.ComponentsElementTypes;

public class CustomRequestTool extends CreationTool{


	private List elementTypes;
	private FMUComponent component;

	public CustomRequestTool(List elementTypes) {
		super();
		this.elementTypes = elementTypes;
	}

	public CustomRequestTool(FMUComponent comp) {
		super();
		this.component = comp;

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();

		DiagramEditor diagramE = (DiagramEditor) page.getActiveEditor();
		final Diagram diag = diagramE.getDiagram();
		DiagramEditPart diagramEditPart = diagramE.getDiagramEditPart();
		CreateViewRequest documentReq = CreateViewRequestFactory.getCreateShapeRequest(ComponentsElementTypes.FMUComponent_2001, diagramEditPart.getDiagramPreferencesHint());

		Command createDocument = diagramEditPart.getCommand(documentReq);

		final Component compo = component;
		compo.setName("Copy of " + compo.getName());
		final ComponentDiagram diagram = (ComponentDiagram) diagramE.getDiagram().getElement();
		TransactionalEditingDomain editingDomain = diagramE.getEditingDomain();
		if (compo != null) {
			editingDomain.getCommandStack().execute(
					new RecordingCommand(editingDomain) {
						protected void doExecute() {
							diagram.getComponents().add(compo);
						}
					});
		}
	}


	protected Request createTargetRequest() {
		List<IElementType> etl = new ArrayList<IElementType>();
		etl.add(ComponentsElementTypes.FMUComponent_2001);
		CreateUnspecifiedTypeRequest cutr = new CreateUnspecifiedTypeRequest(etl, getPreferencesHint());
		return cutr;
	}
}
