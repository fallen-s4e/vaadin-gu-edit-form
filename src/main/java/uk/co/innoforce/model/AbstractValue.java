
package uk.co.innoforce.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;


/**
 * <p>Java class for AbstractValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 * @param <T>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractValue")
@XmlSeeAlso({
    EnumerationValue.class
})
public abstract class AbstractValue<T> {

    public abstract T getValue(Map<String, Object> context, String fieldName);

    public abstract T convertValue(String value);

    public abstract String convertValue(T value);
}
