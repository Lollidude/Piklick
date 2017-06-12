package boredomskingdom.piklick.data;

import android.provider.BaseColumns;

public class UpgradesContract {

    public static final class UpgradesEntry implements BaseColumns{

        public static final String TABLE_NAME = "upgrades";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_COST = "cost";
        public static final String COLUMN_LEVEL = "level";
    }

}
