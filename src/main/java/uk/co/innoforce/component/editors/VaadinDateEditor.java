package uk.co.innoforce.component.editors;

import com.vaadin.shared.ui.datefield.Resolution;

/**
 * @author fallen
 * @since 7/26/14 4:26 PM
 */
public class VaadinDateEditor extends VaadinDateTimeEditor {
    public VaadinDateEditor() {
        setResolution(Resolution.DAY);
    }
}
