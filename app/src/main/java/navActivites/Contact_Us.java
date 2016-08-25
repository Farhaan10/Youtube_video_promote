package navActivites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.farhaan.youtube_app.R;

/**
 * Created by Farhaan on 08-08-2016.
 */
public class Contact_Us extends Activity{

    TextView first, number, second, email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        first = (TextView) findViewById(R.id.first_text_contact_us);
        number = (TextView) findViewById(R.id.number_whatsapp);
        second = (TextView) findViewById(R.id.second_text_contact_us);
        email = (TextView) findViewById(R.id.email_contact);
        first.setText("If you have any questions, please message us on WhatsApp to either number below, just tap to write us:");
        number.setText("+91-91482-61948");
        second.setText("You can also email us to:");
        email.setText("support@baymack.com");
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
