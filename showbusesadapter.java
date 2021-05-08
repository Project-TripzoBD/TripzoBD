package tripzobd.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class showbusesadapter extends RecyclerView.Adapter<showbusesadapter.MyViewHolder> {

    Context context;
    ArrayList<showbusesfirebaseclass> list;


    public showbusesadapter(Context context, ArrayList<showbusesfirebaseclass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.showbuses_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        showbusesfirebaseclass showbusesfirebaseclass1 = list.get(position);
        holder.force.setText(showbusesfirebaseclass1.getForce());
        holder.name.setText(showbusesfirebaseclass1.getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView force, name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            force= itemView.findViewById(R.id.forcexml);
            name = itemView.findViewById(R.id.namexml);


        }
    }
}
