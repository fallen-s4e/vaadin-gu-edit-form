package uk.co.innoforce.components;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * expandable container from stackoverflow.com
 * @author fallen
 * @since 7/29/14 4:24 PM
 */
public class ExpandableContainerSO extends TabSheet implements IExpandableContainer {

    // Defining Vertical Layout for Tab 1 content
    private final VerticalLayout verLayout1 = new VerticalLayout();

    public ExpandableContainerSO() {
        // Tab 2 content
        VerticalLayout verLayout2 = new VerticalLayout();
        verLayout2.setSizeUndefined();
        verLayout2.setMargin(true);

        addTab(verLayout1, "+", null);
        addTab(verLayout2, "-", null);
        addListener(listenerForTab());
    }

    /**
     * Method to handle tab sheet hide/show event
     *
     * @return TabSheet.SelectedTabChangeListener
     */
    private TabSheet.SelectedTabChangeListener listenerForTab() {
        // Instance of TabSheet.SelectedTabChangeListener
        TabSheet.SelectedTabChangeListener listener = new TabSheet.SelectedTabChangeListener() {
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                TabSheet tabsheet = event.getTabSheet();
                TabSheet.Tab tab = tabsheet.getTab(tabsheet.getSelectedTab());
                // Tab content displayed on setting height to the tab sheet
                if(tab.getCaption().equals("+")) {
                    tabsheet.setHeight("100%");
                } else {
                    tabsheet.setHeight("33px");
                }
            }
        };
        return listener;
    }

    @Override
    public void addComponent(Component c) {
        verLayout1.addComponent(c);
    }
}
