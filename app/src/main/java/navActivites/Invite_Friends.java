package navActivites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.farhaan.youtube_app.R;

import java.util.ArrayList;

/**
 * Created by Farhaan on 08-08-2016.
 */
public class Invite_Friends extends Activity{

    TextView head, body;
    ListView medium;
    ArrayAdapter arrayAdapter;
    ArrayList<String> social_media = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        head = (TextView) findViewById(R.id.first_text_invite);
        body = (TextView) findViewById(R.id.second_text_invite);
        medium = (ListView) findViewById(R.id.list_invite);
        head.setText("Invite Friends to join Baymack and get coins");
        body.setText("They get 250 coins and you will too, after they redeem first reward. Share Now!");
        social_media.add("Facebook");
        social_media.add("Email Invite");
        social_media.add("WhatsApp");
        social_media.add("Messages");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, social_media);
        medium.setAdapter(arrayAdapter);
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
