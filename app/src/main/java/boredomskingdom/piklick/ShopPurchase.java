package boredomskingdom.piklick;


import android.content.Context;
import android.widget.Toast;

import java.util.Locale;

public class ShopPurchase{

    int mMoney;

    Context mContext;
    public ShopPurchase(Context c, int money) {
        mContext = c;
        mMoney = money;
    }

    int changeBackground() {
        String upgrade = "background change";
        int cost = 150;
        mMoney -= cost;
        butterToast(upgrade, cost);
        return mMoney;
    }

    public void butterToast(String item, int cost) {
        String toastString = "You bought a " + item + " for %d coins";
        Toast.makeText(mContext, String.format(Locale.US, toastString, cost), Toast.LENGTH_LONG).show();
    }

}
