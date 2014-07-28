package uk.co.innoforce.component;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import uk.co.innoforce.model.IReferenceItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fallen
 * @since 7/28/14 3:54 PM
 */
public class PopupCombobox<RI extends IReferenceItem> extends HorizontalLayout implements IVaadinComponent<RI> {
    private List<RI> referenceItems;
    private Label selectedItemLabel = new Label("");
    private Table table;

    public PopupCombobox(RI ... items) {
        referenceItems = new ArrayList<RI>(Arrays.asList(items));

        // selectedItemLabel
        addComponent(selectedItemLabel);

        // button
        Button button = new Button();
        button.setIcon(new ThemeResource("icons/search.gif"));

        addComponent(button);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().addWindow(createSubWindow());
            }
        });
    }

    /**
     * creates a new Windows and reinitialize table inside this method
     * @return
     */
    private Window createSubWindow() {
        Window subWindow = new Window("Ref data selector table");
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

        // Define the names and data types of columns.
        // The "default value" parameter is meaningless here.
        table.addContainerProperty("Code",         String.class,  null);
        table.addContainerProperty("DisplayName",  String.class,  null);

        for (RI item : referenceItems) {
            table.addItem(new Object[] { item.getCode(), item.getDisplayName() }, item);
        }

        table.setSelectable(true);
        table.setImmediate(true);

        table.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                subWindow.close();
                selectedItemLabel.setCaption(((RI)table.getValue()).getDisplayName());
            }
        });
        return table;
    }

    public void setSelectedItem(RI selectedItem) {
        selectedItemLabel.setCaption(table.getValue() == null ? "" : ((RI) table.getValue()).getDisplayName());
    }

    @Override
    public RI getV() throws MalformedInputException {
        return (RI) table.getValue();
    }

    @Override
    public void setV(RI item) {
        table.setValue(item);
        selectedItemLabel.setCaption(item.getDisplayName());
    }
}
