package co.edu.aulamatriz.reviewapplication.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import co.edu.aulamatriz.reviewapplication.databases.DBHelper;

import static co.edu.aulamatriz.reviewapplication.utilities.Constantes.AUTHORITY;
import static co.edu.aulamatriz.reviewapplication.utilities.Constantes.TABLE_NAME;

public class MyContentProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher;
    private static final int DATUM = 1;
    private static final int DATUM_ID = 2;
    private static final int DATUM_INSERT = 3;
    private static final int DATUM_BULK_INSERT = 4;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, DATUM);
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", DATUM_ID);
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/insert", DATUM_INSERT);
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/bulk-insert", DATUM_BULK_INSERT);
    }

    private DBHelper dbHelper;

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        switch (sUriMatcher.match(uri)){
            case DATUM:
                break;
            case DATUM_ID:
                break;
            default:
                break;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs,
                null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = "";
        switch (sUriMatcher.match(uri)) {
            case DATUM_INSERT:
                tableName = TABLE_NAME;
                break;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(tableName, null, values);
        return uri;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        String tableName = "";
        switch (sUriMatcher.match(uri)) {
            case DATUM_BULK_INSERT:
                tableName = TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rows = 0;
        db.beginTransaction();
        try {
            for (ContentValues value : values) {
                db.insert(tableName, null, value);
                rows++;
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return rows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = "";
        switch (sUriMatcher.match(uri)) {
            case DATUM:
                tableName = TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(tableName, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
