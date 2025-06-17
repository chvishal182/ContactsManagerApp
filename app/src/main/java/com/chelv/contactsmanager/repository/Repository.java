package com.chelv.contactsmanager.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.chelv.contactsmanager.ContactDatabase.ContactDatabase;
import com.chelv.contactsmanager.dao.ContactDAO;
import com.chelv.contactsmanager.entity.Contacts;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final ContactDAO contactDAO;
    private ExecutorService executorService;
    private Handler handler;

    public Repository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getDbInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();
        executorService = Executors.newSingleThreadExecutor();
        handler         = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contact){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });

    }

    public void deleteContact(Contacts contacts){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts(){

        return contactDAO.getAllContacts();

    }


}
