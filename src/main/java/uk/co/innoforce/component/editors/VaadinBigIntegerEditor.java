package uk.co.innoforce.component.editors;

import com.vaadin.data.util.converter.Converter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Locale;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public class VaadinBigIntegerEditor extends VaadinAbstractEditor<BigInteger> {
    public VaadinBigIntegerEditor() {
        super(new Converter<String, BigInteger>() {
            @Override
            public BigInteger convertToModel(String value, Class<? extends BigInteger> aClass, Locale locale) throws ConversionException {
                try {
                    if (StringUtils.isBlank(value)) {
                        return null;
                    }
                    return new BigInteger(value);
                } catch (Exception e) {
                    throw new ConversionException(e);
                }
            }

            @Override
            public String convertToPresentation(BigInteger value, Class<? extends String> targetType, Locale locale)
                    throws Converter.ConversionException {
                if (value == null) {
                    return null;
                }
                return value.toString();
            }

            @Override
            public Class<BigInteger> getModelType() {
                return BigInteger.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }
}