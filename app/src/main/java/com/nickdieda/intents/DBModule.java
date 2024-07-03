package com.nickdieda.intents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBModule extends SQLiteOpenHelper {


    public DBModule(Context context) {
        super(context, "helb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table loans(" +
                "name TEXT," +
                "institution text," +
                "regno text primary key," +
                "loanAmount integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop table if exists loans");

    }
public boolean insertData(String name,String inst,String regno,int loanAmount) {
    SQLiteDatabase sdb = this.getWritableDatabase();
    ContentValues data = new ContentValues();
    data.put("name", name);
    data.put("institution", inst);
    data.put("regno", regno);
    data.put("loanAmount", loanAmount);

    long response = sdb.insert("loans", null, data);
    if (response == -1) {
        return false;
    } else {
        return true;
    }


}





public boolean deleteData(String regno) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from loans where regno=?", new String[]{regno});
        if (cursor.getCount() > 0) {
            long results=db.delete("loans","regno=?",new String[]{regno});
            if(results==-1){
                return false;
            }
            else{
                return true;
            }
        }else {
            return false;
        }


        //String whereClause = "regno = ?";
     //   String[] whereArgs = {regno};
      //  int rowsAffected = db.delete("loans", whereClause, whereArgs);
     //   db.close();
     //   return rowsAffected > 0;
    }


public boolean updateData(String name,String inst,String regno,int loanAmount) {
    SQLiteDatabase sdb = this.getWritableDatabase();
    ContentValues updated = new ContentValues();
    updated.put("name", name);
    updated.put("institution", inst);

    updated.put("loanAmount", loanAmount);

     long results =sdb.update("loans", updated, "regno=?", new String[]{regno});
    if(results==-1){
    return false;
}
else{
    return true;
}
}
public Cursor viewData(String regno){
    SQLiteDatabase sdb = this.getWritableDatabase();

    Cursor cursor = sdb.rawQuery("select * from loans where regno=?", new String[]{regno});
    return cursor;
}

}
