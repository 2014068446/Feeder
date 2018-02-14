package automate.capstone.feeder.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
        myHolder.tvScheduleName.setText(current.sched_name);
        myHolder.tvScheduleInfo.setText(current.start_date + " - " + current.end_date +
                "\n\n" + "Feed amount: " + current.feed_amount + "g");
    }

    @Override
    public int getItemCount() {return data.size();}

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvScheduleName;
        TextView tvScheduleInfo;
        Button btnViewSchedInfo, btnDeleteSched;
        public MyHolder(View itemView) {
            super(itemView);
            btnViewSchedInfo = (Button) itemView.findViewById(R.id.btn_view_sched_info);
            btnDeleteSched = (Button) itemView.findViewById(R.id.btn_delete_sched);
            tvScheduleName = (TextView) itemView.findViewById(R.id.tv_schedule_name_recycler);
            tvScheduleInfo = (TextView) itemView.findViewById(R.id.tv_schedule_info_recycler);

            //
            btnViewSchedInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(tvScheduleInfo.getText().toString())
                            .setCancelable(true)
                            .setTitle(tvScheduleName.getText().toString())
                            .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //isa pang dialog
                                    editAt(getAdapterPosition());
                                }
                            });
                    builder.create().show();
                }
            });
            //
            btnDeleteSched.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete " + tvScheduleName.getText().toString() + "?")
                            .setCancelable(true)
                            .setTitle("Warning!")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        removeAt(getAdapterPosition());
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder.create().show();
                }
            });

        }
    }

    //
    public void removeAt(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }
    //
    public void editAt(int position){
        //edit code


    }

}
