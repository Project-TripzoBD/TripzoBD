package tripzobd.com;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private RecyclerViewClickListener listener;
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
        holder.allpickupadapter.setText(filteredUserDataList.get(position).getAll_pickup_points());
        holder.buscomadapter.setText(filteredUserDataList.get(position).getBus_company_name());
        holder.bidadapter.setText(filteredUserDataList.get(position).getBus_id());
        holder.endingptadapter.setText(filteredUserDataList.get(position).getEnding_point());
        holder.endingtimeadapter.setText(filteredUserDataList.get(position).getEnding_time());
        holder.jdateadapter.setText(filteredUserDataList.get(position).getJourney_date());
        holder.pp1adapter.setText(filteredUserDataList.get(position).getPickup_point_1());
        holder.pp2adapter.setText(filteredUserDataList.get(position).getPickup_point_2());
        holder.pp3adapter.setText(filteredUserDataList.get(position).getPickup_point_3());
        holder.pp4adapter.setText(filteredUserDataList.get(position).getPickup_point_4());
        holder.searchdetailsapapter.setText(filteredUserDataList.get(position).getSearchdetails());
        holder.searchp1adapter.setText(filteredUserDataList.get(position).getSearchpickup1());
        holder.searchp2adapter.setText(filteredUserDataList.get(position).getSearchpickup2());
        holder.searchp3adapter.setText(filteredUserDataList.get(position).getSearchpickup3());
        holder.searchp4adapter.setText(filteredUserDataList.get(position).getSearchpickup4());
        holder.startpointadapter.setText(filteredUserDataList.get(position).getStarting_point());
        holder.sttimeadapter.setText(filteredUserDataList.get(position).getStarting_time());
        holder.ticketpriceadapter.setText(filteredUserDataList.get(position).getTicket_price());

        holder.nbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = holder.buscomadapter.getText().toString().trim();
               Intent intent = new Intent(v.getContext(), showbusesdetails.class);
               intent.putExtra("keyname",str1);
               mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredUserDataList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
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
                    for(showbusfirebase row: mybusList ){
                        if(row.getSearchdetails().toLowerCase().contains(key.toLowerCase())){
                            IsFiltered.add(row);
                        }
                        else if(row.getSearchpickup1().toLowerCase().contains(key.toLowerCase())){
                            IsFiltered.add(row);
                        }
                        else if(row.getSearchpickup2().toLowerCase().contains(key.toLowerCase())){
                            IsFiltered.add(row);
                        }
                        else if(row.getSearchpickup3().toLowerCase().contains(key.toLowerCase())){
                            IsFiltered.add(row);
                        }
                        else if(row.getSearchpickup4().toLowerCase().contains(key.toLowerCase())){
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
class busshowholder extends RecyclerView.ViewHolder {
    TextView allpickupadapter, buscomadapter, bidadapter, endingptadapter, endingtimeadapter, jdateadapter, pp1adapter, pp2adapter,
            pp3adapter,pp4adapter, searchdetailsapapter, searchp1adapter,searchp2adapter,searchp3adapter,searchp4adapter,
            startpointadapter,sttimeadapter, ticketpriceadapter;
    Button nbtn;
    CardView cardadapter;

    public busshowholder(View itemView) {
        super(itemView);

        allpickupadapter = itemView.findViewById(R.id.allptxml);
        buscomadapter = itemView.findViewById(R.id.buscompanyxml);
        bidadapter = itemView.findViewById(R.id.busidxml);
        endingptadapter = itemView.findViewById(R.id.edptxml);
        endingtimeadapter = itemView.findViewById(R.id.edtmxml);
        jdateadapter = itemView.findViewById(R.id.journeydatexml);
        pp1adapter = itemView.findViewById(R.id.pip1txml);
        pp2adapter = itemView.findViewById(R.id.pip2txml);
        pp3adapter = itemView.findViewById(R.id.pip3txml);
        pp4adapter = itemView.findViewById(R.id.pip4txml);
        searchdetailsapapter = itemView.findViewById(R.id.sedtxml);
        searchp1adapter = itemView.findViewById(R.id.sepk1xml);
        searchp2adapter = itemView.findViewById(R.id.sepk2xml);
        searchp3adapter = itemView.findViewById(R.id.sepk3xml);
        searchp4adapter = itemView.findViewById(R.id.sepk4xml);
        startpointadapter = itemView.findViewById(R.id.stptxml);
        sttimeadapter = itemView.findViewById(R.id.sttmxml);
        ticketpriceadapter = itemView.findViewById(R.id.takaxml);

        nbtn = itemView.findViewById(R.id.viewseatnext);
        cardadapter = itemView.findViewById(R.id.cardviewxml);
    }
}
