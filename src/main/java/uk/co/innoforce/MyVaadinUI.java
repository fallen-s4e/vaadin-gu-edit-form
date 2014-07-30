package uk.co.innoforce;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import kz.innoforce.form.rendition.components.IVaadinComponent;
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
            setFields(Arrays.asList(
                    createIntegerField(),
                    createFieldGroup(
                            createDecimalField(),
                            createDecimalField(),
                            createDateTimeField()
                    ),
                    createFieldGroup(
                            createDecimalField(),
                            createDateField(),
                            createDateTimeField(),
                            createFieldGroup(
                                    createFieldGroup(
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

        final IVaadinComponent tf = new FECreator(form);

        getUI().addWindow(new Window() {{
            setContent(new VerticalLayout() {{
                addComponent(tf);
            }});
            setModal(true);
            center();
        }});
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

    private FieldGroup createFieldGroup(final AbstractField ... fieldsToGroup) {
        return new FieldGroup() {{
            setFields(Arrays.<AbstractField>asList(fieldsToGroup));
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


    private Field createStringField() {
        return new Field() {{
            setDisplayedName("Какая-то строка");
            setValue(new StringSimpleValue());
        }};
    }

    private Field createDateTimeField() {
        return new Field() {{
            setDisplayedName("Какое-то дата и время");
            setValue(new DateTimeSimpleValue());
        }};
    }

    private Field createDateField() {
        return new Field() {{
            setDisplayedName("Какая-то дата");
            setValue(new DateSimpleValue());
        }};
    }

    private Field createDecimalField() {
        return new Field() {{
            setDisplayedName("Какое-то дробное значение");
            setValue(new DecimalSimpleValue());
        }};
    }

    private Field createIntegerField() {
        return new Field() {{
            setDisplayedName("Какое-то целое значение");
            setValue(new IntegerSimpleValue());
        }};
    }
}
