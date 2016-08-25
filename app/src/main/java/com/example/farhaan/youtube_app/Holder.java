package com.example.farhaan.youtube_app;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;

import navActivites.Contact_Us;
import navActivites.Invite_Friends;
import navActivites.Notifications;
import navActivites.Terms;
import navActivites.Withdrawls;

/**
 * Created by Farhaan on 09-08-2016.
 */
public class Holder extends AppCompatActivity{

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ViewPager viewPager;
    private LinearLayout navigation_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPlanetTitles = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigation_layout = (LinearLayout) findViewById(R.id.navigation_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mPlanetTitles));
        navigation_layout.bringToFront();
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_video){
            viewPager.setCurrentItem(0);
        }
        if(id == R.id.action_favorite){
            viewPager.setCurrentItem(1);
        }
        if(id == R.id.action_coins){
            viewPager.setCurrentItem(2);
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("Selected position = " + position);
            selectItem(position);
        }
    }

    private void selectItem(int position){
        switch (position){
            case 0:
                Intent intent0 = new Intent(Holder.this, Notifications.class);
                startActivity(intent0);
                break;
            case 1:
                Intent intent1 = new Intent(Holder.this, Withdrawls.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(Holder.this, Terms.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(Holder.this, Invite_Friends.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(Holder.this, Contact_Us.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
    }
}
