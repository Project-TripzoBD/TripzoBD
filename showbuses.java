package tripzobd.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showbuses extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<showbusfirebase> mybusList1;
    showbusadapter myadapter;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    EditText searchView,searchView1;
    Button btn;
    CharSequence search="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all
        setContentView(R.layout.activity_showbuses);

        searchView = (EditText) findViewById(R.id.searchreview);
        searchView1 = (EditText) findViewById(R.id.searchreview1);
        String searchdata = getIntent().getStringExtra("keyname");
        searchView.setText(searchdata);
        btn = (Button) findViewById(R.id.butt);

       // btn.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
             //   String sview = searchView.getText().toString().trim();
               // searchView1.setText(sview);
           // }
        //});



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewxml);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loding buses....");
        mybusList1 = new ArrayList<>();


        showbusadapter myAdapter = new showbusadapter(showbuses.this, mybusList1);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Journey list");
       progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mybusList1.clear();

                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                     showbusfirebase busdata = itemSnapshot.getValue(showbusfirebase.class);
                     mybusList1.add(busdata);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });

        //btn was paste here
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String sview = searchView.getText().toString().trim();
                 searchView1.setText(sview);
                searchView1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        myAdapter.getFilter().filter(s);
                        search = s;
                        searchView.setVisibility(searchView.GONE);
                        btn.setVisibility(btn.GONE);
                        mRecyclerView.setVisibility(mRecyclerView.VISIBLE);
                    }


                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {


                    }
                });

            }



        });

    }

}