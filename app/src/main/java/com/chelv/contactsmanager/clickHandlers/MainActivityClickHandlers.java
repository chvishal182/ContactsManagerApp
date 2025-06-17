package com.chelv.contactsmanager.clickHandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chelv.contactsmanager.AddNewContactAcitvity;

public class MainActivityClickHandlers {

    Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }

    public void onFABClicked(View view){
        Intent addContact = new Intent(view.getContext(), AddNewContactAcitvity.class);
        context.startActivity(addContact);
    }
}
