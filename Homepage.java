package tripzobd.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Homepage extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView1;
    AutoCompleteTextView autoCompleteTextView2;
    DatabaseReference databaseReference;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;
    EditText journeydate, joindetails;
    Button searchbtn;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all
        setContentView(R.layout.activity_homepage);
        autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.source);
        autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.destination);
        searchbtn =(Button) findViewById(R.id.searchxml);
        joindetails = (EditText) findViewById(R.id.bookjoinxml);
        journeydate = findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Ticket counter");
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(Homepage.this,android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        autoCompleteTextView1.setAdapter(adapter);
        autoCompleteTextView2.setAdapter(adapter);
        retriveData();


        //journey date
       final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        journeydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Homepage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                       journeydate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        //search button
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stpoint = autoCompleteTextView1.getText().toString().trim();
                String endpoint = autoCompleteTextView2.getText().toString().trim();
                String jordate = journeydate.getText().toString().trim();

                //String searchdetails = jordate+"/"+stpoint+"/"+endpoint;
                String searchdetails = "From : "+stpoint+"\n To : "+endpoint+"\n Date : "+jordate;;
                joindetails.setText(searchdetails);

                Intent intent = new Intent(Homepage.this,showbuses.class);
                intent.putExtra("keyname",searchdetails);
                startActivity(intent);
            }
        });



    }






    public void retriveData(){
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    spinnerDataList.add(item.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}