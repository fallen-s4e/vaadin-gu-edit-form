package uk.co.innoforce.component.editors;

import com.vaadin.ui.TextField;
import uk.co.innoforce.component.IComponent;

import java.math.BigDecimal;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public class VaadinBigDecimalEditor extends TextField implements IComponent<BigDecimal> {

    public VaadinBigDecimalEditor() {

    }

    @Override
    public BigDecimal getV() {
        return null;
    }

    @Override
    public void setV(BigDecimal bigDecimal) {

    }
}