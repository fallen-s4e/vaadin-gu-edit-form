package uk.co.innoforce.components;

import java.util.Set;

/**
 * @author fallen
 * @since 7/30/14 5:43 PM
 */
public interface IRow {
    public Object get(String str);
    public Object put(String str, Object obj);
    public Set<String> keySet();
}
