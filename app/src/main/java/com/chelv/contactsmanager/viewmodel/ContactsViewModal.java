package com.chelv.contactsmanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.chelv.contactsmanager.entity.Contacts;
import com.chelv.contactsmanager.repository.Repository;

import java.util.List;

public class ContactsViewModal extends AndroidViewModel {

    private Repository repository;

    public ContactsViewModal(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    private LiveData<List<Contacts>> allContacts;

    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = repository.getAllContacts();
        return allContacts;
    }

    public void addNewContact(Contacts contact){
        repository.addContact(contact);
    }

    public  void deleteContact(Contacts contact){
        repository.deleteContact(contact);
    }
}
