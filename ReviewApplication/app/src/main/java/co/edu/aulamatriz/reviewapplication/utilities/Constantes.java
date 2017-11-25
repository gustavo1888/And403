package co.edu.aulamatriz.reviewapplication.utilities;

import android.net.Uri;

import co.edu.aulamatriz.reviewapplication.providers.MyContentProvider;

/**
 * Created by prueba on 25/11/2017.
 */

public class Constantes {

    public final static String NAME_DB = "JOKE.DB";
    public final static String TABLE_NAME = "table_joke";
    public final static String COLUMN_ID = "_id";
    public final static String COLUMN_SERVER_ID = "id";
    public final static String COLUMN_JOKE = "joke";
    public final static int DB_VERSION = 2;
    public final static String AUTHORITY = "co.example.provider.MyContentProvider";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + TABLE_NAME);
    public static final Uri INSERT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + TABLE_NAME + "/insert");
    public static final Uri BULK_INSERT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + TABLE_NAME + "/bulk-insert");

}
