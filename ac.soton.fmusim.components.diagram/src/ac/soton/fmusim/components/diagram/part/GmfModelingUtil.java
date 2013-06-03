
package ac.soton.fmusim.components.diagram.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


public final class GmfModelingUtil {

    private GmfModelingUtil() {
    }

    public static EObject getModelFromGmfEditor(final DiagramEditor diagramEditor) {
        EObject model = null;
        
        View notationElement = ((View) ((DiagramEditor) diagramEditor).getDiagramEditPart()
                .getModel());
        
        if (notationElement == null) {
            return null;
        }
        
        model = (EObject) notationElement.getElement();

        return model;
    } 
    

    public static EditPart getEditPart(final EObject eObject, final EditPart rootEditPart) {

        try {
            EditPart rootEP = rootEditPart;
            if (rootEP == null) {
                DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                DiagramEditPart dep = editor.getDiagramEditPart();
                rootEP = dep;
            }
            EditPart editPart = findEditPart(rootEP, eObject);
            if (editPart == null) {
                rootEP.getViewer().getEditPartRegistry().get(eObject);
            }
            if (editPart == null) {
                @SuppressWarnings("unchecked")
                Collection<Object> editParts = rootEP.getViewer().getEditPartRegistry().values();
                for (Object object : editParts) {
                    editPart = (EditPart) object;
                    EObject model = ((View) ((EditPart) object).getModel()).getElement();
                    if (model == eObject) {
                        while (editPart.getParent() != null) {
                            EditPart parentPart = editPart.getParent();
                            Object view = parentPart.getModel();
                            if (view instanceof View) {
                                EObject parentModel = ((View) view).getElement();
                                if (parentModel == eObject) {
                                    editPart = parentPart;
                                }
                            } else {
                                break;
                            } 
                        }
                        return editPart;
                    }
                }
            }
            return editPart;
        } catch (Exception e) {
        }
        return null;
    }


    private static EditPart findEditPart(final EditPart epBegin, final EObject theElement) {
        if (theElement == null || epBegin == null) {
            return null;
        }

        final View view = (View) ((IAdaptable) epBegin).getAdapter(View.class);

        if (view != null) {
            EObject el = ViewUtil.resolveSemanticElement(view);

            if ((el != null) && el.equals(theElement)) {
                return epBegin;
            }
        }

        for (Object child : epBegin.getChildren()) {
            if (child instanceof EditPart) {
                EditPart elementEP = findEditPart((EditPart) child, theElement);
                if (elementEP != null) {
                    return elementEP;
                }
            }
        }
        return null;
    }


    @SuppressWarnings("unchecked")
	public static List<EditPart> getEditParts(final DiagramEditPart dep, final EObject theElement) {
        List<EditPart> result = new LinkedList<EditPart>();
		Collection<Object> editParts = dep.getViewer().getEditPartRegistry().values();
        for (Object object : editParts) {
            if (object instanceof EditPart) {
                EditPart editPart = (EditPart) object;
                Object objModel = editPart.getModel();
                if (objModel instanceof View) {
                    EObject model = ((View) objModel).getElement();
                    if (model == theElement) {
                        result.add(editPart);
                    }
                }
            }
        }
        return result;
    }







    public static Collection<EObject> getAllByType(final EClassifier eObjectClass,
            final EditPart rootEditPart) {
        EObject rootObject = ((View) rootEditPart.getModel()).getElement();
        TreeIterator<Object> iterator = EcoreUtil.getAllContents(rootObject, true);
        Collection<EObject> elements = EcoreUtil.getObjectsByType(iterator2Collection(iterator),
                eObjectClass);
        return elements;
    }

    public static <T> Collection<T> iterator2Collection(final Iterator<T> iter) {
        ArrayList<T> list = new ArrayList<T>();
        for (; iter.hasNext();) {
            T item = iter.next();
            list.add(item);
        }
        return list;
    }


    public static List<EObject> getModelElementsFromSelection() {
        if (PlatformUI.getWorkbench() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService() != null) {
            ISelection sel = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getSelectionService().getSelection();
            LinkedList<EObject> eo = new LinkedList<EObject>();
            if (sel instanceof StructuredSelection) {
                Iterator<?> it = ((StructuredSelection) sel).iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (next instanceof EditPart) {
                        Object model = ((EditPart) next).getModel();
                        if (model instanceof View && ((View) model).getElement() != null) {
                            eo.add(((View) model).getElement());
                        }
                    }
                }
            }
            return eo;
        }
        return null;
    }

    public static EditPart getEditPart(final EObject eObject) {
        try {
            DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            DiagramEditPart dep = editor.getDiagramEditPart();
            EditPart editPart = dep.findEditPart(dep, eObject);
            if (editPart == null) {
                Object o = dep.getViewer().getEditPartRegistry().get(eObject);
                if (o instanceof EditPart) {
                    editPart = (EditPart) o;
                }
            }
            // have to search registry manually
            if (editPart == null) {
                @SuppressWarnings("unchecked")
                Collection<Object> editParts = dep.getViewer().getEditPartRegistry().values();
                for (Object object : editParts) {
                    try {
                        EditPart theEditPart = (EditPart) object;
                        EObject model = ((View) theEditPart.getModel()).getElement();
                        if (model == eObject) {
                            while (theEditPart.getParent() != null) {
                                EditPart parentPart = theEditPart.getParent();
                                Object view = parentPart.getModel();
                                if (view instanceof View) {
                                    EObject parentModel = ((View) view).getElement();
                                    if (parentModel == eObject) {
                                        theEditPart = parentPart;
                                    }
                                } else {
                                    break;
                                } 
                            }
                            return theEditPart;
                        }

                    } catch (Exception e) {
                    }
                }
            }
            return editPart;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

}
