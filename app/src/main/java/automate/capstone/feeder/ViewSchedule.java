package automate.capstone.feeder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import automate.capstone.feeder.Adapters.AdapterSchedule;
import automate.capstone.feeder.DataRecycler.DataLog;

public class ViewSchedule extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView testtsts;


    TextView tvEditTime,tvEditDate;
    EditText etEditFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvEditDate = (TextView) findViewById(R.id.tv_edit_date_placeholder);
        tvEditTime= (TextView) findViewById(R.id.tv_edit_time_placeholder);
        etEditFeed= (EditText) findViewById(R.id.et_edit_feed_amount);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvEditDate.setText(getIntent().getStringExtra("dateToEdit"));
        tvEditTime.setText(getIntent().getStringExtra("timeToEdit"));
        etEditFeed.setText(getIntent().getStringExtra("feedsToEdit"));

        Toast.makeText(this, "wat" + (Store.schedules_id),Toast.LENGTH_SHORT).show();

        /*
        JSONObject obj = null;
        try {
            obj = new JSONObject(Store.schedule);
            JSONArray jsonSchedule= obj.getJSONArray("schedule");
            JSONArray jsonTimes= obj.getJSONArray("times");
            for(int i = 0;i<jsonSchedule.length();i++){
                JSONObject json_data = jsonSchedule.getJSONObject(i);

                Toast.makeText(this,json_data.getString("schedule_name")+json_data.getString("feed_amount"),Toast.LENGTH_LONG).show();
                //Toast.makeText(this,json_data.getString("log_info"),Toast.LENGTH_LONG).show();

                //data.add(dataLog);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_auto) {
            Intent intent = new Intent(this, AutomaticMode.class);
            startActivity(intent);
        } else if (id == R.id.nav_man) {
            Intent intent = new Intent(this, ManualMode.class);
            startActivity(intent);
        } else if (id == R.id.nav_logs) {
            Intent intent = new Intent(this, ViewLogs.class);
            startActivity(intent);
        } else if (id == R.id.nav_schedule) {
            Intent intent = new Intent(this, ViewScheduleList.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
