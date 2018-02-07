package automate.capstone.feeder.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import automate.capstone.feeder.DataRecycler.DataAutomaticRecycler;
import automate.capstone.feeder.R;

/**
 * Created by Donnald on 2/7/2018.
 */

public class AdapterAutomaticMode extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<DataAutomaticRecycler> data = Collections.emptyList();
    DataAutomaticRecycler current;

    public AdapterAutomaticMode(Context context, List<DataAutomaticRecycler> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.automaticmode_recycler_layout, parent,false);
        MyHolder holder = new AdapterAutomaticMode.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (AdapterAutomaticMode.MyHolder) holder;
        current = data.get(position);
        myHolder.tvTime.setText(current.time);
    }

    @Override
    public int getItemCount() {return data.size();}

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        public MyHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_automatic_sched_recycler);

        }
    }
}
