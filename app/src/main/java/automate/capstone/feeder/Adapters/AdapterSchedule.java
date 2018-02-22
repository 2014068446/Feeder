package automate.capstone.feeder.Adapters;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.app.TimePickerDialog;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import automate.capstone.feeder.DataRecycler.DataAutomaticRecycler;
import automate.capstone.feeder.DataRecycler.DataSchedule;
import automate.capstone.feeder.Fragments.DatePickerFragment;
import automate.capstone.feeder.Fragments.TimePickerFragment;
import automate.capstone.feeder.R;
import automate.capstone.feeder.Store;
import automate.capstone.feeder.ViewScheduleList;

/**
 * Created by Donnald on 2/7/2018.
 */

public class AdapterSchedule extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
        private Context context;
    private LayoutInflater inflater;
    List<DataSchedule> data = Collections.emptyList();
    List<String> newdata = Collections.emptyList();
    DataSchedule current;
    String id;
    //int currentPos = 0;
    private AdapterAutomaticMode adapterAutomaticMode;
    private RecyclerView recyclerSchedule;
    TextView tvEditDateph, tvEditTimeph;
    AdapterSchedule.MyHolder myHolder;

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
        Store.schedule_id = current.id;
        myHolder.tvScheduleInfo.setText(current.start_date + " - " + current.end_date +
                "\n\n" + "Feed amount: " + current.feed_amount + "g");
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        if (myHolder.itemView.isShown()) {
            DataAutomaticRecycler dataTime = new DataAutomaticRecycler();

            adapterAutomaticMode =  new AdapterAutomaticMode(context,newdata);
            dataTime.setTime(String.format("%02d:%02d", hourOfDay, minute));
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        tvEditDateph.setText(currentDateString);
    }


    @Override
    public int getItemCount() {return data.size();}


    class MyHolder extends RecyclerView.ViewHolder{
        TextView tvScheduleName;
        TextView tvScheduleInfo;
        EditText etEditFeedAmount;
        Button btnViewSchedInfo, btnDeleteSched;
        Button btnSetTime, btnSetDate;
        public MyHolder(final View itemView) {
            super(itemView);
            btnViewSchedInfo = (Button) itemView.findViewById(R.id.btn_view_sched_info);
            btnDeleteSched = (Button) itemView.findViewById(R.id.btn_delete_sched);
            tvScheduleName = (TextView) itemView.findViewById(R.id.tv_schedule_name_recycler);
            tvScheduleInfo = (TextView) itemView.findViewById(R.id.tv_schedule_info_recycler);
            //
            btnViewSchedInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(tvScheduleInfo.getText().toString());
                    builder.setCancelable(true);
                    builder.setTitle(tvScheduleName.getText().toString());
                    builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                            final View dialogView = inflater.inflate(R.layout.schedule_edit_dialog, null);
                            dialogBuilder.setView(dialogView);

                            btnSetDate = (Button) dialogView.findViewById(R.id.btn_edit_date);


                            tvEditDateph = (TextView) dialogView.findViewById(R.id.tv_edit_date_placeholder);
                            tvEditTimeph = (TextView) dialogView.findViewById(R.id.tv_edit_time_placeholder);
                            etEditFeedAmount = (EditText) dialogView.findViewById(R.id.et_edit_feed_amount);

                            tvEditDateph.setText(current.start_date);
                            tvEditTimeph.setText(current.start_date);
                            etEditFeedAmount.setText(current.feed_amount);

                            dialogBuilder.setTitle("Editing " + current.sched_name + "...");

                            btnSetTime = (Button) dialogView.findViewById(R.id.btn_edit_time);
                            btnSetTime.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DialogFragment timepicker = new TimePickerFragment();
                                    timepicker.show(((ViewScheduleList)context).getSupportFragmentManager(), "timepicker");
                                }
                            });
                            btnSetDate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DialogFragment datepicker = new DatePickerFragment();
                                    Toast.makeText(context, "Date Picker", Toast.LENGTH_SHORT).show();
                                    datepicker.show(((ViewScheduleList)context).getSupportFragmentManager(), "datepicker");
                                }
                            });

                            dialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //confirm
                                    DataSchedule update = new DataSchedule();
                                    update = current;
                                    update.feed_amount = etEditFeedAmount.getText().toString();
                                    editAt(getAdapterPosition(), update);
                                }
                            });
                            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog b = dialogBuilder.create();
                            b.show();

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
    public void editAt(int position, DataSchedule updated){
        //edit code
        data.set(position,updated);

    }


}
