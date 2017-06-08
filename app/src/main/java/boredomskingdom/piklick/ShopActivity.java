package boredomskingdom.piklick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ShopActivity extends AppCompatActivity {

    int mMoney;
    int tempMoney;
    Intent returnIntent;
    TextView mBalance;
    GridView mShopCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop);
        Intent intent = getIntent();
        mMoney = intent.getIntExtra("money", 0);
        tempMoney = mMoney;

        mBalance = (TextView) findViewById(R.id.balance);
        String balance = mBalance.getText().toString();
        mBalance.setText(String.format(Locale.US, balance, mMoney));


        mShopCategories = (GridView) findViewById(R.id.shop_categories);
        mShopCategories.setAdapter(new ImageAdapter(this));
        mShopCategories.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopPurchase shopPurchase = new ShopPurchase(ShopActivity.this, mMoney);
                returnIntent = new Intent();
                switch (position) {
                    case 0:
                        shopPurchase.changeBackground();
                        mMoney-=150;
                        break;
                    default:
                        break;
                }
                mBalance.setText(String.format(Locale.US, getString(R.string.balance), mMoney));
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

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(275, 275));
                imageView.setScaleType(ImageView.ScaleType.CENTER);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mImages[position]);
            return imageView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        public int[] mImages = {R.drawable.temp_shop_icon, R.drawable.temp_shop_icon,
                R.drawable.temp_shop_icon, R.drawable.temp_shop_icon, R.drawable.temp_shop_icon,
                R.drawable.temp_shop_icon};
    }

    @Override
    protected void onStop() {
        super.onStop();
        setResult(
                tempMoney > mMoney ? RESULT_OK : RESULT_CANCELED,
                returnIntent
        );
    }
}
