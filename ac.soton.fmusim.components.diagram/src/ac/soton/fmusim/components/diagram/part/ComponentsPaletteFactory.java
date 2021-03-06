/**
 * Copyright (c) 2013 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ac.soton.fmusim.components.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import ac.soton.fmusim.components.ComponentsPackage;
import ac.soton.fmusim.components.FMUComponent;
import ac.soton.fmusim.components.diagram.providers.ComponentsElementTypes;

/**
 * @generated
 */
public class ComponentsPaletteFactory {

	/**
	 * @generated NOT
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createComponents1Group());
	}
	
	public void fillPalette(PaletteRoot paletteRoot, FMUComponent component) {
		paletteRoot.add(createComponents1Group(component));
	}

	/**
	 * Creates "components" palette tool group
	 * @generated
	 */
	private PaletteContainer createComponents1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Components1Group_title);
		paletteContainer.setId("createComponents1Group"); //$NON-NLS-1$
		paletteContainer.add(createFMUComponent1CreationTool());
		paletteContainer.add(createVariable2CreationTool());
		paletteContainer.add(createEventBComponent3CreationTool());
		paletteContainer.add(createInputPort4CreationTool());
		paletteContainer.add(createOutputPort5CreationTool());
		paletteContainer.add(createConnector6CreationTool());
		paletteContainer.add(createLink7CreationTool());
		return paletteContainer;
	}
	
	private PaletteContainer createComponents1Group(FMUComponent component) {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.Components1Group_title);
		paletteContainer.setId("test"); //$NON-NLS-1$
		paletteContainer.add(createCustomFMU(component));
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFMUComponent1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.FMUComponent1CreationTool_title,
				Messages.FMUComponent1CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.FMUComponent_2001));
		entry.setId("createFMUComponent1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/FMUComponent.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	private ToolEntry createCustomFMU(FMUComponent component){//Object component) {
		
		CustomCreationTool cct = new CustomCreationTool("CustomFMU", "Create a custom FMU Component", null, component);

		cct.setToolProperty("comp", component);
		cct.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/FMUComponent.gif")); //$NON-NLS-1$
		cct.setLargeIcon(cct.getSmallIcon());
		
		
	//	javax.swing.JOptionPane.showMessageDialog(null,((FMUComponent)cct.getToolProperty("comp")).getName()); 
		
		return cct;
	}
	
	
	/**
	 * @generated
	 */
	private ToolEntry createVariable2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Variable2CreationTool_title, null,
				Collections.singletonList(ComponentsElementTypes.Variable_3005));
		entry.setId("createVariable2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/Variable.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEventBComponent3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.EventBComponent3CreationTool_title,
				Messages.EventBComponent3CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.EventBComponent_2002));
		entry.setId("createEventBComponent3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/EventBComponent.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInputPort4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ComponentsElementTypes.FMUPort_3001);
		types.add(ComponentsElementTypes.EventBPort_3003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.InputPort4CreationTool_title,
				Messages.InputPort4CreationTool_desc, types);
		entry.setId("createInputPort4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/InputPort.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOutputPort5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ComponentsElementTypes.FMUPort_3002);
		types.add(ComponentsElementTypes.EventBPort_3004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.OutputPort5CreationTool_title,
				Messages.OutputPort5CreationTool_desc, types);
		entry.setId("createOutputPort5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/OutputPort.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConnector6CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Connector6CreationTool_title,
				Messages.Connector6CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.Connector_2003));
		entry.setId("createConnector6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/Connector.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLink7CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Link7CreationTool_title,
				Messages.Link7CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.PortConnector_4001));
		entry.setId("createLink7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.fmusim.components/icons/Link.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
