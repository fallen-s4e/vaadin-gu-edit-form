package uk.co.innoforce.component.editors;

import com.vaadin.ui.ComboBox;
import uk.co.innoforce.component.IVaadinComponent;
import uk.co.innoforce.model.EnumerationType;
import uk.co.innoforce.model.EnumerationValue;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public class VaadinEnumerationEditor extends ComboBox implements IVaadinComponent<EnumerationType> {

    public VaadinEnumerationEditor(EnumerationValue enumerationValue) {
        for (EnumerationType enumerationType : enumerationValue.getEnum()) {
            addItem(enumerationType);
            setItemCaption(enumerationType, enumerationType.getName());
        }
    }

    @Override
    public EnumerationType getV() throws MalformedInputException {
        return (EnumerationType) getValue();
    }

    @Override
    public void setV(EnumerationType value) {
        setValue(value);
    }
}