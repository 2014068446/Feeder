package automate.capstone.feeder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse {
    Button btnTest, btnAutomatic, btnManual, btnViewLogs;
    EditText etIP_Address;
    String rpi_ip_address;
    Boolean connection;
    NavigationView navigationView;
    Menu menu_nav;
    String logs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final DatabaseHelper dh = new DatabaseHelper(this);
        connection = false;
        btnAutomatic = (Button) findViewById(R.id.btn_Automatic);
        btnManual = (Button) findViewById(R.id.btn_Manual);
        btnViewLogs = (Button) findViewById(R.id.btn_Logs);

        btnAutomatic.setEnabled(false);
        btnManual.setEnabled(false);
        btnViewLogs.setEnabled(false);

        btnTest = (Button) findViewById(R.id.btn_test_ip_address);
        etIP_Address = (EditText) findViewById(R.id.et_ip_address);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu_nav = navigationView.getMenu();
        menu_nav.setGroupEnabled(R.id.nav_group,false);
        Toast.makeText(this,Store.settings,Toast.LENGTH_LONG).show();
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rpi_ip_address = etIP_Address.getText().toString();
                Store.ip_address=rpi_ip_address;
                DatabaseHelper dh = new DatabaseHelper(HomeActivity.this);
                String type="view logs";
                dh.execute(type);

                    try{
                        Store.logs = dh.get().toString();
                       btnAutomatic.setEnabled(true);
                        btnManual.setEnabled(true);
                        btnViewLogs.setEnabled(true);
                        menu_nav.setGroupEnabled(R.id.nav_group, true);
                        Toast.makeText(HomeActivity.this, "Connection success", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        btnAutomatic.setEnabled(false);
                        btnManual.setEnabled(false);
                        btnViewLogs.setEnabled(false);
                        menu_nav.setGroupEnabled(R.id.nav_group, false);
                        Toast.makeText(HomeActivity.this, "Connection Fail", Toast.LENGTH_SHORT).show();
                    }



            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
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
    public void chooseAutomatic(View view){
        Intent intent = new Intent(HomeActivity.this, AutomaticMode.class);
        startActivity(intent);
    }

    public void chooseManual(View view){
        Intent intent = new Intent(HomeActivity.this, ManualMode.class);
        startActivity(intent);
    }

    public void chooseViewLogs(View view){
        Intent intent = new Intent(HomeActivity.this, ViewLogs.class);
        startActivity(intent);
    }

    public void chooseSettings(View view){
        Intent intent = new Intent(HomeActivity.this, Settings.class);
        startActivity(intent);
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

    @Override
    public void processFinish(String output) {

    }
}
