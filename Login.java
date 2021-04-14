package tripzobd.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout textInputphoneno, textInputpass;
    TextView registertextview, forgetpassword;
    Button loginbtn;

    //velidation check
    private Boolean validatephoneNo(){
        String val = textInputphoneno.getEditText().getText().toString();

        if (val.isEmpty()){
            textInputphoneno.setError("Phone number cannot be empty");
            return false;
        } else {
            textInputphoneno.setError(null);
            return true;
        }
    }

    private Boolean validatePassword (){
        String val = textInputpass.getEditText().getText().toString();

        if (val.isEmpty()) {
            textInputpass.setError("Password cannot be empty");
            return false;
        } else {
            textInputpass.setError(null);
            return true;
        }
    }

    //check completed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide action bar and title bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //thats all

        setContentView(R.layout.activity_login);


        registertextview=(TextView)findViewById(R.id.login_create_account);
        forgetpassword=(TextView)findViewById(R.id.forgetpassword);
        textInputphoneno = findViewById(R.id.loginphone);
        textInputpass = findViewById(R.id.loginpass);
        loginbtn = (Button) findViewById(R.id.loginbutton);

        //register button
        registertextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);


                //Toast.makeText(Login.this,"Tripzobd", Toast.LENGTH_SHORT).show();  when need to show something in notification
            }
        });


        //forget password button
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Forpass.class);
                startActivity(intent);


                //Toast.makeText(Login.this,"Tripzobd", Toast.LENGTH_SHORT).show();  when need to show something in notification
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatephoneNo() | !validatePassword()){
                    return;
                }else{
                    isUser();
                }

            }
        });


    }








    private void isUser() {
        String userEnteredphoneno = textInputphoneno.getEditText().getText().toString().trim();
        String userEnteredpassword = textInputpass.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users Details");
        Query CheckUser = reference.orderByChild("phone").equalTo(userEnteredphoneno);
        CheckUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){

                    textInputphoneno.setError(null);
                    textInputphoneno.setErrorEnabled(false);

                    String passwordFromDB = datasnapshot.child(userEnteredphoneno).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredpassword)){

                        textInputpass.setError(null);
                        textInputpass.setErrorEnabled(false);

                        Intent intent = new Intent(getApplicationContext(),Homepage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        textInputpass.setError("Wrong Password");
                        textInputpass.requestFocus();
                    }
                }else{
                    textInputphoneno.setError("No such user exist");
                    textInputphoneno.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
