package uk.co.innoforce;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import kz.innoforce.form.rendition.components.IVaadinComponent;
import kz.innoforce.form.rendition.components.VaadinComponentFactory;
import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
import kz.innoforce.isgp.form.info.FieldGroup;
import kz.innoforce.isgp.form.info.Form;
import kz.innoforce.isgp.form.values.component.ComponentAccess;

import java.util.List;
import java.util.Map;

/**
 * @author fallen
 * @since 7/29/14 11:16 AM
 */
public class FECreator extends VerticalLayout
        implements IVaadinComponent<Map> {

    public FECreator(Form form) {
        addFields(form.getFields());
    }

    private void addFields(List<AbstractField> fields) {
        if (fields != null) {
            for (AbstractField aField : fields) {
                if (aField instanceof FieldGroup) {
                    addFields(((FieldGroup)aField).getFields());
                } else {
                    addField((Field) aField);
                }
            }
        }
    }

    private void addField(Field field) {
        // I believe there is no way to avoid this cast, b/c java does not support generics of higher kind
        IVaadinComponent editor = (IVaadinComponent) field.getValue().getComponent(
                ComponentAccess.EDIT, VaadinComponentFactory.getInstance(), field.getDisplayedName());
        addComponent(new HorizontalLayout(
                new Label(field.getDisplayedName()),
                editor
        ));
    }

    @Override
    public Map getV() throws MalformedInputException {
        return null;
    }

    @Override
    public void setV(Map map) {

    }
}