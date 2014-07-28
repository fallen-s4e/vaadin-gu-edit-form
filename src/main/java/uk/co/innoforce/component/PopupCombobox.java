package uk.co.innoforce.component;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fallen
 * @since 7/28/14 3:54 PM
 */
public abstract class PopupCombobox<T> extends HorizontalLayout implements IVaadinComponent<T> {
    private List<T> referenceItems;
    private Label selectedItemLabel = new Label("");
    private Table table;

    /** this method supposed to add ContainerProperties and add items to table */
    protected abstract void fillTable(Table table, List<T> items);
    /** returns caption of to be displayed in the interface */
    protected abstract String getCaption(T item);

    public PopupCombobox(final String windowCaption, T... items) {
        referenceItems = new ArrayList<T>(Arrays.asList(items));

        // selectedItemLabel
        addComponent(selectedItemLabel);

        // button
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/search.gif"));

        addComponent(button);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().addWindow(createSubWindow(windowCaption));
            }
        });
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
                selectedItemLabel.setCaption(getCaption((T) table.getValue()));
            }
        });
        return table;
    }

    public void setSelectedItem(T selectedItem) {
        selectedItemLabel.setCaption(table.getValue() == null ? "" : getCaption((T) table.getValue()));
    }

    @Override
    public T getV() throws MalformedInputException {
        return (T) table.getValue();
    }

    @Override
    public void setV(T item) {
        table.setValue(item);
        selectedItemLabel.setCaption(getCaption(item));
    }
}
