package com.vaadin.tutorial.addressbook.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ContactForm extends FormLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email");

    private Button saveButton = new Button("Save");

    public ContactForm() {
        add(firstName, lastName, email, saveButton);
    }
}
