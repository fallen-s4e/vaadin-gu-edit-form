package uk.co.innoforce;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
import kz.innoforce.isgp.form.info.FieldGroup;
import kz.innoforce.isgp.form.info.Form;
import kz.innoforce.isgp.form.values.*;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "uk.co.innoforce.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        //---------------------------------------------------------------------------

        kz.innoforce.isgp.form.info.Form form = new Form(){{
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
        }
        };
        getUI().addWindow(new FECreator(form).getAsWindow());

        //---------------------------------------------------------------------------
        final Label label = new Label("Feedback");
        final Button button = new Button("save");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    label.setValue(String.format("sup"));
                } catch (Exception e) {
                    label.setValue("error occurred with name");
                }
            }
        });
        layout.addComponent(button);
        layout.addComponent(label);
    }

    private FieldGroup createFieldGroup(final String nameToDisplay, final AbstractField ... fieldsToGroup) {
        return new FieldGroup() {{
            setFields(Arrays.<AbstractField>asList(fieldsToGroup));
            setDisplayedName(nameToDisplay);
        }};
    }

    private Field createRefItemField() {
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


    private Field createField(final String dispName, final AbstractValue av) {
        return new Field() {{
            setDisplayedName(dispName);
            setValue(av);
        }};
    }

    private Field createStringField() {
        return createField("Какая-то строка", new StringSimpleValue());
    }

    private Field createDateTimeField() {
        return createField("Какое-то дата и время", new DateTimeSimpleValue());
    }

    private Field createDateField() {
        return createField("Какая-то дата", new DateSimpleValue());
    }

    private Field createDecimalField() {
        return createField("Какое-то дробное значение", new DecimalSimpleValue());
    }

    private Field createIntegerField() {
        return createField("Какое-то целое значение", new IntegerSimpleValue());
    }
}
