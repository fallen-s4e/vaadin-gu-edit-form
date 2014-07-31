package uk.co.innoforce;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import uk.co.innoforce.test.FECreatorTester;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends FECreatorTester
{
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "uk.co.innoforce.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
}
