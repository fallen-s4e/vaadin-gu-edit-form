package uk.co.innoforce.component.editors;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.DateField;
import uk.co.innoforce.component.IVaadinComponent;

import java.util.Date;

/**
 * @author fallen
 * @since 7/26/14 4:26 PM
 */
public class VaadinDateTimeEditor extends DateField implements IVaadinComponent<Date> {

    public VaadinDateTimeEditor() {
        setResolution(Resolution.MINUTE);
    }

    @Override
    public Date getV() throws MalformedInputException {
        return getValue();
    }
    @Override
    public void setV(Date date) {
        setValue(date);
    }
}
