package tripzobd.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showbuses extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    showbusesadapter showbusesadapter1;
    ArrayList<showbusesfirebaseclass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbuses);

        recyclerView = findViewById(R.id.recviewshow);
        databaseReference = FirebaseDatabase.getInstance().getReference("recview");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        showbusesadapter1 = new showbusesadapter(this,list);
        recyclerView.setAdapter(showbusesadapter1);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    showbusesfirebaseclass showbusesfirebaseclass2 = dataSnapshot.getValue(showbusesfirebaseclass.class);
                    list.add(showbusesfirebaseclass2);
                }
                showbusesadapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}