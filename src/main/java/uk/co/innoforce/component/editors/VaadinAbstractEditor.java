package uk.co.innoforce.component.editors;

import com.vaadin.ui.VerticalLayout;
import uk.co.innoforce.component.IComponent;

/**
 * AbstractEditor that contains value of a type T
 * @author magzhan.karasayev
 * @since 7/25/14 6:49 PM
 */
public class VaadinAbstractEditor<T> extends VerticalLayout implements IComponent<T> {

    private T value;
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
