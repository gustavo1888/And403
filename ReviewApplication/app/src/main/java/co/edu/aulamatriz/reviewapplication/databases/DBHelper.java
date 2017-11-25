package co.edu.aulamatriz.reviewapplication.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

import co.edu.aulamatriz.reviewapplication.utilities.Constantes;

/**
 * Created by prueba on 25/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, Constantes.NAME_DB, null, Constantes.DB_VERSION);
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(Constantes.TABLE_NAME, null, null, null,
                null, null, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + Constantes.TABLE_NAME + " ( " +
                Constantes.COLUMN_ID + " integer PRIMARY KEY not null, " +
                Constantes.COLUMN_SERVER_ID + " integer, " +
                Constantes.COLUMN_JOKE + " text" +
                " )";
        db.execSQL(query);
        Logger.d("Base de datos creada, %s", Constantes.TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXIST " + Constantes.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
}
