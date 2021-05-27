package tripzobd.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Print extends AppCompatActivity {

    TextView daja, sttja, spja, epja,allpja, tpja, cidja, cnameja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all
        setContentView(R.layout.activity_print);

        daja = (TextView) findViewById(R.id.jdx);
        sttja = (TextView) findViewById(R.id.jtx);
        spja = (TextView) findViewById(R.id.fromx);
        epja = (TextView) findViewById(R.id.tox);
        allpja = (TextView) findViewById(R.id.bpx);
        tpja = (TextView) findViewById(R.id.tcx);
        cidja = (TextView) findViewById(R.id.cidx);
        cnameja = (TextView) findViewById(R.id.cnx);

        String totalcost = getIntent().getStringExtra("ticketprice");
        tpja.setText(totalcost);

        String startpoi = getIntent().getStringExtra("startpoint");
        spja.setText(startpoi);

        String endpoi = getIntent().getStringExtra("endpoint");
        epja.setText(endpoi);

        String starttim = getIntent().getStringExtra("starttime");
        sttja.setText(starttim);

        String date = getIntent().getStringExtra("date");
        daja.setText(date);

        String allpicup = getIntent().getStringExtra("allpickup");
        allpja.setText(allpicup);

        String busididid = getIntent().getStringExtra("busid");
        cidja.setText(busididid);

        String namebus = getIntent().getStringExtra("busn");
        cnameja.setText(namebus);


    }
}