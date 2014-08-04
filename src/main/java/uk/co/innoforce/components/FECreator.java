package uk.co.innoforce.components;

import com.vaadin.ui.*;
import kz.innoforce.form.rendition.components.IVaadinComponent;
import kz.innoforce.form.rendition.components.VaadinComponentFactory;
import kz.innoforce.isgp.form.info.AbstractField;
import kz.innoforce.isgp.form.info.Field;
import kz.innoforce.isgp.form.info.FieldGroup;
import kz.innoforce.isgp.form.info.Form;
import kz.innoforce.isgp.form.values.component.ComponentAccess;
import kz.innoforce.isgp.form.values.component.IComponent;

import java.util.*;
import java.util.function.Function;

/**
 * @author fallen
 * @since 7/29/14 11:16 AM
 */
public class FECreator extends VerticalLayout {

    private final Form form;
    private final IRow row;
    private final Button saveButton = new Button("save");
    private final Button cancelButton = new Button("cancel");
    private final Map<String, IVaadinComponent> components = new HashMap<>();

    private static class FECreeatorModel {
        private static Boolean saveToRow(Map<String, IVaadinComponent> components, IRow row,
                                      Function<String, Void> onValidationError) {
            try {
                Map<String, Object> v = getValuesAndValidate(components);
                for (String key : v.keySet()) {
                    row.put(key, v.get(key));
                }
                return true;
            } catch (IComponent.MalformedInputException e) {
                onValidationError.apply(e.getMessage());
            } catch (Exception e) {
                onValidationError.apply("внутренняя ошибка: " + e.getMessage());
            }
            return false;
        }

        private static Map<String, Object> getValuesAndValidate(Map<String, IVaadinComponent> components)
                throws IComponent.MalformedInputException {
            Map<String, Object> map = new HashMap<>();
            for (String key : components.keySet()) {
                IVaadinComponent iVaadinComponent = components.get(key);
                map.put(key, iVaadinComponent.getV());
            }
            return map;
        }
    }

    public FECreator(Form form, IRow row) {
        this.form = form;
        this.row = row;

        addComponent(getInputComponentContainers(form));
        addComponent(new HorizontalLayout() {{
            addComponent(saveButton);
            addComponent(cancelButton);
        }});

        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Function<String, Void> onError = new Function<String, Void>() {
                    @Override
                    public Void apply(String error) {
                        Notification.show("Ошибка", error, Notification.Type.WARNING_MESSAGE);
                        return null;
                    }
                };
                if (!FECreeatorModel.saveToRow(components, row, onError)) {

                }
            }
        });
    }

    private IExpandableContainer getInputComponentContainers(Form form) {
        IExpandableContainer newContainer = createNewContainer(getHeader(form));
        addFields(newContainer, form.getFields());
        newContainer.setHeight(400, Unit.PIXELS);
        newContainer.setWidth(700, Unit.PIXELS);
        return newContainer;
    }

    private static String getHeader(Form form) {
        return String.format("%s - %s", form.getFormNumber(), form.getFormSpec());
    }

    public Window getAsWindow() {
        return new Window() {{
            setContent(new VerticalLayout() {{
                addComponent(FECreator.this);
            }});
            setModal(true);
            center();
            setCaption(getHeader(form));
            Button.ClickListener cl = new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    close();
                }
            };
            saveButton.addClickListener(cl);
            cancelButton.addClickListener(cl);
        }};
    }

    private void addFields(IExpandableContainer container, List<AbstractField> fields) {
        if (fields != null) {
            Queue<Field> queue = new ArrayDeque<>();
            for (AbstractField aField : fields) {
                if (aField instanceof FieldGroup) {
                    FieldGroup grp = (FieldGroup) aField;
                    addField(container, queue);
                    IExpandableContainer newContainer = createNewContainer(grp.getDisplayedName());
                    addFields(newContainer, grp.getFields());
                    container.addComponent(newContainer);
                } else {
                    queue.add((Field) aField);
                }
            }
            addField(container, queue);
        }
    }

    private IExpandableContainer createNewContainer(String sectionName) {
        return new ExpandableContainer(sectionName);
    }

    private void addField(IExpandableContainer componentToAddTo, Queue<Field> fields) {
        if (fields.size() > 0) {

            final GridLayout grid = new GridLayout(2, fields.size());
            while (fields.size() > 0) {
                final Field field = fields.poll();
                // I believe there is no way to avoid this cast, b/c java does not support generics of higher kind
                final IVaadinComponent editor = getIVaadinComponent(field);

                // setting value
                editor.setV(row.get(field.getName()));

                final VerticalLayout l1 = new VerticalLayout(){{
                    addComponent(new Label(field.getDisplayedName()));
                    setMargin(true);
                }};
                final VerticalLayout l2 = new VerticalLayout(){{
                    addComponent(editor);
                    setMargin(true);
                }};
                grid.addComponent(l1);
                grid.addComponent(l2);
                grid.setComponentAlignment(l1, Alignment.MIDDLE_LEFT);
                grid.setComponentAlignment(l2, Alignment.MIDDLE_RIGHT);
            }
            grid.setMargin(true);
            componentToAddTo.addComponent(grid);
        }
    }

    /**
     * creates a new component and adds it to list 'components'
     */
    private IVaadinComponent getIVaadinComponent(Field field) {
        IVaadinComponent component = (IVaadinComponent) field.getValue().getComponent(
                ComponentAccess.EDIT, VaadinComponentFactory.getInstance(), field.getDisplayedName());
        components.put(field.getName(), component);
        return component;
    }
}