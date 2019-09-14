package ericmgs.assassinspizza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DBPATH = "data/data/ericmgs.assassinspizza/databases/";
    private static String DBNAME = "database";
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private boolean checkDatabase() {
        SQLiteDatabase db = null;
        try {
            String path = DBPATH + DBNAME;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        } catch (SQLiteException e) {

        }
        return db != null;
    }

    private void createDataBase() throws Exception {
        boolean exists = checkDatabase();
        if (!exists) {
            this.getReadableDatabase();
            try{
                copyDatabase();
            } catch(IOException e) {
                throw new Error("Não foi possível copiar o arquivo");
            }
        }
    }

    private void copyDatabase() throws IOException {
        String dbPath = DBPATH + DBNAME;
        OutputStream dbStream = new FileOutputStream(dbPath);
        InputStream dbInputStream = context.getAssets().open(DBNAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dbInputStream.read(buffer)) > 0) {
            dbStream.write(buffer, 0, length);
        }
        dbInputStream.close();
        dbStream.flush();
        dbStream.close();
    }

    public SQLiteDatabase getDatabase() {
        try {
            createDataBase();
            String path = DBPATH + DBNAME;
            return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            return getWritableDatabase();
        }
    }

    public List<String> getList(String tableName) {
        List <String> labels = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
}
