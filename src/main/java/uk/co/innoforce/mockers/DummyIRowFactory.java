package uk.co.innoforce.mockers;

import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
import kz.innoforce.isgp.form.info.FieldGroup;
import kz.innoforce.isgp.form.info.Form;
import uk.co.innoforce.IRow;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author fallen
 * @since 7/30/14 6:32 PM
 */
public class DummyIRowFactory {
    public static IRow createDummyIRow(final Form form) {
        return new IRow() {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();

            {
                for (Field field : flattenFields(new ArrayList<Field>(), form.getFields())) {
                    map.put(field.getName(), field.getValue().getValue(null, field.getName()));
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
                return map.get(str);
            }

            @Override
            public void set(String str, Object obj) {
                map.put(str, obj);
            }
        };
    }
}
