package com.vaadin.tutorial.addressbook.frontend.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class AddressBookView extends VerticalLayout {

    public AddressBookView() {
        Button addContactBtn = new Button("Add Contact");
        addContactBtn.addClickListener(event -> {
            // Add logic to open the ContactForm
        });
        add(addContactBtn);
    }
} 
