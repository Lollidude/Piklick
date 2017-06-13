package boredomskingdom.piklick;


import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class ShopPurchase {

    int mMoney;

    Context mContext;

    public ShopPurchase(Context c) {
        mContext = c;
    }

    int changeBackground(int cost) {
        String upgrade = "background change";
        butterToast(upgrade, cost);
        return randomColor();
    }

    public void butterToast(String item, int cost) {
        String toastString = "You bought a " + item + " for %d coins";
        Toast.makeText(mContext, String.format(Locale.US, toastString, cost), Toast.LENGTH_LONG).show();
    }

    int randomColor() {
        String values = "ABCDEF0123456789";
        char[] tempColor = new char[6];
        String color = "#";
        for (int i = 0; i < 6; i++) {
            tempColor[i] = values.charAt(new Random().nextInt(16));
        }
        for (char x : tempColor)
            color += x;

        return Color.parseColor(color);
    }

    void notEnough(int cost) {
        Toast.makeText(mContext, String.format(
                Locale.US, "You need %d for this", cost),
                Toast.LENGTH_SHORT).show();
    }
}
