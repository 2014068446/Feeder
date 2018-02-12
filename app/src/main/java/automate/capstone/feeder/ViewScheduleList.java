package automate.capstone.feeder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import automate.capstone.feeder.Adapters.AdapterSchedule;
import automate.capstone.feeder.DataRecycler.DataSchedule;

public class ViewScheduleList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AdapterSchedule adapterSchedule;
    private RecyclerView recyclerSchedule;
    List<DataSchedule> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String schedules= Store.schedules;
        data = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(schedules); //Store.logs when connecting to db
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject json_data = jsonArray.getJSONObject(i);
                DataSchedule dataSched = new DataSchedule();
                dataSched.sched_name = json_data.getString("schedule_name");
                dataSched.start_date = json_data.getString("start_date");
                dataSched.end_date = json_data.getString("end_date");
                dataSched.date_added = json_data.getString("date_added");
                dataSched.feed_amount = json_data.getString("feed_amount");
                dataSched.id = json_data.getString("id_automatic");
                data.add(dataSched);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        adapterSchedule =  new AdapterSchedule(ViewScheduleList.this,data);

        recyclerSchedule = (RecyclerView) findViewById(R.id.recycler_schedule);
        recyclerSchedule.setLayoutManager(new LinearLayoutManager(ViewScheduleList.this));
        recyclerSchedule.setAdapter(adapterSchedule);

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
        getMenuInflater().inflate(R.menu.view_schedule_list, menu);
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
            Intent goToSettings = new Intent(this,Settings.class);
            startActivity(goToSettings);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        new NavigationItemSelect(this,id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
