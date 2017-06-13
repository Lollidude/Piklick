package boredomskingdom.piklick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ImageAdapter extends BaseAdapter {

    private String[] moreFakeData = {
            "Background change: 100",
            "Bigger button: 200",
            "+%d seconds: 300",
            "More diamonds: 400",
            "fuck u: 500",
            "Still nothing lol: 600"
    };

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
        if (convertView == null) {
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            myView = inflater.inflate(R.layout.gv_base, null);
            final ImageView img = (ImageView) myView.findViewById(R.id.gv_image);
            final TextView txt = (TextView) myView.findViewById(R.id.gv_text);

            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                    mImages[position]);
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 250, 250, true);

            img.setImageBitmap(bitmap2);
            txt.setText(moreFakeData[position]);
        } else {
            myView = convertView;
        }

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