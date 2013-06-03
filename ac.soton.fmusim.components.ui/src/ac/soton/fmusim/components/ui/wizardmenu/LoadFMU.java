package ac.soton.fmusim.components.ui.wizardmenu;

import java.io.IOException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Diagram;

import ac.soton.fmusim.components.FMUComponent;

public class LoadFMU {

	public static void setFMU(){
		//get diagram components
		IWorkbench workbench = PlatformUI.getWorkbench();
		DiagramEditor editor = (DiagramEditor)workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		Diagram diagram = editor.getDiagram();
		EObject element = diagram.getElement();
		EList<EObject> eContents = element.eContents();
		
		//check if component is FMUComponent
		for(int i=0; i<eContents.size();i++ )
		{
			EObject obj = eContents.get(i);
			if (obj.getClass().getName().endsWith("FMUComponent"))
			{
				//get the FMU file's path
				FMUComponent FMUComponent = (FMUComponent)obj;
				String FMUFilePath = FMUComponent.getPath();
				FMU fmu = null;
				try {
					fmu = new FMU(FMUFilePath);
					if(fmu != null){
						insert(fmu, FMUComponent, editor);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	
	//set the FMU property of a FMU component
	public static void insert(final FMU fmu, final FMUComponent FMUComponent, DiagramEditor editor){	
		DiagramEditPart selectedElement = editor.getDiagramEditPart();
		TransactionalEditingDomain editingDomain = selectedElement.getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			protected void doExecute() {
				FMUComponent.setFmu(fmu);
			}
		});
	}	
}

