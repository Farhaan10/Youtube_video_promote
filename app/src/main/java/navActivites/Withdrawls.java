package navActivites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.farhaan.youtube_app.R;

import java.util.ArrayList;

/**
 * Created by Farhaan on 08-08-2016.
 */
public class Withdrawls extends Activity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> notify_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawls);
        listView = (ListView) findViewById(R.id.list_withdrawls);
        notify_list.add("Withdrawl - 1");
        notify_list.add("Withdrawl - 2");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notify_list);
        listView.setAdapter(arrayAdapter);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
