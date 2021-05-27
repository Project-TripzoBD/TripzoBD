package tripzobd.com;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentInfo extends AppCompatActivity {

    private Button buttonFinish;
    EditText cardn, cvvn, epirydaten;
    TextView tpjava, stpjava, enpjava, sttjava, datajava, alljava, bidjava, busnamejava;
    //card number
    private Boolean checkcard(){
        String val = cardn.getText().toString();

        if (val.isEmpty()){
            cardn.setError("Card number cannot be empty");
            return false;
        } else if (val.length()>=17){
            cardn.setError("Card number cannot be more than 16");
            return false;
        }else if (val.length()<16) {
            cardn.setError("Card number cannot be less than 16");
            return false;
        } else {
            cardn.setError(null);
            return true;
        }
    }

    //cvv number check
    private Boolean cvv(){
        String val = cvvn.getText().toString();

        if (val.isEmpty()){
            cvvn.setError("CVV cannot be empty");
            return false;
        } else if (val.length()>=4){
            cvvn.setError("CVV cannot be more than 3");
            return false;
        }else if (val.length()<3) {
            cvvn.setError("CVV cannot be less than 3");
            return false;
        } else {
            cvvn.setError(null);
            return true;
        }
    }

    // Expiry check
    private Boolean expiredate(){
        String val = epirydaten.getText().toString();

        if (val.isEmpty()){
            epirydaten.setError("Expire date cannot be empty");
            return false;
        } else {
            epirydaten.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all
        setContentView(R.layout.activity_payment_info);
        cardn = (EditText) findViewById(R.id.cardnumberxml);
        cvvn = (EditText) findViewById(R.id.cvvxml);
        epirydaten = (EditText) findViewById(R.id.expirydatexml);

        tpjava = (TextView) findViewById(R.id.tppaymentxml);
        stpjava = (TextView) findViewById(R.id.startpaymentxml);
        enpjava = (TextView) findViewById(R.id.endpaymentxml);
        sttjava = (TextView) findViewById(R.id.starttimepaymentxml);
        datajava = (TextView) findViewById(R.id.datepaymentxml);
        alljava = (TextView) findViewById(R.id.allpaymentxml);
        bidjava = (TextView) findViewById(R.id.idbusxml);
        busnamejava = (TextView) findViewById(R.id.busnamexml);

        String totalcost = getIntent().getStringExtra("ticketprice");
        tpjava.setText(totalcost);

        String startpoi = getIntent().getStringExtra("startpoint");
        stpjava.setText(startpoi);

        String endpoi = getIntent().getStringExtra("endpoint");
        enpjava.setText(endpoi);

        String starttim = getIntent().getStringExtra("starttime");
        sttjava.setText(starttim);

        String date = getIntent().getStringExtra("date");
        datajava.setText(date);

        String allpicup = getIntent().getStringExtra("allpickup");
        alljava.setText(allpicup);

        String busididid = getIntent().getStringExtra("busid");
        bidjava.setText(busididid);

        String couchname = getIntent().getStringExtra("cnamename");
        busnamejava.setText(couchname);





        buttonFinish = (Button) findViewById(R.id.btnFinish);


        buttonFinish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!checkcard() | !cvv() | !expiredate() ){
                    return;
                }

                String date = datajava.getText().toString().trim();
                String ticketprice = tpjava.getText().toString().trim();
                String startitme = sttjava.getText().toString().trim();
                String startpoint = stpjava.getText().toString().trim();
                String endpoint = enpjava.getText().toString().trim();
                String allpickup = alljava.getText().toString().trim();
                String busid = bidjava.getText().toString().trim();
                String busn = busnamejava.getText().toString().trim();
                Intent intent = new Intent(PaymentInfo.this,Print.class);
                intent.putExtra("date", date);
                intent.putExtra("ticketprice", ticketprice);
                intent.putExtra("starttime", startitme);
                intent.putExtra("startpoint", startpoint);
                intent.putExtra("endpoint", endpoint);
                intent.putExtra("allpickup", allpickup);
                intent.putExtra("busid", busid);
                intent.putExtra("busn", busn);
                startActivity(intent);

            }
        });
    }
}