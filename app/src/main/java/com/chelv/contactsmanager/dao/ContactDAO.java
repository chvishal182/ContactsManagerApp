package com.chelv.contactsmanager.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.lifecycle.LiveData;

import com.chelv.contactsmanager.entity.Contacts;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void insert(Contacts contact);

    @Delete
    void delete(Contacts contact);

    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contacts>> getAllContacts();
}
