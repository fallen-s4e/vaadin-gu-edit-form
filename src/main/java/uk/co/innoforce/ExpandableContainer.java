package uk.co.innoforce;

import com.vaadin.ui.*;

/**
 * @author magzhan.karasayev
 * @since 7/29/14 4:24 PM
 */
public class ExpandableContainer extends Panel implements IExpandableContainer {

    private final VerticalLayout contentPane = new VerticalLayout();
    private final Component label;

    public ExpandableContainer(String sectionName) {
        label = new Label(sectionName) {{
            setVisible(false);
        }};
        final GridLayout gl = new GridLayout(2,2);
        gl.addComponent(new Button("-") {{
            addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent clickEvent) {
                    contentPane.setVisible(!contentPane.isVisible());

                    setCaption(contentPane.isVisible() ? "-" : "+");
                    label.setVisible(!contentPane.isVisible());
                }
            });
        }});

        gl.addComponent(label, 1, 0);
        gl.addComponent(contentPane, 1, 1);

        setContent(gl);
    }

    @Override
    public void addComponent(Component c) {
        contentPane.addComponent(c);
    }
}
