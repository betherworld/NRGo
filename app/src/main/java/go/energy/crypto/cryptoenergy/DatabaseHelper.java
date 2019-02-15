package go.energy.crypto.cryptoenergy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import go.energy.crypto.cryptoenergy.contracts.ItemFactory;

public class DatabaseHelper  extends SQLiteOpenHelper{
    private String loginFile;
    private Context c;
    HashMap<String, String> mapElemToCol = new HashMap<String, String>();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_WALLET_ADDRESS = "user_wallet_address";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String COLUMN_USER_DROP_COUNT = "user_drop_count";
    private static final String COLUMN_USER_LEAF_COUNT = "user_leaf_count";
    private static final String COLUMN_USER_FIRE_COUNT = "user_fire_count";
    private static final String COLUMN_USER_AIR_COUNT = "user_air_count";
    private static final String COLUMN_USER_ELECTRICITY_COUNT = "user_electricity_count";
    private static final String COLUMN_USER_SPECIAL_COUNT = "user_special_count";

    public static final String USER_ADDRESS = "0x16663Fe2aB68e1434A472470a872421bDa4dEaA3";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_WALLET_ADDRESS + " TEXT,"
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

    //////////////////////////////////////////////////
    private static class CreateItem extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String userId = (String) objects[0];
            long dnaWater = (long) objects[1];
            long dnaElectricity = (long) objects[2];
            long dnaFire = (long) objects[3];
            long dnaLeaf = (long) objects[4];
            long dnaAir = (long) objects[5];
            long dnaSpecial = (long) objects[6];

            return createItem(userId, dnaWater, dnaElectricity,dnaFire,dnaLeaf,dnaAir,dnaSpecial);
        }
        private Element createItem(String userId, long dnaWater,long dnaElectricity, long dnaFire,long dnaLeaf,long dnaAir,long dnaSpecial) {
            ItemFactory itemFactory = null;
            try {
                itemFactory = ItemFactoryManager.getItemFactory();
                itemFactory.createItem(userId,
                        BigInteger.valueOf(dnaWater),
                        BigInteger.valueOf(dnaElectricity),
                        BigInteger.valueOf(dnaFire),
                        BigInteger.valueOf(dnaLeaf),
                        BigInteger.valueOf(dnaAir),
                        BigInteger.valueOf(dnaSpecial)
                ).send();
//                System.out.println(itemFactory.getItems(userId));
                return new Element(
                        (int) dnaWater,
                        (int) dnaElectricity,
                        (int) dnaFire,
                        (int) dnaLeaf,
                        (int) dnaAir,
                        (int) dnaSpecial
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private static class GetItem extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String userId = (String) objects[0];
            return getItem(userId);
        }
        private Element getItem(String userId){
            ItemFactory itemFactory = null;
            try {
                itemFactory = ItemFactoryManager.getItemFactory();
                Tuple6<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> item = itemFactory.getItems(userId).send();
                return new Element(
                        item.getValue1().intValue(),
                        item.getValue2().intValue(),
                        item.getValue3().intValue(),
                        item.getValue4().intValue(),
                        item.getValue5().intValue(),
                        item.getValue6().intValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private static class UpdateItem extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            ElementEnum elementEnum = (ElementEnum) objects[0];
            String userId = (String) objects[1];
            long newValue = (long) objects[2];

            updateItem(elementEnum, userId, newValue);
            return null;
        }
        private void updateItem(ElementEnum elementEnum, String userId, long newValue){
            ItemFactory itemFactory = null;
            try {
                itemFactory = ItemFactoryManager.getItemFactory();
                Element element = DatabaseHelper.getItem(userId);
                switch (elementEnum){
                    case WATER:
                        itemFactory.updateWater(userId, BigInteger.valueOf(element.getDnaWater() + newValue)).send();
                        break;
                    case ELECTRICITY:
                        itemFactory.updateElectricity(userId, BigInteger.valueOf(element.getDnaElectricity() + newValue)).send();
                        break;
                    case FIRE:
                        itemFactory.updateFire(userId, BigInteger.valueOf(element.getDnaFire() + newValue)).send();
                        break;
                    case LEAF:
                        itemFactory.updateLeaf(userId, BigInteger.valueOf(element.getDnaLeaf() + newValue)).send();
                        break;
                    case AIR:
                        itemFactory.updateAir(userId, BigInteger.valueOf(element.getDnaAir() + newValue)).send();
                        break;
                    case SPECIAL:
                        itemFactory.updateSpecial(userId, BigInteger.valueOf(element.getDnaSpecial() + newValue)).send();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void createItem(String userId, long dnaWater,long dnaElectricity,long dnaFire,long dnaLeaf,long dnaAir,long dnaSpecial) {
        Object[] objs = new Object[7];
//        objs[0] = "0x16663Fe2aB68e1434A472470a872421bDa4dEaA3";
        objs[0] = userId;
        objs[1] = dnaWater;
        objs[2] = dnaElectricity;
        objs[3] = dnaFire;
        objs[4] = dnaLeaf;
        objs[5] = dnaAir;
        objs[6] = dnaSpecial;
        new CreateItem().execute(objs);
    }
    public static Element getItem(String userId) {
        Object[] objs = new Object[1];
        objs[0] = userId;
        Element element = null;
        try {
            element = (Element) new GetItem().execute(objs).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return element;
    }
    public static void updateItem(ElementEnum elementEnum, String userId, long newDna){
        Object[] objs = new Object[3];
        objs[0]=elementEnum;
        objs[1]=userId;
        objs[2]=newDna;
        new UpdateItem().execute(objs);
    }
//    public int getPoints(Element element){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        String selection = COLUMN_USER_EMAIL + " = ?";
//        /*String[] selectionArgs = { 0 };
//
//        Cursor cursor = db.query(TABLE_USER,
//                columns,
//                selection,
//                selectionArgs,
//                null,
//                null,
//                null);
//        int cursorCount = cursor.getCount();
//        cursor.close();*/
//        db.close();
//
//        return 0;
//    }

//    public void updatePoints(Element element){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        String colName = mapElemToCol.get(element.getName());
//        if(!colName.isEmpty()) {
//            values.put(colName, element.getCoins());
//
//            db.insert(TABLE_USER, null, values);
//            db.close();
//        }
//    }
}
