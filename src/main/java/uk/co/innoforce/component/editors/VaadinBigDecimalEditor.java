package uk.co.innoforce.component.editors;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

import java.math.BigDecimal;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public class VaadinBigDecimalEditor extends VaadinAbstractEditor<BigDecimal> {
    public VaadinBigDecimalEditor() {
        addComponent(new Label("Stub", ContentMode.HTML));
    }
}