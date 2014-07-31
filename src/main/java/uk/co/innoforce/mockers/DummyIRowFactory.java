package uk.co.innoforce.mockers;

import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
import kz.innoforce.isgp.form.info.FieldGroup;
import kz.innoforce.isgp.form.info.Form;
import uk.co.innoforce.components.IRow;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author fallen
 * @since 7/30/14 6:32 PM
 */
public class DummyIRowFactory {
    public static IRow createDummyIRow(final Form form) {
        abstract class IRowIpl extends LinkedHashMap<String, Object> implements IRow {};
        return new IRowIpl() {
            {
                for (Field field : flattenFields(new ArrayList<Field>(), form.getFields())) {
                    put(field.getName(), field.getValue().getValue(null, field.getName()));
                }
            }

            private List<Field> flattenFields(List<Field> arr, List<AbstractField> fields) {
                for (AbstractField field : fields) {
                    if (field instanceof Field) {
                        arr.add((Field) field);
                    } else {
                        flattenFields(arr, ((FieldGroup)field).getFields());
                    }
                }
                return arr;
            }

            @Override
            public Object get(String str) {
                return get((Object)str);
            }
        };
    }
}
