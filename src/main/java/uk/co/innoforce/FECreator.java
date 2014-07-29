package uk.co.innoforce;

import com.vaadin.ui.*;
import kz.innoforce.form.rendition.components.IVaadinComponent;
import kz.innoforce.form.rendition.components.VaadinComponentFactory;
import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
import kz.innoforce.isgp.form.info.FieldGroup;
import kz.innoforce.isgp.form.info.Form;
import kz.innoforce.isgp.form.values.component.ComponentAccess;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author fallen
 * @since 7/29/14 11:16 AM
 */
public class FECreator extends VerticalLayout
        implements IVaadinComponent<Map> {

    public FECreator(Form form) {
        IExpandableContainer newContainer = createNewContainer();
        addFields(newContainer, form.getFields());
        addComponent(newContainer);
    }

    private void addFields(IExpandableContainer container, List<AbstractField> fields) {
        if (fields != null) {
            Queue<Field> queue = new ArrayDeque<>();
            for (AbstractField aField : fields) {
                if (aField instanceof FieldGroup) {
                    addField(container, queue);
                    IExpandableContainer newContainer = createNewContainer();
                    addFields(newContainer, ((FieldGroup)aField).getFields());
                    container.addComponent(newContainer);
                } else {
                    queue.add((Field) aField);
                }
            }
            addField(container, queue);
        }
    }

    private IExpandableContainer createNewContainer() {
        return new ExpandableContainer();
    }

    private void addField(IExpandableContainer componentToAddTo, Queue<Field> fields) {
        if (fields.size() > 0) {

            final GridLayout grid = new GridLayout(2, fields.size());
            while (fields.size() > 0) {
                final Field field = fields.poll();
                // I believe there is no way to avoid this cast, b/c java does not support generics of higher kind
                final IVaadinComponent editor = (IVaadinComponent) field.getValue().getComponent(
                        ComponentAccess.EDIT, VaadinComponentFactory.getInstance(), field.getDisplayedName());

                final VerticalLayout l1 = new VerticalLayout(){{
                    addComponent(new Label(field.getDisplayedName()));
                    setMargin(true);
                }};
                final VerticalLayout l2 = new VerticalLayout(){{
                    addComponent(editor);
                    setMargin(true);
                }};
                grid.addComponent(l1);
                grid.addComponent(l2);
                grid.setComponentAlignment(l1, Alignment.MIDDLE_LEFT);
                grid.setComponentAlignment(l2, Alignment.MIDDLE_RIGHT);
            }
            grid.setMargin(true);
            componentToAddTo.addComponent(grid);
        }
    }

    @Override
    public Map getV() throws MalformedInputException {
        return null;
    }

    @Override
    public void setV(Map map) {

    }
}