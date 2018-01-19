package com.example.louis.weinschmeckeroffenburg.Datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.louis.weinschmeckeroffenburg.Datenbank.Item.Item;

import java.util.ArrayList;
import java.util.List;

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
                KEY_FAVORIT,




                };
    }
//hier werden alle Daten eingepflegt

    private ArrayList<Item> createWineData() {
        ArrayList<Item> tmpWineList = new ArrayList<>();

        Item wine0 = new Item("4003104002617",
                "Collection Oberkirch Spätburgunder",
                "2015",
                "Baden",
                "8,49 €",
                "lieblich",
                "Rotwein",
                "Edeka",
                "Zu Wildgerichten, Rinderbraten, Fondue (Fleisch und Käse), Käse",
                "Ein samtiger Wein zum Genießen mit fülligem Bukett und einem feinen Duft von Waldbeeren.",

                "wine_0",
                0);

        Item wine1 = new Item("4005758892262",
                "Bischoffinger Tradition Spätburgunder",
                "2016",
                "Baden",
                "4,49 €",
                "trocken",
                "Rotwein",
                "Edeka",
                "Fruchiger und ausgewogener Wein. Aromen von reifen Kirschen und Schokolade, feinherb, klares dichtes Rot",
                "Fruchiger und ausgewogener Wein. Aromen von reifen Kirschen und Schokolade, feinherb, klares dichtes Rot.",
                "wine_1",
                0);
        Item wine2 = new Item("4036505047918",
                "Blanc de Blanc",
                "2016",
                "Baden",
                "7.49 €",
                "trocken",
                "Weißwein",
                "Edeka",
                "Serviervorschlag",
                "Der Cuvée begeistert durch seine spritzige, frische Art sowie durch Aromen von Weinbergpfirsich. Die Kombination mit dem Sauvignon Blanc überzeugt durch exotische Früchte wie Mango und Passionsfrucht.",
                "wine_2",
                0);
        Item wine3 = new Item("4005460592269",
                "Bötzinger Chardonnay",
                "2016",
                "Baden",
                "5,99 €",
                "trocken",
                "Weißwein",
                "Edeka",
                "Serviervorschlag",
                "Vielversprechendes Aroma nach reifen Äpfeln, etwas Maracuja, dazu röstige Note bei hellem Gelb. Begleitet von leichten Nusstönen und einer eleganten Säure.",
                "wine_3",
                0);
        Item wine4 = new Item("4007474862529",
                "Charakterköpfe 53 Region Affentaler",
                "2015",
                "Baden",
                "6,99 €",
                "lieblich",
                "Rotwein",
                "Edeka",
                "Serviervorschlag",
                "Ein samtiger Wein zum Genießen mit fülligem Bukett und einem feinen Duft von Waldbeeren.",
                "wine_4",
                0);
        Item wine5 = new Item("4007474861973",
                "Klassiker Baden Weißburgunder",
                "2015",
                "Baden",
                "4,49 €",
                "trocken",
                "Weisswein",
                "Edeka",
                "Serviervorschlag",
                "In der Nase feine Blütenaromen und Birne. Ein fruchtiger Wein, im Geschmack viel Rasse und Frucht mit einem harmonischen Abgang.",
                "wine_5",
                0);
        Item wine6 = new Item("4011441202560",
                "Durbacher Plauelrain Klingelberger",
                "2015",
                "Baden",
                "7,99 €",
                "süß",
                "Weißwein",
                "Edeka",
                "Serviervorschlag",
                "Eine vollmundige Spätlese mit reifem Fruchtspiel und schöner Mineralität. Vollreife Ananas- und Pfirsicharomen in der Nase, am Gaumen eher beerig. Saftig und kräftig garantiert dieser Wein ein intensives Geschmackserlebnis.",
                "wine_6",
                0);
        Item wine7 = new Item("4007474860419",
                "Edle Edition Riesling",
                "2012",
                "Baden",
                "15,99 €",
                "trocken",
                "Weißwein",
                "Edeka",
                "Serviervorschlag",
                "Ein fruchtiger Weißwein mit rassiger Säure und feiner Pfirsichnote.",
                "wine_7",
                0);

        Item wine8 = new Item("4002140050859",
                "Oberrotweiler Spätburgunder",
                "2006",
                "Baden",
                "24,99 €",
                "süß",
                "Rotwein",
                "Edeka",
                "Serviervorschlag",
                "Tiefdunkler Rotwein mit dichtem, komplexen Geschmack mit sanften Gerbstoffen.",
                "wine_8",
                0);


        Item wine9 = new Item("4002140975220",
                "Oberrotweiler Rosé Spätburgunder",
                "2016",
                "Baden",
                "4,79 €",
                "trocken",
                "Rosé",
                "Edeka",
                "Serviervorschlag",
                "Schöne Fruchtreife in Kombination mit einer fruchtigen Art, lang anhaltend im Abgang.",
                "wine_9",
                0);


        Item wine10 = new Item("4027295080254",
                "Rammersweier Tradition Spätburgunder",
                "2014",
                "Baden",
                "6,49 €",
                "trocken",
                "Rotwein",
                "Edeka",
                "Serviervorschlag",
                "Rubinroter Wein mit Aromen von Kirsche, Mocca und schwarzem Pfeffer; feste Struktur, zurückhaltend in der Säure und intensiv im Geschmack.",
                "wine_10",
                0);

        Item wine11 = new Item("4005848222610",
                "Schliengener Sonnenstück Gutedel",
                "2015",
                "Baden",
                "4,49 €",
                "trocken",
                "Weißwein",
                "Edeka",
                "Serviervorschlag",
                "Empfehlungs als Frühlings- und Sommerwein mit feinen ausgeprägten Frucharomen. Belebender, anregender und frischer Wein.",
                "wine_11",
                0);

        Item wine12 = new Item("4027295080254",
                "Spätburgunder",
                "2014",
                "Baden",
                "6,49 €",
                "trocken",
                "Rotwein",
                "Edeka",
                "Serviervorschlag",
                "Der Duft dieses Rotweins erinnert an Aromen von Kirschen und Brombeeren, er hat eine niedrige Säure und sanften Gerbstoffen.",
                "wine_12",
                0);

        Item wine13 = new Item("4012868101511",
                "Waldulmer Pfarrberg Spätburgunder",
                "2015",
                "Baden",
                "9,49 €",
                "halbtrocken",
                "Rotwein",
                "Edeka",
                "Serviervorschlag",
                "Eleganter Duft nach reifen Kirschen, beerengelee mit einem Hauch Bitterschokolade und schwarzem Tee. Kräftiger Spätburgunder.",
                "wine_13",
                0);

        Item wine14 = new Item("4005371012214",
                "Winzerkeller Auggener Schäf Gutedel",
                "2016",
                "Baden",
                "3,79 €",
                "trocken",
                "Weißwein",
                "Edeka",
                "Serviervorschlag",
                "Traditionelle Markgräfler Rebsorte mit dezentem Bukett und milder Säure. Feinfruchtig, leicht und sehr ausgewogen bei strohgelber Farbe mit grünen Reflexen.",
                "wine_14",
                0);

        Item wine15 = new Item("4003154602225",
                "Rivaner trocken",
                "2016",
                "Baden",
                "4,20 €",
                "trocken",
                "Weißwein",
                "Edeka",
                "Serviervorschlag",
                "Traditionelle Markgräfler Rebsorte mit dezentem Bukett und milder Säure. Feinfruchtig, leicht und sehr ausgewogen bei strohgelber Farbe mit grünen Reflexen.",
                "wine_15",
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
        tmpWineList.add(wine8);
        tmpWineList.add(wine9);
        tmpWineList.add(wine10);
        tmpWineList.add(wine11);
        tmpWineList.add(wine12);
        tmpWineList.add(wine13);
        tmpWineList.add(wine14);
        tmpWineList.add(wine15);

        // Insert it to database
        insertWine(wine0);
        insertWine(wine1);
        insertWine(wine2);
        insertWine(wine3);
        insertWine(wine4);
        insertWine(wine5);
        insertWine(wine6);
        insertWine(wine7);
        insertWine(wine8);
        insertWine(wine9);
        insertWine(wine10);
        insertWine(wine11);
        insertWine(wine12);
        insertWine(wine13);
        insertWine(wine14);
        insertWine(wine15);

        return tmpWineList;
    }

}
