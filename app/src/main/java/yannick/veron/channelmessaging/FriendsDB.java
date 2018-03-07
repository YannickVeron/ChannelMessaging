package yannick.veron.channelmessaging;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by verony on 07/03/2018.
 */
public class FriendsDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MyDB.db";
    public static final String FRIEND_TABLE_NAME = "Friend";
    public static final String KEY_ID = "userID";
    public static final String KEY_NAME = "username";
    public static final String KEY_IMG = "imageUrl";

    private static final String FRIEND_TABLE_CREATE = "CREATE TABLE " + FRIEND_TABLE_NAME + " (" + KEY_ID + " INTEGER, " + KEY_NAME + " TEXT, " + KEY_IMG + " TEXT);";


    public FriendsDB(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/"+DATABASE_NAME, null , DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(FRIEND_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " +FRIEND_TABLE_NAME);
        onCreate(db);
    }
}

