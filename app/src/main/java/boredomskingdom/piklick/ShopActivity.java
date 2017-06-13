package boredomskingdom.piklick;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import boredomskingdom.piklick.data.UpgradesDatabase;


public class ShopActivity extends AppCompatActivity {

    int mMoney;
    int tempMoney;
    Intent returnIntent;
    GridView mShopCategories;
    Toolbar mToolbar;
    SharedPreferences sharedPrefs;
    int[] fakeData = {
            100, 200, 300, 400, 500, 600
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mMoney = sharedPrefs.getInt("money", 0);
        tempMoney = mMoney;
        final int bgColor = sharedPrefs.getInt("bgColor", Color.parseColor("#FFFFFF"));

        findViewById(R.id.rl_shop).setBackgroundColor(bgColor);

        final TextView tbBalance = (TextView) findViewById(R.id.toolbar_tv);
        tbBalance.setText(String.format(Locale.US, getString(R.string.balance), mMoney));

        final UpgradesDatabase upgradesDB = new UpgradesDatabase(getApplicationContext());
        SQLiteDatabase mDb = upgradesDB.getWritableDatabase();

        mShopCategories = (GridView) findViewById(R.id.shop_categories);
        mShopCategories.setAdapter(new ImageAdapter(this));

        mShopCategories.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopPurchase shopPurchase = new ShopPurchase(ShopActivity.this);
                returnIntent = new Intent();
                int cost;
                switch (position) {
                    case 0:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            shopPurchase.notEnough(cost);
                            break;
                        }
                        SharedPreferences.Editor editor = sharedPrefs.edit();
                        int bgColor = shopPurchase.changeBackground(cost);
                        editor.putInt("bgColor", bgColor);
                        editor.apply();
                        mMoney -= cost;
                        findViewById(R.id.rl_shop).setBackgroundColor(bgColor);
                        break;
                    case 1:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            shopPurchase.notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 2:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            shopPurchase.notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 3:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            shopPurchase.notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 4:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            shopPurchase.notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 5:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            shopPurchase.notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                }
                tbBalance.setText(String.format(Locale.US, getString(R.string.balance), mMoney));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }


    public void save() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("money", mMoney);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.back) {
            save();
            finish();
        } else {
            Toast.makeText(ShopActivity.this, "Something broke", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}


