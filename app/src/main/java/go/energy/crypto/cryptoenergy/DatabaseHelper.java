package go.energy.crypto.cryptoenergy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class DatabaseHelper  extends SQLiteOpenHelper{
    private String loginFile;
    private Context c;
    HashMap<String, String> mapElemToCol = new HashMap<String, String>();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String COLUMN_USER_DROP_COUNT = "user_drop_count";
    private static final String COLUMN_USER_LEAF_COUNT = "user_leaf_count";
    private static final String COLUMN_USER_FIRE_COUNT = "user_fire_count";
    private static final String COLUMN_USER_AIR_COUNT = "user_air_count";
    private static final String COLUMN_USER_ELECTRICITY_COUNT = "user_electricity_count";
    private static final String COLUMN_USER_SPECIAL_COUNT = "user_special_count";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_DROP_COUNT + " INTEGER,"
            + COLUMN_USER_LEAF_COUNT + " INTEGER,"
            + COLUMN_USER_FIRE_COUNT + " INTEGER,"
            + COLUMN_USER_AIR_COUNT + " INTEGER,"
            + COLUMN_USER_ELECTRICITY_COUNT + " INTEGER,"
            + COLUMN_USER_SPECIAL_COUNT + " INTEGER"
            + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context, String loginFile, Context c){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.loginFile = loginFile;
        this.c = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        mapElemToCol.put("drop", COLUMN_USER_DROP_COUNT);
        mapElemToCol.put("air", COLUMN_USER_AIR_COUNT);
        mapElemToCol.put("fire", COLUMN_USER_FIRE_COUNT);
        mapElemToCol.put("electricity", COLUMN_USER_ELECTRICITY_COUNT);
        mapElemToCol.put("leaf", COLUMN_USER_LEAF_COUNT);
        mapElemToCol.put("special", COLUMN_USER_SPECIAL_COUNT);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean autoLogin() {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            FileInputStream  in = c.openFileInput(loginFile);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String email = bufferedReader.readLine();
            String pass = bufferedReader.readLine();
            Log.d("LOGIN", "email:" + email + "pass:"+ pass);
            return checkUser(email, pass);
        }
        catch (IOException e) {
            return false;
        }
    }
    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            try {
                FileOutputStream  file = c.openFileOutput(loginFile,c.MODE_PRIVATE);
                file.write((email+"\n" + password + "\n").getBytes());
                file.close();
            }catch( IOException e ){

            }
            return true;
        }
        return false;
    }

    public void updatePoints(Element element){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String colName = mapElemToCol.get(element.getName());
        if(!colName.isEmpty()) {
            values.put(colName, element.getCoins());

            db.insert(TABLE_USER, null, values);
            db.close();
        }
    }
}
