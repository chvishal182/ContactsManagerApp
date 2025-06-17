package com.chelv.contactsmanager.clickHandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.chelv.contactsmanager.MainActivity;
import com.chelv.contactsmanager.entity.Contacts;
import com.chelv.contactsmanager.viewmodel.ContactsViewModal;

public class AddNewContactClickHandler {

    Contacts contact;
    Context context;
    ContactsViewModal viewModal;

    public AddNewContactClickHandler(Contacts contact, Context context, ContactsViewModal viewModal) {
        this.contact = contact;
        this.context = context;
        this.viewModal = viewModal;
    }

    public void onSubmitBtnClicked(View view){
        if(contact.getName() == null || contact.getEmail() == null){
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(context, MainActivity.class);
            Contacts c = new Contacts(
                    contact.getName(),
                    contact.getEmail()
            );

            viewModal.addNewContact(c);
            context.startActivity(i);
        }
    }
}
