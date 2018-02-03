package automate.capstone.feeder;

import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class ViewLogs extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper dh = new DatabaseHelper(this);
    TextView test;
    ListView listView;
    ProgressDialog pDialog;
    private RecyclerView recyclerLog;
    private AdapterLog adapterLog;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logs);
        //Make call to AsyncTask
String asd = "[{\"id_logs\":\"1\",\"log_type\":\"test\",\"log_info\":\"test loginfo\",\"date_add\":\"2018-01-30 07:16:34\"},{\"id_logs\":\"2\",\"log_type\":\"log test 2\",\"log_info\":\"log test description 2\",\"date_add\":\"2018-01-30 09:34:35\"}]";
        List<DataLog> data = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(asd);

            String stor3 ="";
            for(int i = 0;i<=jsonArray.length();i++){
                JSONObject json_data = jsonArray.getJSONObject(i);
                DataLog dataLog = new DataLog();
                dataLog.logtype= json_data.getString("log_info");
                dataLog.loginfo= json_data.getString("log_type");
                data.add(dataLog);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerLog = (RecyclerView) findViewById(R.id.recyclerlog);
        adapterLog = new AdapterLog(ViewLogs.this, data);

        recyclerLog.setAdapter(adapterLog);
        recyclerLog.setLayoutManager(new LinearLayoutManager(ViewLogs.this));

        //Toast.makeText(this,asd,LENGTH_LONG).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.view_logs, menu);
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
