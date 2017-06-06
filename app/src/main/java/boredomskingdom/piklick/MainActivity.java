package boredomskingdom.piklick;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    int money;
    int upgrade;
    TextView mDisplayCoins;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        //getting stats, setting default values if non-existent
        money = sharedPref.getInt("money", 0);
        upgrade = sharedPref.getInt("upgrade", 1);
        mDisplayCoins = (TextView) findViewById(R.id.display_money);
        mDisplayCoins.setText(String.format(Locale.US, "%d", money));
        ImageView tapButton = (ImageView) findViewById(R.id.tap_Button);
        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money+=upgrade;
                mDisplayCoins.setText(String.format(Locale.US, "%d", money));
            }
        });


    }

    public void save() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("money", money);
        editor.putInt("upgrade", upgrade);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }
}
