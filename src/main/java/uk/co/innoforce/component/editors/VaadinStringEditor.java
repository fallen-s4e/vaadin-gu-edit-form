package uk.co.innoforce.component.editors;

import com.vaadin.ui.TextField;
import uk.co.innoforce.component.IComponent;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public class VaadinStringEditor extends TextField implements IComponent<String> {

    public VaadinStringEditor() {
    }

    @Override
    public String getV() throws MalformedInputException {
        return getValue();
    }

    @Override
    public void setV(String string) {
        setValue(string);
    }
}