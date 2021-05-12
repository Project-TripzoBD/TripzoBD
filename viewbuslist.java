package tripzobd.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class viewbuslist extends AppCompatActivity {

    AutoCompleteTextView buscompany, sp, ep, pp1, pp2,pt1, pt2;
    EditText bid,jd, tp, st, et,  join, pickup, spp1, spp2, spp3, spp4;
    Button submitbtn;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    ValueEventListener listener, listener1;
    ArrayAdapter<String> adapter, adapter1;
    ArrayList<String> spinnerDataList, spinnerDataList1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all
        setContentView(R.layout.activity_viewbuslist);

        buscompany = (AutoCompleteTextView) findViewById(R.id.buscompanyname);
        sp = (AutoCompleteTextView) findViewById(R.id.startpoint);
        ep = (AutoCompleteTextView) findViewById(R.id.endpoint);
        pp1 = (AutoCompleteTextView) findViewById(R.id.ppoint1);
        pp2 = (AutoCompleteTextView) findViewById(R.id.ppoint2);
        bid = (EditText) findViewById(R.id.busid);
        jd = (EditText) findViewById(R.id.journeydate);
        tp = (EditText) findViewById(R.id.ticketp);
        st = (EditText) findViewById(R.id.starttime);
        et = (EditText) findViewById(R.id.endtime);
        spp1 = (EditText) findViewById(R.id.showp1);
        spp2 = (EditText) findViewById(R.id.showp2);
        spp3 = (EditText) findViewById(R.id.showp3);
        spp4 = (EditText) findViewById(R.id.showp4);
        pickup = (EditText) findViewById(R.id.pickuppoints);
        pt1 = (AutoCompleteTextView) findViewById(R.id.pptime1);
        pt2 = (AutoCompleteTextView) findViewById(R.id.pptime2);
        submitbtn = (Button) findViewById(R.id.submitbutton);
        join = (EditText) findViewById(R.id.joinshow);



        //For bus company
        databaseReference = FirebaseDatabase.getInstance().getReference("Bus company name");
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(viewbuslist.this,android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        buscompany.setAdapter(adapter);
        retriveData();

        //For location
        databaseReference = FirebaseDatabase.getInstance().getReference("Ticket counter");
        spinnerDataList1 = new ArrayList<>();
        adapter1 = new ArrayAdapter<String>(viewbuslist.this,android.R.layout.simple_spinner_dropdown_item, spinnerDataList1);
        sp.setAdapter(adapter1);
        ep.setAdapter(adapter1);
        pp1.setAdapter(adapter1);
        pp2.setAdapter(adapter1);
        pt1.setAdapter(adapter1);
        pt2.setAdapter(adapter1);
        retriveData1();


        //Journey date
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(viewbuslist.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        jd.setText(date);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        //Date setup end





        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                databaseReference = rootNode.getReference("Journey list");


                String bus_company_name = buscompany.getText().toString().trim();
                String starting_point = sp.getText().toString().trim();
                String ending_point = ep.getText().toString().trim();
                String pickup_point_1 = pp1.getText().toString().trim();
                String pickup_point_2 = pp2.getText().toString().trim();
                String ticket_price = tp.getText().toString().trim();
                String bus_id = bid.getText().toString().trim();
                String journey_date = jd.getText().toString().trim();
                String starting_time = st.getText().toString().trim();
                String ending_time = et.getText().toString().trim();
                String pickup_point_3= pt1.getText().toString().trim();
                String pickup_point_4 = pt2.getText().toString().trim();


                String searchdetails = journey_date+"/"+starting_point+"/"+ending_point;
                join.setText(searchdetails);

                String searchpickup1 = journey_date+"/"+pickup_point_1+"/"+ending_point;
                spp1.setText(searchpickup1);

                String searchpickup2 = journey_date+"/"+pickup_point_2+"/"+ending_point;
                spp2.setText(searchpickup2);

                String searchpickup3 = journey_date+"/"+pickup_point_3+"/"+ending_point;
                spp3.setText(searchpickup3);

                String searchpickup4 = journey_date+"/"+pickup_point_4+"/"+ending_point;
                spp4.setText(searchpickup4);

                String all_pickup_points = starting_point+" - "+pickup_point_1+" - "+pickup_point_2+" - "+
                        pickup_point_3+" - "+pickup_point_4+" - "+ending_point;
                pickup.setText(all_pickup_points);



                viewbuslistfirebaseclass firebaseClass = new viewbuslistfirebaseclass(bus_company_name, starting_point, ending_point,
                        pickup_point_1, pickup_point_2, ticket_price, bus_id, journey_date, starting_time, ending_time, pickup_point_3,
                        pickup_point_4,searchdetails,searchpickup1,searchpickup2,searchpickup3,searchpickup4, all_pickup_points);

                databaseReference.child(bus_id).setValue(firebaseClass);
                //databaseReference.child(journey_date).child(bus_id).child(pickup_point_1).setValue(firebaseClass);
                //databaseReference.child(journey_date).child(bus_id).child(pickup_point_2).setValue(firebaseClass);

                buscompany.setText(""); sp.setText(""); ep.setText(""); pp1.setText(""); pp2.setText(""); tp.setText("");
                bid.setText(""); jd.setText(""); st.setText(""); et.setText(""); pt1.setText(""); pt2.setText("");


                Toast.makeText(viewbuslist.this,"Data submitted successfully", Toast.LENGTH_SHORT).show();


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


    public void retriveData1(){
        listener1 = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    spinnerDataList1.add(item.getValue().toString());
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}