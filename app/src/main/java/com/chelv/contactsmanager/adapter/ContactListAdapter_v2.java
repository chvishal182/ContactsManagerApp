package com.chelv.contactsmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chelv.contactsmanager.R;
import com.chelv.contactsmanager.databinding.ContactListItemBinding;
import com.chelv.contactsmanager.entity.Contacts;

public class ContactListAdapter_v2 extends ListAdapter<Contacts, ContactListAdapter_v2.ContactViewHolder>{

    private static final DiffUtil.ItemCallback<Contacts>
    CONTACT_DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Contacts>() {
                @Override
                public boolean areItemsTheSame(@NonNull Contacts oldItem, @NonNull Contacts newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Contacts oldItem, @NonNull Contacts newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public ContactListAdapter_v2() {
        super(CONTACT_DIFF_CALLBACK); // Use the static DiffUtil.ItemCallback instance
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false
        );
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            Contacts currentContacts = getItem(position);
            holder.contactListItemBinding.setContact(currentContacts);
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }


}
