package uk.co.innoforce;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import uk.co.innoforce.component.IComponent;
import uk.co.innoforce.component.IVaadinComponent;
import uk.co.innoforce.component.editors.VaadinEnumerationEditor;
import uk.co.innoforce.component.editors.VaadinStringEditor;
import uk.co.innoforce.model.DecimalSimpleValue;
import uk.co.innoforce.model.EnumerationType;
import uk.co.innoforce.model.EnumerationValue;

import javax.servlet.annotation.WebServlet;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

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
        // types:
        Date date = null;
        XMLGregorianCalendar dateTime = null;
        BigDecimal bd = null;
        BigInteger integer = null;

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        layout.addComponent(new VaadinStringEditor());

        //---------------------------------------------------------------------------
        final IVaadinComponent tf = new VaadinEnumerationEditor(new EnumerationValue(){{
            EnumerationType et1 = new EnumerationType();
            et1.setName("name1");
            et1.setValue(new DecimalSimpleValue(){{ setValue(new BigDecimal(1));}});
            getEnum().add(et1);

            EnumerationType et2 = new EnumerationType();
            et2.setName("name2");
            et2.setValue(new DecimalSimpleValue(){{ setValue(new BigDecimal(2));}});
            getEnum().add(et2);
        }});

        // And bind the field
        layout.addComponent(tf);
        //---------------------------------------------------------------------------
        final Label label = new Label("Feedback");
        final Button button = new Button("save");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    label.setValue(String.format("textfield: getV = '%s'",
                            tf.getV()
                    ));
                } catch (IComponent.MalformedInputException e) {
                    label.setValue("error occurred with name");
                }
            }
        });
        layout.addComponent(button);
        layout.addComponent(label);

    }

}
