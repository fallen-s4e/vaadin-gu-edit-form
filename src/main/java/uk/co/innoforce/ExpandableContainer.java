package uk.co.innoforce;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

/**
 * @author magzhan.karasayev
 * @since 7/29/14 4:24 PM
 */
public class ExpandableContainer extends Panel implements IExpandableContainer {

    private final VerticalLayout contentPane = new VerticalLayout();
    private final Component label;

    public ExpandableContainer(final String sectionName) {
        // for some reasons setAlignment(CENTER) does not work, so I set it manually
        label = new Label("<center><b>" + sectionName + "</b></center>", ContentMode.HTML);
        final GridLayout gl = new GridLayout(2,2);
        gl.addComponent(new Button("-") {{
            addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent clickEvent) {
                    contentPane.setVisible(!contentPane.isVisible());

                    setCaption(contentPane.isVisible() ? "-" : "+");
                }
            });
            setWidth(30, Unit.PIXELS);
        }});

        gl.addComponent(label, 1, 0);
        gl.addComponent(contentPane, 1, 1);
        gl.setWidth(100, Unit.PERCENTAGE);

        gl.setColumnExpandRatio(0, 0);
        gl.setColumnExpandRatio(1, 1);
//        gl.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        setContent(gl);
    }

    @Override
    public void addComponent(Component c) {
        contentPane.addComponent(c);
    }
}
