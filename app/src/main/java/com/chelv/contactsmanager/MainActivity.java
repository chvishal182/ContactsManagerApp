package com.chelv.contactsmanager;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chelv.contactsmanager.ContactDatabase.ContactDatabase;
import com.chelv.contactsmanager.adapter.ContactListAdapter;
import com.chelv.contactsmanager.adapter.ContactListAdapter_v2;
import com.chelv.contactsmanager.clickHandlers.MainActivityClickHandlers;
import com.chelv.contactsmanager.databinding.ActivityMainBinding;
import com.chelv.contactsmanager.entity.Contacts;
import com.chelv.contactsmanager.viewmodel.ContactsViewModal;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //DataSource
    private ContactDatabase contactDatabase;

    //Adapter
    public ContactListAdapter_v2 contactListAdapter;

    //Binding
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;
    ContactsViewModal viewModal;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialiseBinds();
    }


    public void initialiseBinds(){
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers(this);

        activityMainBinding.setClickHandler(handlers);

        contactListAdapter = new ContactListAdapter_v2();

        recyclerView = activityMainBinding.contactsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        contactDatabase = ContactDatabase.getDbInstance(this);

        viewModal = new ViewModelProvider(this)
                                          .get(ContactsViewModal.class);



        recyclerView.setAdapter(contactListAdapter);

        viewModal.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> newListOfContacts) {
                        contactListAdapter.submitList(newListOfContacts);
                    }
                });

        setupSwipeToDelete();
    }

    public void setupSwipeToDelete(){

        final Drawable deleteIcon = ContextCompat.getDrawable(this, R.drawable.baseline_delete_sweep_24);
        final ColorDrawable swipeBackground = new ColorDrawable(ContextCompat.getColor(this, R.color.red));
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                Contacts contactToDelete = contactListAdapter.getCurrentList().get(position);

                if(contactToDelete != null){
                    viewModal.deleteContact(contactToDelete);

                    Snackbar.make(recyclerView, "Contact deleted", BaseTransientBottomBar.LENGTH_LONG)
                            .setAction("UNDO", view -> {
                                viewModal.addNewContact(contactToDelete);
                            }).show();
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                View itemView  = viewHolder.itemView;

                int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight())/2;
                int iconTop    = itemView.getTop() + (iconMargin);
                int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();

                if(dX < 0){
                    swipeBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(),
                            itemView.getRight(), itemView.getBottom());
                    swipeBackground.draw(c);
                    int iconLeft = itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth();
                    int iconRight = itemView.getRight() - iconMargin;
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                    deleteIcon.draw(c);
                }else{
                    swipeBackground.setBounds(0, 0, 0, 0); // Clear bounds
                    swipeBackground.draw(c);
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }

}