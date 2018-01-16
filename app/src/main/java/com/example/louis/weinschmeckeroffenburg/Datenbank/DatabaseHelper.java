package com.example.louis.weinschmeckeroffenburg.Datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;

import java.util.ArrayList;

/**
 * Created by louis on 21.12.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static String DB_NAME = "wineDatabase";
    private static int DB_VERSION = 1;

    // Table name
    private static final String WINE_TABLE = "WEIN";

    // Wine Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_JAHRGANG = "JAHRGANG";
    private static final String KEY_LAND = "LAND";
    private static final String KEY_PREIS = "PREIS";
    private static final String KEY_GESCHMACK = "GESCHMACK";
    private static final String KEY_ART = "ART";
    private static final String KEY_LADEN = "LADEN";
    private static final String KEY_SERVIERVORSCHLAG = "SERVIERVORSCHLAG";
    private static final String KEY_CONTENT = "CONTENT";
    private static final String KEY_IMG = "IMG";
    private static final String KEY_FAVORIT = "FAVORIT";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_WINE_TABLE = "CREATE TABLE " + WINE_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_JAHRGANG + " TEXT,"
                + KEY_LAND + " TEXT,"
                + KEY_PREIS + " TEXT,"
                + KEY_GESCHMACK + " TEXT,"
                + KEY_ART + " TEXT,"
                + KEY_LADEN + " TEXT,"
                + KEY_SERVIERVORSCHLAG + " TEXT,"
                + KEY_CONTENT + " TEXT,"
                + KEY_IMG + " TEXT,"
                + KEY_FAVORIT + " INTEGER" + ")";

        sqLiteDatabase.execSQL(CREATE_WINE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WINE_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void insertWine(Item wine) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Inserting Row
        db.insert(WINE_TABLE, null, getContentValuesFromWine(wine));
        db.close();
    }


    public void updateWine(Item wine) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValuesFromWine(wine);

        // updating row
        db.update(WINE_TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(wine.getId())});
    }


    public Item getWine(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + WINE_TABLE + " WHERE " + KEY_ID + " =?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{id});

        if (cursor != null)
            cursor.moveToFirst();

        return getWineFromCursor(cursor);
    }

    public void removeWine(Item wine) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WINE_TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(wine.getId())});
        db.close();
    }

    public ArrayList<Item> getAllWineFromDB() {
        ArrayList<Item> wineList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + WINE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item wine = getWineFromCursor(cursor);
                wineList.add(wine);
            } while (cursor.moveToNext());
        }

        if(wineList.isEmpty()) {
            wineList = createWineData();
        }

        cursor.close();

        return wineList;
    }

    public ArrayList<Item> getAllFavouriteWineFromDB() {
        ArrayList<Item> wineList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + WINE_TABLE + " WHERE FAVORIT == 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Item wine = getWineFromCursor(cursor);
                wineList.add(wine);
            } while (cursor.moveToNext());
        }

        return wineList;
    }


    // Helper Methods

    @NonNull
    private ContentValues getContentValuesFromWine(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, item.getId());
        contentValues.put(KEY_NAME, item.getWeinname());
        contentValues.put(KEY_JAHRGANG, item.getJahrgang());
        contentValues.put(KEY_LAND, item.getLand());
        contentValues.put(KEY_PREIS, item.getPreis());
        contentValues.put(KEY_GESCHMACK, item.getGeschmack());
        contentValues.put(KEY_ART, item.getArt());
        contentValues.put(KEY_LADEN, item.getLaden());
        contentValues.put(KEY_SERVIERVORSCHLAG, item.getServierVorschlag());
        contentValues.put(KEY_CONTENT, item.getContent());
        contentValues.put(KEY_IMG, item.getImg());
        contentValues.put(KEY_FAVORIT, item.getIsFavourite());
        return contentValues;
    }

    @NonNull
    private Item getWineFromCursor(Cursor cursor) {
        return new Item(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                Integer.parseInt(cursor.getString(11))
        );
    }

    @NonNull
    private String[] getColumns() {
        return new String[]{
                KEY_ID,
                KEY_NAME,
                KEY_JAHRGANG,
                KEY_LAND,
                KEY_PREIS,
                KEY_GESCHMACK,
                KEY_ART,
                KEY_LADEN,
                KEY_SERVIERVORSCHLAG,
                KEY_CONTENT,
                KEY_IMG,
                KEY_FAVORIT};
    }

    private ArrayList<Item> createWineData() {
        ArrayList<Item> tmpWineList = new ArrayList<>();

        Item wine0 = new Item("0",
                "Savignon Blanc",
                "1990",
                "Deutschland",
                "30",
                "trocken",
                "Weisswein",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "wein_1",
                0);

        Item wine1 = new Item("1",
                "Savignon Noir",
                "2015",
                "Frankreich",
                "30.00€",
                "lieblich",
                "Rotwein",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "herz",
                0);
        Item wine2 = new Item("3",
                "Grauburgunder",
                "2010",
                "Deutschland, Baden",
                "6.00€",
                "lieblich",
                "Rosé",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "herz",
                0);
        Item wine3 = new Item("4",
                "Savignon Blanc",
                "1990",
                "Spanien",
                "30",
                "trocken",
                "Weisswein",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "wein_1",
                0);
        Item wine4 = new Item("5",
                "Savignon Blanc",
                "1990",
                "Frankreich",
                "30",
                "lieblich",
                "Rotwein",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "wein_2",
                0);
        Item wine5 = new Item("6",
                "Savignon Blanc",
                "1990",
                "Deutschland",
                "30",
                "halbtrocken",
                "Weisswein",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "wein_3",
                0);
        Item wine6 = new Item("7",
                "Savignon Blanc",
                "1990",
                "Schweiz",
                "30",
                "trocken",
                "Rotwein",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "wein_1",
                0);
        Item wine7 = new Item("42141105",
                "Savignon Blanc",
                "1990",
                "Spanien",
                "30",
                "lieblich",
                "Rosé",
                "Laden",
                "Serviervorschlag",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                "wein_2",
                0);

        // Give back the list
        tmpWineList.add(wine0);
        tmpWineList.add(wine1);
        tmpWineList.add(wine2);
        tmpWineList.add(wine3);
        tmpWineList.add(wine4);
        tmpWineList.add(wine5);
        tmpWineList.add(wine6);
        tmpWineList.add(wine7);

        // Insert it to database
        insertWine(wine0);
        insertWine(wine1);
        insertWine(wine2);
        insertWine(wine3);
        insertWine(wine4);
        insertWine(wine5);
        insertWine(wine6);
        insertWine(wine7);

        return tmpWineList;
    }

}
