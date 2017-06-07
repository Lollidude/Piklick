package boredomskingdom.piklick;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

    int money;
    TextView mBalance;
    GridView mShopCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Intent intent = getIntent();
        money = intent.getIntExtra("money", 0);
        mBalance = (TextView) findViewById(R.id.balance);
        String balance = mBalance.getText().toString();
        mBalance.setText(String.format(Locale.US, balance, money));
        mShopCategories = (GridView) findViewById(R.id.shop_categories);
        mShopCategories.setAdapter(new ImageAdapter(this));
        mShopCategories.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(ShopActivity.this, "Position: " + position, Toast.LENGTH_LONG).show();
                view.setBackgroundColor(Color.parseColor("#ff0000"));
            }
        });
    }
}

class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(85, 85, 85, 85);
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
            R.drawable.temp_shop_icon, R.drawable.temp_shop_icon, R.drawable.temp_shop_icon};
}
