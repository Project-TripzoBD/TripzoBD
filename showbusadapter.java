package tripzobd.com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class showbusadapter extends FirebaseRecyclerAdapter<showbusfirebase,showbusadapter.myviewholder> {

    public showbusadapter(@NonNull FirebaseRecyclerOptions<showbusfirebase> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull showbusfirebase showbusfirebase) {
        holder.buscom.setText(showbusfirebase.getBus_company_name());
        holder.bid.setText(showbusfirebase.getBus_id());
        holder.jorneyd.setText(showbusfirebase.getJourney_date());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showbuses_item,parent,false);
        return new myviewholder(view);
    }




    class myviewholder extends RecyclerView.ViewHolder{

        TextView buscom,bid,jorneyd;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            buscom=(TextView)itemView.findViewById(R.id.buscompanyxml);
            bid=(TextView)itemView.findViewById(R.id.busidxml);
            jorneyd=(TextView)itemView.findViewById(R.id.journeydatexml);
        }
    }



    
}
