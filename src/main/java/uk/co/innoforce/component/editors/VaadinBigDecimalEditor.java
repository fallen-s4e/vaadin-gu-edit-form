package uk.co.innoforce.component.editors;

import com.vaadin.data.util.converter.StringToBigDecimalConverter;

import java.math.BigDecimal;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public class VaadinBigDecimalEditor extends VaadinAbstractEditor<BigDecimal> {
    public VaadinBigDecimalEditor() {
        super(new StringToBigDecimalConverter());
    }
}