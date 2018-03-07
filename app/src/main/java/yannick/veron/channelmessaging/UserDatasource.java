package yannick.veron.channelmessaging;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import yannick.veron.channelmessaging.model.Friend;

/**
 * Created by verony on 07/03/2018.
 */
public class UserDatasource {
    //Database fields
    private SQLiteDatabase database;
    private FriendsDB dbHelper;
    private String[]allColumns = {FriendsDB.KEY_ID,FriendsDB.KEY_NAME, FriendsDB.KEY_IMG};
    public UserDatasource(Context context)
    {
        dbHelper = new FriendsDB(context);
    }
    public
    void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }
    public
    void close() {
        dbHelper.close();
    }

    public Friend createFriend(String nom, String url) {
        ContentValues values = new ContentValues();
        values.put(FriendsDB.KEY_NAME, nom);
        values.put(FriendsDB.KEY_IMG,url);
        UUID newID = UUID.randomUUID();
        values.put(FriendsDB.KEY_ID, newID.toString());
        database.insert(FriendsDB.FRIEND_TABLE_NAME,null,values);
        Cursor cursor = database.query(FriendsDB.FRIEND_TABLE_NAME, allColumns,FriendsDB.KEY_ID + "=\"" + newID.toString ()+"\"", null, null, null, null);

        cursor.moveToFirst();
        Friend newFriend = cursorToFriend(cursor);
        cursor.close();
        return newFriend;
    }

    public List<Friend> getAllFriends() {
        List <Friend> lesFriends = new ArrayList<Friend>();
        Cursor cursor = database.query(FriendsDB.FRIEND_TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Friend unFriend = cursorToFriend(cursor);
            lesFriends.add(unFriend);
            cursor.moveToNext();
        }
        //make sure to close the cursor
        cursor.close();
        return lesFriends;
    }
    private Friend cursorToFriend(Cursor cursor)
    {
        Friend comment = new Friend();
        String result = cursor.getString(0);
        comment.setId(UUID.fromString(result));
        comment.setUsername(cursor.getString(1));
        comment.setImgUrl(cursor.getString(2));
        return comment;
    }

    public void deleteFriend(Friend unFriend) {
        UUID id = unFriend.getId();
        database.delete(FriendsDB.FRIEND_TABLE_NAME, FriendsDB.KEY_ID + " =\"" + id.toString ()+"\"", null);
    }
}
