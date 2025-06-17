package com.chelv.contactsmanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.chelv.contactsmanager.clickHandlers.AddNewContactClickHandler;
import com.chelv.contactsmanager.databinding.ActivityAddNewContactAcitvityBinding;
import com.chelv.contactsmanager.entity.Contacts;
import com.chelv.contactsmanager.viewmodel.ContactsViewModal;

public class AddNewContactAcitvity extends AppCompatActivity {

    private ActivityAddNewContactAcitvityBinding addNewContactAcitvityBinding;
    private AddNewContactClickHandler handler;
    private Contacts contacts;
    private ContactsViewModal viewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_contact_acitvity);

        contacts = new Contacts();
        addNewContactAcitvityBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact_acitvity);
        addNewContactAcitvityBinding.setContact(contacts);

        viewModal = new ViewModelProvider(this).get(ContactsViewModal.class);

        handler = new AddNewContactClickHandler(contacts, this, viewModal);
        addNewContactAcitvityBinding.setClickHandler(handler);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}