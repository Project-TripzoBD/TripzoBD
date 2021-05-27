package tripzobd.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.cardview.widget.CardView;

public class SeatReservation extends AppCompatActivity {

    GridLayout mainGrid;
    Double seatPrice = 500.00;
    Double totalCost = 0.0;
    int totalSeats = 0;
    TextView totalPrice;
    TextView totalBookedSeats;
    TextView sep, stap, enp, stat, dattime,allpic;
    private Button buttonBook;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all
        setContentView(R.layout.activity_seat_reservation);
        getSupportActionBar().setTitle("Select Your Favorite Seats");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        totalBookedSeats = (TextView) findViewById(R.id.total_seats);
        totalPrice = (TextView) findViewById(R.id.total_cost);
        buttonBook = (Button) findViewById(R.id.btnBook);
        sep = (TextView) findViewById(R.id.buscid);
        stap = (TextView) findViewById(R.id.startp);
        enp = (TextView) findViewById(R.id.endp);
        stat = (TextView) findViewById(R.id.starttime1);
        dattime = (TextView) findViewById(R.id.date1);
        allpic = (TextView) findViewById(R.id.allp);

        String date = getIntent().getStringExtra("date");
        dattime.setText(date);

        String stt = getIntent().getStringExtra("stt");
        stat.setText(stt);

        String start = getIntent().getStringExtra("start");
        stap.setText(start);

        String end = getIntent().getStringExtra("end");
        enp.setText(end);

        String allpickup = getIntent().getStringExtra("allpickup");
        allpic.setText(allpickup);

        String busid = getIntent().getStringExtra("ty");
        sep.setText(busid);
        seatPrice = Double.parseDouble(busid);

        //Set Event
        setToggleEvent(mainGrid);

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SeatReservation.this,PaymentInfo.class);
                startActivity(intent);

                String totalPriceI = totalPrice.getText().toString().trim();
                String totalBookedSeatsI = totalBookedSeats.getText().toString().trim();

                PaymentDetail paymentDetail = new PaymentDetail(totalPriceI, totalBookedSeatsI);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child("SeatDetails").setValue(paymentDetail);


            }
        });

    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#00FF00"));
                        totalCost += seatPrice;
                        ++totalSeats;
                        Toast.makeText(SeatReservation.this, "You Selected Seat Number :" + (finalI + 1), Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        totalCost -= seatPrice;
                        --totalSeats;
                        Toast.makeText(SeatReservation.this, "You Unselected Seat Number :" + (finalI + 1), Toast.LENGTH_SHORT).show();
                    }
                    totalPrice.setText("" + totalCost + "0");
                    totalBookedSeats.setText("" + totalSeats);
                }
            });
        }
    }

}