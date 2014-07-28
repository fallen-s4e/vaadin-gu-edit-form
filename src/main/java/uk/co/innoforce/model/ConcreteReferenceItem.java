package uk.co.innoforce.model;

import java.util.Date;

/**
 * @author fallen
 * @since 7/28/14 3:56 PM
 */
public class ConcreteReferenceItem implements IReferenceItem {
    private String name;
    private String value;

    public ConcreteReferenceItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getCode() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDisplayName() {
        return getValue();
    }

    @Override
    public Date getActualStartDate() {
        return new Date();
    }

    @Override
    public Date getActualEndDate() {
        return new Date();
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
