package ac.soton.fmusim.components.diagram.part;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import ac.soton.fmusim.components.FMUComponent;

public class CustomCreationTool extends ToolEntry{


	private final List<IElementType> elementTypes;
	private FMUComponent component;


	public CustomCreationTool(String title, String description, List<IElementType> elementTypes) {
		super(title, description, null, null);
		this.elementTypes = elementTypes;
	}
	
	public CustomCreationTool(String title, String description, List<IElementType> elementTypes, FMUComponent component) {
		super(title, description, null, null);
		this.elementTypes = null;
		this.component = component;
	}


	@Override
	public Tool createTool() {
		Tool tool = new CustomRequestTool(component);
		tool.setProperties(getToolProperties());
		return tool;
	}
	
	
}
