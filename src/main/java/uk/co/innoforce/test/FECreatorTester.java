package uk.co.innoforce.test;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import kz.innoforce.isgp.form.info.Form;
import uk.co.innoforce.components.FECreator;
import uk.co.innoforce.components.IRow;
import uk.co.innoforce.mockers.DummyFormFactory;
import uk.co.innoforce.mockers.DummyIRowFactory;

public abstract class FECreatorTester extends UI
{
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        //---------------------------------------------------------------------------

        Form form = DummyFormFactory.createDummyForm();
        IRow row = DummyIRowFactory.createDummyIRow(form);
        getUI().addWindow(new FECreator(form, row).getAsWindow());

        //---------------------------------------------------------------------------
        final Label label = new Label("Feedback", ContentMode.HTML);
        final Button button = new Button("save");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    StringBuilder sb = new StringBuilder();
                    for (String s : row.keySet()) {
                        sb.append(String.format("%s = %s <br />", s, row.get(s)));
                    }
                    label.setValue(sb.toString());
                } catch (Exception e) {
                    label.setValue("error occurred with name");
                }
            }
        });
        layout.addComponent(button);
        layout.addComponent(label);
    }
}
