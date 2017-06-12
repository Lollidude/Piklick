package boredomskingdom.piklick.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UpgradesDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "upgrades.db";
    private static final int VERSION = 1;

    public UpgradesDatabase(Context c) {
        super(c, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_UPGRADES_TABLE = "CREATE TABLE " + UpgradesContract.UpgradesEntry.TABLE_NAME
                + " (" + UpgradesContract.UpgradesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UpgradesContract.UpgradesEntry.COLUMN_LEVEL + " INTEGER NOT NULL,"
                + UpgradesContract.UpgradesEntry.COLUMN_VALUE + " INTEGER NOT NULL,"
                + UpgradesContract.UpgradesEntry.COLUMN_COST + " INTEGER N0T NULL);";
        db.execSQL(CREATE_UPGRADES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
