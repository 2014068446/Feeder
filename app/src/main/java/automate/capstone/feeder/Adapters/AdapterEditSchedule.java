package automate.capstone.feeder.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import automate.capstone.feeder.DataRecycler.DataAutomaticRecycler;
import automate.capstone.feeder.DataRecycler.DataSchedule;
import automate.capstone.feeder.R;

/**
 * Created by Donnald on 2/7/2018.
 */

public class AdapterEditSchedule extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<DataSchedule> data = Collections.emptyList();
    List<String> timeArray = Collections.emptyList();;
    String current;
    public Button removeTime;

    public AdapterEditSchedule(Context context, List<DataSchedule> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.timeArray = timeArray;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.edit_schedule_recycler_layout, parent,false);
        MyHolder holder = new AdapterEditSchedule.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (AdapterEditSchedule.MyHolder) holder;
        current = data.get(position).toString();
        myHolder.tvTime.setText(data.get(position).toString());
    }

    @Override
    public int getItemCount() {return data.size();}


    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        public MyHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_edit_sched_recycler);
            //removeTime = (Button) itemView.findViewById(R.id.btn_remove_time_edit_schedule);
            //removeTime.setOnClickListener(new View.OnClickListener() {
            //    @Override
             //   public void onClick(View v) {
             //       removeAt(getAdapterPosition());
             //   }
            //});

        }
    }

    public void removeAt(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
