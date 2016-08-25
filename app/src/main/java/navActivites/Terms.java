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
public class Terms extends Activity{

    TextView text_term;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        text_term = (TextView) findViewById(R.id.text_terms);
        text_term.setText("Conditions of Use\n" +
                "\n" +
                "Welcome to our online store! MYCOMPANY and its associates provide their services to you subject to the following conditions. If you visit or shop within this website, you accept these conditions. Please read them carefully. \u200B\n" +
                "PRIVACY\n" +
                "\n" +
                "Please review our Privacy Notice, which also governs your visit to our website, to understand our practices.\n" +
                "ELECTRONIC COMMUNICATIONS\n" +
                "\n" +
                "When you visit MYCOMPANY or send e-mails to us, you are communicating with us electronically. You consent to receive communications from us electronically. We will communicate with you by e-mail or by posting notices on this site. You agree that all agreements, notices, disclosures and other communications that we provide to you electronically satisfy any legal requirement that such communications be in writing.\n" +
                "COPYRIGHT\n" +
                "\n" +
                "All content included on this site, such as text, graphics, logos, button icons, images, audio clips, digital downloads, data compilations, and software, is the property of MYCOMPANY or its content suppliers and protected by international copyright laws. The compilation of all content on this site is the exclusive property of MYCOMPANY, with copyright authorship for this collection by MYCOMPANY, and protected by international copyright laws.\n" +
                "TRADE MARKS\n" +
                "\n" +
                "MYCOMPANYs trademarks and trade dress may not be used in connection with any product or service that is not MYCOMPANYs, in any manner that is likely to cause confusion among customers, or in any manner that disparages or discredits MYCOMPANY. All other trademarks not owned by MYCOMPANY or its subsidiaries that appear on this site are the property of their respective owners, who may or may not be affiliated with, connected to, or sponsored by MYCOMPANY or its subsidiaries.");
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
