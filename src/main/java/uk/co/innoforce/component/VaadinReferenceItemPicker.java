package uk.co.innoforce.component;

import com.vaadin.ui.Table;
import uk.co.innoforce.model.IReferenceItem;

import java.util.List;

/**
 * @author fallen
 * @since 7/28/14 3:54 PM
 */
public class VaadinReferenceItemPicker<RI extends IReferenceItem> extends PopupCombobox<RI> {

    public VaadinReferenceItemPicker(String windowCaption, RI... items) {
        super(windowCaption, items);
    }

    @Override
    protected void fillTable(Table table, List<RI> items) {
        // Define the names and data types of columns.
        // The "default value" parameter is meaningless here.
        table.addContainerProperty("Code",         String.class,  null);
        table.addContainerProperty("DisplayName",  String.class,  null);

        for (RI item : items) {
            table.addItem(new Object[] { item.getCode(), item.getDisplayName() }, item);
        }
    }

    @Override
    protected String getDisplayName(RI item) {
        return item.getDisplayName();
    }
}
