package uk.co.innoforce.component;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fallen
 * @since 7/28/14 3:54 PM
 */
public abstract class PopupCombobox<T> extends HorizontalLayout implements IVaadinComponent<T> {
    private List<T> referenceItems;
    private TextField selectedItemTextField = new TextField();
    private Table table;

    /** this method supposed to add ContainerProperties and add items to table */
    protected abstract void fillTable(Table table, List<T> items);
    /** returns caption of to be displayed in the interface */
    protected abstract String getDisplayName(T item);

    public PopupCombobox(final String windowCaption, T... items) {
        referenceItems = new ArrayList<T>(Arrays.asList(items));

        // selectedItemTextField
        addComponent(selectedItemTextField);

        // button
        final Button button = new Button();
        addAttachListener(new AttachListener() {
            @Override
            public void attach(AttachEvent attachEvent) {
                if (getUI() != null) {
                    setIcon(button, "icons/search1.gif", "select!");
                }
            }
        });

        addComponent(button);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().addWindow(createSubWindow(windowCaption));
            }
        });
    }

    private void setIcon(Button button, String r, String altName) {
        InputStream s = getUI().getSession().getService().getThemeResourceAsStream(getUI(), getUI().getTheme(), r);
        if(s != null) {
            IOUtils.closeQuietly(s);
            button.setIcon(new ThemeResource(r));
        } else {
            button.setCaption(altName);
        }
    }

    /**
     * creates a new Windows and reinitialize table inside this method
     * @return
     * @param windowCaption
     */
    private Window createSubWindow(String windowCaption) {
        Window subWindow = new Window(windowCaption);
        VerticalLayout subContent = new VerticalLayout();
        subContent.setMargin(true);
        subWindow.setContent(subContent);

        // Put some components in it
        table = createTableComponent(subWindow);
        subContent.addComponent(table);

        // Center it in the browser window
        subWindow.center();
        subWindow.setModal(true);

        return subWindow;
    }

    private Table createTableComponent(final Window subWindow) {
        // Create the table with a caption.
        final Table table = new Table();

        fillTable(table, referenceItems);

        table.setSelectable(true);
        table.setImmediate(true);

        table.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                subWindow.close();
                setV((T) table.getValue());
            }
        });
        return table;
    }

    @Override
    public T getV() throws MalformedInputException {
        for (T item : referenceItems) {
            if (StringUtils.isNotBlank(getDisplayName(item)) &&
                    getDisplayName(item).equals(selectedItemTextField.getValue())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void setV(T item) {
        selectedItemTextField.setValue(getDisplayName(item));
    }
}
