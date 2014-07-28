package uk.co.innoforce.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Java class for EnumerationValue complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EnumerationValue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://isgp.innoforce.kz/form/values}AbstractValue">
 *       &lt;sequence>
 *         &lt;element name="enum" type="{http://isgp.innoforce.kz/form/values}EnumerationType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnumerationValue", propOrder = {
    "_enum"
})
public class EnumerationValue extends AbstractValue<String> {

    @XmlElement(name = "enum", required = true)
    protected List<EnumerationType> _enum;

    /**
     * Gets the value of the enum property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
     * not a <CODE>set</CODE> method for the enum property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnum().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link EnumerationType }
     *
     *
     * @return 
     */
    public List<EnumerationType> getEnum() {
        if (_enum == null) {
            _enum = new ArrayList<>();
        }
        return this._enum;
    }

    @Override
    public String getValue(Map<String, Object> context, String fieldName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String convertValue(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
