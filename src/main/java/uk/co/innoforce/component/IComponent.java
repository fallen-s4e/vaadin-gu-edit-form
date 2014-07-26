package uk.co.innoforce.component;

/**
 * Component for a view part. AbstractValue knows how to return it and this component could be rendered to user to
 * view/edit corresponding data.
 *
 * @author magzhan.karasayev
 * @since 7/25/14 5:16 PM
 */
public interface IComponent<T> {
    public T getValue();
    public void setValue(T t);
}