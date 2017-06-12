package boredomskingdom.piklick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import boredomskingdom.piklick.data.UpgradesDatabase;


public class ShopActivity extends AppCompatActivity {

    enum mapIDtoUpgrade{
        tapUpgrade;
    }

    int mMoney;
    int tempMoney;
    Intent returnIntent;
    GridView mShopCategories;
    Toolbar mToolbar;
    SharedPreferences sharedPreferences;
    int[] fakeData = {
            100, 200, 300, 400, 500, 600
    };
    String[] moreFakeData = {
            "Background change: 100",
            "Something else: 200",
            "I don't fucking know: 300",
            "smd lol: 400",
            "fuck u: 500",
            "Still nothing lol: 600"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        SQLiteDatabase mDb;
        Intent intent = getIntent();
        mMoney = intent.getIntExtra("money", 0);
        tempMoney = mMoney;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int bgColor = sharedPreferences.getInt("bgColor", Color.parseColor("#FFFFFF"));
        findViewById(R.id.rl_shop).setBackgroundColor(bgColor);

        final TextView tbBalance = (TextView) findViewById(R.id.toolbar_tv);
        tbBalance.setText(String.format(Locale.US, getString(R.string.balance), mMoney));

        UpgradesDatabase upgradesDB = new UpgradesDatabase(getApplicationContext());
        mDb = upgradesDB.getWritableDatabase();

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
                            notEnough(cost);
                            break;
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        int bgColor = shopPurchase.changeBackground(cost);
                        editor.putInt("bgColor", bgColor);
                        editor.apply();
                        mMoney -= cost;
                        findViewById(R.id.rl_shop).setBackgroundColor(bgColor);
                        break;
                    case 1:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 2:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 3:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 4:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                    case 5:
                        cost = fakeData[position];
                        if (mMoney < cost) {
                            notEnough(cost);
                            break;
                        }
                        mMoney -= cost;
                        break;
                }
                tbBalance.setText(String.format(Locale.US, getString(R.string.balance), mMoney));
                returnIntent.putExtra("money", mMoney);
                setResult(Activity.RESULT_OK, returnIntent);
            }
        });

    }

    class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int[] mImages = {R.drawable.temp_shop_icon, R.drawable.temp_shop_icon,
                R.drawable.temp_shop_icon, R.drawable.temp_shop_icon, R.drawable.temp_shop_icon,
                R.drawable.temp_shop_icon};

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View myView;

            LayoutInflater inflater = LayoutInflater.from(ShopActivity.this);
            myView = inflater.inflate(R.layout.gv_base, null);
            ImageView img = (ImageView) myView.findViewById(R.id.gv_image);
            TextView txt = (TextView) myView.findViewById(R.id.gv_text);

            Bitmap bitmap = BitmapFactory.decodeResource(ShopActivity.this.getResources(),
                    mImages[position]);
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 250, 250, true);

            img.setImageBitmap(bitmap2);

            txt.setText(moreFakeData[position]);

            return myView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        setResult(
                tempMoney > mMoney ? RESULT_OK : RESULT_CANCELED,
                returnIntent
        );
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
            setResult(
                    tempMoney > mMoney ? RESULT_OK : RESULT_CANCELED,
                    returnIntent
            );
            finish();
        } else {
            Toast.makeText(ShopActivity.this, "Something broke", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    void notEnough(int cost) {
        Toast.makeText(ShopActivity.this, String.format(
                Locale.US, "You need %d for this", cost),
                Toast.LENGTH_SHORT).show();
    }

}


