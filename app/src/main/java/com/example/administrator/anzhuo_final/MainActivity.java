package com.example.administrator.anzhuo_final;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout mtabs;
    private  ViewPager mViewpage;
    private FragmentAdapter madapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TextView name1;
   // private List<Fragment> fragments;
   // private List<String>tabs;

    private  Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initData();
        initEvent();
        //Intent intent=getIntent();

        /*String name=intent.getStringExtra("name");

        name1.setText(name);*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // name1=(TextView)findViewById(R.id.name) ;
       // name1.setText("sgzx");
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(resultCode){
            case RESULT_OK:
                super.onActivityResult(requestCode, resultCode, data);
                //接收数据：采用Bundle传值
                Bundle bundle =data.getExtras();
                String name=bundle.getString("name");
                name1.setText(name);
                Log.e("TAG", name);
                break;
            default:
                break;
        }
    }
    private void initEvent() {
        mtabs=(TabLayout)findViewById(R.id.tabs);
        mViewpage=(ViewPager)findViewById(R.id.viewPage) ;
        madapter=new FragmentAdapter(getSupportFragmentManager());
        mViewpage.setAdapter(madapter);
        name1=(TextView)findViewById(R.id.name) ;

        mtabs.setupWithViewPager(mViewpage);
        one = mtabs.getTabAt(0);
        two = mtabs.getTabAt(1);
        three = mtabs.getTabAt(2);
        four = mtabs.getTabAt(3);
    }

    /*private void initData() {
        fragments=new ArrayList<>();
        tabs=new ArrayList<>();
        for(int i=0;i<8;i++)
        {
            fragment=new TabFragment();
            Bundle bd=new Bundle();
            bd.putString("theme","tab"+i);
            fragment.setArguments(bd);
            fragments.add(fragment);
            tabs.add("Tab"+i);
        }

    }*/

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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        else if (id == R.id.nav_login) {
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
