package automate.capstone.feeder.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import automate.capstone.feeder.DataRecycler.DataSchedule;
import automate.capstone.feeder.R;

/**
 * Created by Donnald on 2/7/2018.
 */

public class AdapterSchedule extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<DataSchedule> data = Collections.emptyList();
    DataSchedule current;
    //int currentPos = 0;

    public AdapterSchedule(Context context, List<DataSchedule> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.schedule_row_layout, parent,false);
        MyHolder holder = new AdapterSchedule.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AdapterSchedule.MyHolder myHolder = (AdapterSchedule.MyHolder) holder;
        current = data.get(position);
        myHolder.tvScheduleName.setText(current.schedname);
        myHolder.tvScheduleInfo.setText(current.schedinfo);
    }

    @Override
    public int getItemCount() {return data.size();}

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvScheduleName;
        TextView tvScheduleInfo;
        public MyHolder(View itemView) {
            super(itemView);
            tvScheduleName = (TextView) itemView.findViewById(R.id.tv_schedule_name_recycler);
            tvScheduleInfo = (TextView) itemView.findViewById(R.id.tv_schedule_info_recycler);
        }
    }
}
