package boredomskingdom.piklick;

import android.app.Activity;
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
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    int mMoney;
    int mUpgrade;
    int mBgColor;
    TextView mDisplayCoins;
    RelativeLayout mRLMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mMoney = sharedPref.getInt("money", 0);
        mUpgrade = sharedPref.getInt("upgrade", 1);
        mBgColor = Color.parseColor(sharedPref.getString("bgColor", "#ffffff"));

        mDisplayCoins = (TextView) findViewById(R.id.display_money);
        mDisplayCoins.setText(String.format(Locale.US, "%d", mMoney));

        mRLMain = (RelativeLayout) findViewById(R.id.rl_main);
        mRLMain.setBackgroundColor(mBgColor);

        ImageView tapButton = (ImageView) findViewById(R.id.tap_Button);
        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMoney += mUpgrade;
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
                intent.putExtra("money", mMoney);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        mMoney = data.getIntExtra("money", 0);
    }

    public void save() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("money", mMoney);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDisplayCoins.setText(String.format(Locale.US, "%d", mMoney));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mDisplayCoins.setText(String.format(Locale.US, "%d", mMoney));
    }

    @Override
    protected void onStop() {
        save();
        super.onStop();
    }
}
