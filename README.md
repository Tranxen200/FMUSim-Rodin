FMUSim-Rodin
============

FMUSim-Rodin


Added methods in : 

-ac.soton.fmusim.components.diagram.edit.parts.FMUComponentVariablesCompartmentEditPart
    ->the method adding the tool on the palette is to be moved to "performFinished" of the Import function
  
    ->the "actionPerformed" method opens the wizard to enable modifications. Those modifications do not apply on finish yet.
    
    
-ac.soton.fmusim.components.diagram.part.ComponentsPaletteFactory
    ->added a few methods to add a custom Tool on the Palette
    
    
-ac.soton.fmusim.components.diagram.part.CustomCreationTool
              AND
-ac.soton.fmusim.components.diagram.part.CustomRequestTool
    ->methods to create the custom tool and add the component on the diagram
