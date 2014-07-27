package uk.co.innoforce.component.editors;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.converter.StringToBigDecimalConverter;
import com.vaadin.ui.TextField;
import uk.co.innoforce.component.IComponent;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public class VaadinBigDecimalEditor extends TextField implements IComponent<BigDecimal> {

    public VaadinBigDecimalEditor() {
        final ObjectProperty<BigDecimal> property =
                new ObjectProperty<BigDecimal>(new BigDecimal(42));

        setConverter(new StringToBigDecimalConverter(){
            @Override
            public String convertToPresentation(BigDecimal value, Class<? extends String> targetType, Locale locale) throws ConversionException {
                return value == null ? "" : super.convertToPresentation(value, targetType, locale);
            }
        });
        setConversionError("Could not convert value to {0}");

        setPropertyDataSource(property);
    }

    @Override
    public BigDecimal getV() throws MalformedInputException {
        try {
            return (BigDecimal) getConvertedValue();
        } catch (Exception e) {
            throw new MalformedInputException(e);
        }
    }

    @Override
    public void setV(BigDecimal bigDecimal) {
        setConvertedValue(bigDecimal);
    }
}