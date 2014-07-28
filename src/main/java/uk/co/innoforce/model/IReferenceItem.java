package uk.co.innoforce.model;

import java.util.Date;

/**
 * @author fallen
 * @since 7/28/14 3:56 PM
 */
public interface IReferenceItem {
    public String getCode();
    public String getValue();
    public String getDisplayName();
    public Date getActualStartDate();
    public Date getActualEndDate();
}
