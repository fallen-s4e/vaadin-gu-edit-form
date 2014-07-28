package uk.co.innoforce.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

/**
 * <p>
 * Java class for DecimalSimpleValue complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DecimalSimpleValue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://isgp.innoforce.kz/form/values}AbstractValue">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "DecimalSimpleValue", propOrder = {
    "value"
})
public class DecimalSimpleValue extends SimpleValue<BigDecimal>  {

    /**
     * Gets the value of the value property.
     *
     * @return possible object is {@link java.math.BigDecimal }
     *
     */
    @XmlElement
    @Override
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is {@link java.math.BigDecimal }
     *
     */
    @Override
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal convertValue(String value) {
        return new BigDecimal(value);
    }

    @Override
    public String convertValue(BigDecimal value) {
        return value.toPlainString();
    }

}
