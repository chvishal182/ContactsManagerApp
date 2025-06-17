package com.chelv.contactsmanager.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.chelv.contactsmanager.R;
import com.chelv.contactsmanager.databinding.ContactListItemBinding;
import com.chelv.contactsmanager.entity.Contacts;

import java.util.ArrayList;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contactsArrayList;

    public ContactListAdapter(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ContactListItemBinding
                contactListItemBinding = DataBindingUtil.inflate(
                                         LayoutInflater.from(parent.getContext()),
                                         R.layout.contact_list_item,
                                         parent,
                                        false);
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

            Contacts currentContacts = contactsArrayList.get(position);
            holder.contactListItemBinding.setContact(currentContacts);
    }

    @Override
    public int getItemCount() {
        if(contactsArrayList != null){
            return contactsArrayList.size();
        }
        return 0;
    }

    public void setContactsArrayList(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
        notifyDataSetChanged();
    }


    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
