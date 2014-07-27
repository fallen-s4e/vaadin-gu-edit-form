package uk.co.innoforce.component.editors;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.TextField;
import uk.co.innoforce.component.IVaadinComponent;

import java.util.Locale;

/**
 * @author magzhan.karasayev
 * @since 7/25/14 6:30 PM
 */
public abstract class VaadinAbstractEditor<T> extends TextField implements IVaadinComponent<T> {

    public VaadinAbstractEditor(final com.vaadin.data.util.converter.Converter<java.lang.String, T> converter) {
        setConverter(new Converter<String, T>() {
            @Override
            public T convertToModel(String s, Class<? extends T> aClass, Locale locale) throws ConversionException {
                return converter.convertToModel(s, aClass, locale);
            }

            @Override
            public String convertToPresentation(T value, Class<? extends String> aClass, Locale locale) throws ConversionException {
                return value == null ? "" : converter.convertToPresentation(value, aClass, locale);
            }

            @Override
            public Class<T> getModelType() {
                return converter.getModelType();
            }

            @Override
            public Class<String> getPresentationType() {
                return converter.getPresentationType();
            }
        });
        setConversionError("Could not convert value to {0}");
    }

    @Override
    public T getV() throws MalformedInputException {
        try {
            return (T) getConvertedValue();
        } catch (Exception e) {
            throw new MalformedInputException(e);
        }
    }

    @Override
    public void setV(T value) {
        setConvertedValue(value);
    }
}