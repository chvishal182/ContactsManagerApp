package com.chelv.contactsmanager.ContactDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.chelv.contactsmanager.dao.ContactDAO;
import com.chelv.contactsmanager.entity.Contacts;

@Database(entities = {Contacts.class},
          version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase dbInstance;

    public abstract ContactDAO getContactDAO();

    public static synchronized ContactDatabase getDbInstance(Context context) {

        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
