package automate.capstone.feeder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Settings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ToggleButton tbNotif;
    Button btnSubmitSetting;
    SeekBar sbNotif;
    TextView tvPercentage;

    //variables to be passed
    Boolean notif , user_notif, sys_notif;
    int container_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sbNotif = (SeekBar) findViewById(R.id.sb_notif);
        tvPercentage = (TextView) findViewById(R.id.tv_percentage_container);
        sbNotif.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //checks the amount of container (ranging from 0-10)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("ADebugTag", Integer.toString(progress));
                container_progress = progress;
                tvPercentage.setText(container_progress + "0%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tbNotif = (ToggleButton) findViewById(R.id.toggle_notifsms);
        tbNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //  enable/disable notification
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notif = true;
                    Log.d("ADebugTag", notif.toString());
                } else {
                    notif = false;
                    Log.d("ADebugTag", notif.toString());
                }
            }
        });

        btnSubmitSetting = (Button) findViewById(R.id.btn_submit_settings);
    }

    public void checkBoxNotif(View view) {
        //checks the activity between two checkboxes
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.cb_sysnotif:
                if (checked){
                    sys_notif = true;
                    Log.d("ADebugTag", "System Notif = " + sys_notif);
                }
                else{
                    sys_notif = false;
                    Log.d("ADebugTag", "System Notif = " + sys_notif);
                }
                break;
            case R.id.cb_usernotif:
                if (checked){
                    user_notif = true;
                    Log.d("ADebugTag", "User Notif = " + user_notif);
                }
                else{
                    user_notif = false;
                    Log.d("ADebugTag", "User Notif = " + user_notif);
                }
                break;
        }
    }

    public void saveConfig(View view) {
        //do process when submitting config to RPI
        Toast.makeText(this,"Saved!",Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.settings, menu);
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
