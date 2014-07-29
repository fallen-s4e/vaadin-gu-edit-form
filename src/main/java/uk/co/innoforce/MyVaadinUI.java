package uk.co.innoforce;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import kz.innoforce.form.rendition.components.IVaadinComponent;
import kz.innoforce.form.rendition.components.editors.VaadinStringEditor;
import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
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

        layout.addComponent(new VaadinStringEditor());

        //---------------------------------------------------------------------------

        kz.innoforce.isgp.form.info.Form form = new Form(){{
            setFields(Arrays.asList(
                    (AbstractField)new Field() {{
                        setName("f1");
                        setValue(new IntegerSimpleValue());
                    }},
                    new Field() {{
                        setName("f2");
                        setValue(new DecimalSimpleValue());
                    }},
                    new Field() {{
                        setName("f3");
                        setValue(new DateSimpleValue());
                    }},
                    new Field() {{
                        setName("f4");
                        setValue(new DateTimeSimpleValue());
                    }},
                    new Field() {{
                        setName("f5");
                        setValue(new StringSimpleValue());
                    }}

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
            ));
        }};

        final IVaadinComponent tf = new FECreator(form);

        // And bind the field
        layout.addComponent(tf);
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

}
