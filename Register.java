 package tripzobd.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextInputLayout textInputLayout1, textInputLayout2, textInputLayout3;
    Button button1;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //velidation check
    private Boolean validatephoneNo(){
        String val = textInputLayout2.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{11,11}\\z";

        if (val.isEmpty()){
            textInputLayout2.setError("Phone number cannot be empty");
            return false;
        } else if (val.length()>=12){
            textInputLayout2.setError("Bangladesh phone number cannot be more than 11");
            return false;
        }else if (val.length()<11){
            textInputLayout2.setError("Bangladesh Phone number cannot be less than 11");
            return false;
        }else if (!val.matches(noWhiteSpace)){
            textInputLayout2.setError("Space are not allowed");
            return false;
        }else {
            textInputLayout2.setError(null);
            return true;
        }
    }

    private Boolean validatePassword (){
        String val = textInputLayout3.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +
                "(?=.*[@#$%^&+=])" +
                ".{6,}" +
                "$";

        if (val.isEmpty()) {
            textInputLayout3.setError("Password cannot be empty");
            return false;
        }else if (!val.matches(passwordVal)) {
            textInputLayout3.setError("Password atleast need six & one special character");
            return false;
        }
        else {
            textInputLayout3.setError(null);
            return true;
        }
    }


    //Check completed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all

        setContentView(R.layout.activity_register);

        //Push data on firebase
        textInputLayout1=findViewById(R.id.reg_email);
        textInputLayout2=findViewById(R.id.reg_phone);
        textInputLayout3=findViewById(R.id.reg_pass);
        button1 = (Button) findViewById(R.id.reg_signup);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users Details");

                if (!validatephoneNo() | !validatePassword()){
                    return;
                }

                String email = textInputLayout1.getEditText().getText().toString();
                String phone = textInputLayout2.getEditText().getText().toString();
                String password = textInputLayout3.getEditText().getText().toString();

                Intent intent = new Intent(getApplicationContext(), verifyphoneno.class);
                intent.putExtra("phoneNo", phone);
                startActivity(intent);

                UsersFirebaseClass firebaseClass = new UsersFirebaseClass(email, phone, password);
                reference.child(phone).setValue(firebaseClass);

                Toast.makeText(Register.this,"Please verify your phone number", Toast.LENGTH_SHORT).show();


            }
        });




    }


}