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
        implements IVaadinComponent<Map>, IExpandableContainer {

    public FECreator(Form form) {
        addFields(this, form.getFields());
    }

    private void addFields(IExpandableContainer container, List<AbstractField> fields) {
        if (fields != null) {
            Queue<Field> queue = new ArrayDeque<>();
            for (AbstractField aField : fields) {
                if (aField instanceof FieldGroup) {
                    addField(container, queue);
                    IExpandableContainer newContainer = new ExpandableContainerSO();
                    addFields(newContainer, ((FieldGroup)aField).getFields());
                    container.addComponent(newContainer);
                } else {
                    queue.add((Field) aField);
                }
            }
            addField(container, queue);
        }
    }

    private void addField(IExpandableContainer componentToAddTo, Queue<Field> fields) {
        if (fields.size() > 0) {

            while (fields.size() > 0) {
                Field field = fields.poll();
                // I believe there is no way to avoid this cast, b/c java does not support generics of higher kind
                IVaadinComponent editor = (IVaadinComponent) field.getValue().getComponent(
                        ComponentAccess.EDIT, VaadinComponentFactory.getInstance(), field.getDisplayedName());
                componentToAddTo.addComponent(new HorizontalLayout(
                        new Label(field.getDisplayedName()),
                        editor
                ));
            }
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