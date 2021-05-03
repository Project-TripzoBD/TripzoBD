package tripzobd.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class showbuslist extends AppCompatActivity {
    TextInputLayout textInputLayout1, textInputLayout2, textInputLayout3;
    EditText editText1;
    Button button1;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all

        setContentView(R.layout.activity_showbuslist);

        //Push data on firebase
        textInputLayout1=findViewById(R.id.showdate);
        textInputLayout2=findViewById(R.id.showstart);
        textInputLayout3=findViewById(R.id.showend);
        editText1=findViewById(R.id.anything);
        button1 = (Button) findViewById(R.id.showenter);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Travels Details");


                String phone = textInputLayout1.getEditText().getText().toString();
                String email = textInputLayout2.getEditText().getText().toString();
                String password = textInputLayout3.getEditText().getText().toString();
                String any = editText1.getText().toString().trim();












                showfirebaseclass firebaseClass = new showfirebaseclass(email, phone, password, any);
                reference.child(any).child(email).child(password).setValue(firebaseClass);

                Toast.makeText(showbuslist.this,"Please verify your phone number", Toast.LENGTH_SHORT).show();


            }
        });




    }


}