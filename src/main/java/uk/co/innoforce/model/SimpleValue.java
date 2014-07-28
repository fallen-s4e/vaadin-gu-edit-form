package uk.co.innoforce.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;

/**
 *
 * @author Darmen Zhakenov
 * @param <T>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SimpleValue")
@XmlSeeAlso({
})public abstract class SimpleValue<T> extends AbstractValue<T> {
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T getValue(Map<String, Object> context, String fieldName) {
        return getValue();
    }
    
}
