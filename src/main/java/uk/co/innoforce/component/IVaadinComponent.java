package uk.co.innoforce.component;

import com.vaadin.ui.Component;

/**
 * Shortcut type for IComponent and vaadin.Component
 *
 * @author magzhan.karasayev
 * @since 7/25/14 5:16 PM
 */
public interface IVaadinComponent<T> extends IComponent<T>, Component {
}