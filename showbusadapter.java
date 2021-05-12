package tripzobd.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class showbusadapter extends RecyclerView.Adapter<busshowholder>{

    private Context mContext;
    List<showbusfirebase> mybusList;
    List<showbusfirebase> filteredUserDataList;


    public showbusadapter(Context mContext, List<showbusfirebase> mybusList) {
        this.mContext = mContext;
        this.mybusList = mybusList;
        this.filteredUserDataList = mybusList;
    }

    @Override
    public busshowholder onCreateViewHolder(@NonNull ViewGroup ViewGroup, int viewType) {

        View mView = LayoutInflater.from(ViewGroup.getContext()).inflate(R.layout.showbuses_item, ViewGroup, false);

        return new busshowholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull busshowholder holder, int position) {
        holder.buscomadapter.setText(filteredUserDataList.get(position).getBus_company_name());
        holder.bidadapter.setText(filteredUserDataList.get(position).getBus_id());
        holder.jdateadapter.setText(filteredUserDataList.get(position).getJourney_date());

    }

    @Override
    public int getItemCount() {
        return filteredUserDataList.size();
    }



     //Search working

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if(key.isEmpty()){
                    filteredUserDataList = mybusList;
                }
                else {
                    List<showbusfirebase> IsFiltered = new ArrayList<>();
                    for(showbusfirebase row: mybusList){
                        if(row.getBus_company_name().toLowerCase().contains(key.toLowerCase())){
                            IsFiltered.add(row);
                        }
                    }
                    filteredUserDataList = IsFiltered;
                }
                FilterResults filterResults= new FilterResults();
                filterResults.values = filteredUserDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {

                filteredUserDataList = (List<showbusfirebase>) results.values;
                notifyDataSetChanged();

            }
        };
    }


//End search working


}
class busshowholder extends RecyclerView.ViewHolder{
    TextView buscomadapter, bidadapter, jdateadapter;
    CardView cardadapter;

    public busshowholder(View itemView) {
        super(itemView);
        buscomadapter = itemView.findViewById(R.id.buscompanyxml);
        bidadapter = itemView.findViewById(R.id.busidxml);
        jdateadapter = itemView.findViewById(R.id.journeydatexml);
        cardadapter = itemView.findViewById(R.id.cardviewxml);
    }


}