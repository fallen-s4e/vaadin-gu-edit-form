package uk.co.innoforce.mockers;

import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
import kz.innoforce.isgp.form.info.FieldGroup;
import kz.innoforce.isgp.form.info.Form;
import kz.innoforce.isgp.form.values.*;

import java.util.Arrays;

/**
 * @author fallen
 * @since 7/30/14 2:58 PM
 */
public class DummyFormFactory {
    public static Form createDummyForm() {
        return new Form(){{
            setFormNumber("Form1");
            setFormSpec("Spec1");
            setFields(Arrays.asList(
                    createIntegerField(),
                    createFieldGroup("group 1",
                            createDecimalField(),
                            createDecimalField(),
                            createDateTimeField()
                    ),
                    createFieldGroup("group 2",
                            createDecimalField(),
                            createDateField(),
                            createDateTimeField(),
                            createFieldGroup("group 2.1",
                                    createFieldGroup("group 2.1.1",
                                            createDecimalField(),
                                            createDecimalField(),
                                            createDateTimeField()
                                    ),
                                    createDateTimeField(),
                                    createStringField(),
                                    createRefItemField()
                            )
                    ),
                    createDecimalField(),
                    createDateField(),
                    createDateTimeField(),
                    createStringField(),
                    createRefItemField()
            ));
        }};
    }


    private static FieldGroup createFieldGroup(final String nameToDisplay, final AbstractField... fieldsToGroup) {
        return new FieldGroup() {{
            setFields(Arrays.<AbstractField>asList(fieldsToGroup));
            setDisplayedName(nameToDisplay);
        }};
    }

    private static Field createRefItemField() {
        return createStringField();
        /*
        // for future testing
        new Field() {{
            final int n = 25;
            ConcreteReferenceItem[] items = new ConcreteReferenceItem[n];
            for (int i = 0; i < items.length; i++) {
                items[i] = new ConcreteReferenceItem("name" + i, "value" + i);
            }
            final IVaadinComponent tf = new VaadinReferenceItemPicker("Ref data selector table", items);


            setName("fXXX");
            setValue(new StringSimpleValue());
        }}
        */
    }


    private static int counter = 0;
    private static Field createField(final String dispName, final AbstractValue av) {
        return new Field() {{
            setName("R" + (counter++));
            setDisplayedName(String.format("%s(%s)", dispName, getName()));
            setValue(av);
        }};
    }

    private static Field createStringField() {
        return createField("Какая-то строка", new StringSimpleValue());
    }

    private static Field createDateTimeField() {
        return createField("Какое-то дата и время", new DateTimeSimpleValue());
    }

    private static Field createDateField() {
        return createField("Какая-то дата", new DateSimpleValue());
    }

    private static Field createDecimalField() {
        return createField("Какое-то дробное значение", new DecimalSimpleValue());
    }

    private static Field createIntegerField() {
        return createField("Какое-то целое значение", new IntegerSimpleValue());
    }
}
