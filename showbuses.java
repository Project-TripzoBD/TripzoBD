package tripzobd.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class showbuses extends AppCompatActivity {
    RecyclerView recycler;
    showbusadapter adapter;
    EditText textsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbuses);

        //edittext search data
        textsearch=(EditText) findViewById(R.id.searchreview);
        String searchdata = getIntent().getStringExtra("keyname");
        textsearch.setText(searchdata);

        //recyclerview firebase data
        recycler = (RecyclerView) findViewById(R.id.recyclerviewxml);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<showbusfirebase> options =
                new FirebaseRecyclerOptions.Builder<showbusfirebase>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Journey list"), showbusfirebase.class)
                        .build();

        adapter = new showbusadapter(options);
        recycler.setAdapter(adapter);







        textsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }









}