package boredomskingdom.piklick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    int mMoney;
    int mTapValue;
    int mBgColor;
    TextView mDisplayCoins;
    RelativeLayout mRLMain;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting values

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mMoney = sharedPref.getInt("money", 0);
        mTapValue = sharedPref.getInt("tapValue", 10000);
        mBgColor = sharedPref.getInt("bgColor", Color.parseColor("#FFFFFF"));


        mDisplayCoins = (TextView) findViewById(R.id.display_money);
        mDisplayCoins.setText(String.format(Locale.US, "%d", mMoney));

        mRLMain = (RelativeLayout) findViewById(R.id.rl_main);
        mRLMain.setBackgroundColor(mBgColor);

        ImageView tapButton = (ImageView) findViewById(R.id.tap_Button);
        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoney += mTapValue;
                mDisplayCoins.setText(String.format(Locale.US, "%d", mMoney));
            }
        });

        Button shopButton = (Button) findViewById(R.id.shop_button);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class destination = ShopActivity.class;
                Intent intent = new Intent(context, destination);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMoney = sharedPref.getInt("money", mMoney);
        mDisplayCoins.setText(String.format(Locale.US, "%d", mMoney));
        int bgColor = sharedPref.getInt("bgColor", Color.parseColor("#FFFFFF"));
        mRLMain.setBackgroundColor(bgColor);
    }

    public void save() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("money", mMoney);
        editor.apply();
    }
}
