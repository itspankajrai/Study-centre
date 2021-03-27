package com.Rai.studycenter.caching;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.Rai.studycenter.models.Notesmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final  int DB_Version=1;
    private static final String
            DB_Name="studycenter",
            DB_Table_Note="notes",
            DB_Table_Note_Id="note_id",
            DB_Table_Notes="note_name",
            DB_Table_Desc="notes_desc";
            SQLiteDatabase db;
    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_Version);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DB_Table_Note + "("
                + DB_Table_Note_Id + " INTEGER  PRIMARY KEY AUTOINCREMENT ," + DB_Table_Notes + " TEXT,"
                + DB_Table_Desc + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_Table_Note);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    public void insertNewNote(String title, String desc){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_Table_Notes, title); // Contact Name
        values.put(DB_Table_Desc, desc); // Contact Phone
        db.insert(DB_Table_Note,null,values);
        db.close();
    }
    public List<Notesmodel> getNotes(){
        List<Notesmodel> notesList= new ArrayList<Notesmodel>();
        String Query= "SELECT * FROM "+ DB_Table_Note;
        db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(Query,null);
        if(cursor.moveToFirst()){
            do{
                Notesmodel notesmodel=new Notesmodel();
                notesmodel.setId(Integer.parseInt(cursor.getString(0)));
                notesmodel.setName(cursor.getString(1));
                notesmodel.setDesc(cursor.getString(2));
                notesList.add(notesmodel);
            }
            while (cursor.moveToNext());
        }
     return notesList;
    }
    // Deleting single contact
    public void deleteNote(Notesmodel notesmodel) {
        db = this.getWritableDatabase();
        db.delete(DB_Table_Note, DB_Table_Note_Id + " = ?",
                new String[] { String.valueOf(notesmodel.getId()) });
        db.close();
    }
    public Notesmodel getNote(int id){
        db=this.getReadableDatabase();

        Cursor cursor = db.query(DB_Table_Note, new String[] { DB_Table_Note_Id,
                        DB_Table_Notes, DB_Table_Desc }, DB_Table_Note_Id + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Notesmodel note = new Notesmodel(cursor.getString(1),
                cursor.getString(2), Integer.parseInt(cursor.getString(0)));
        // return contact
        return note;
    }

    public int updateNote(Notesmodel notesmodel){

        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DB_Table_Notes,notesmodel.getName());
        contentValues.put(DB_Table_Desc,notesmodel.getDesc());
        return db.update(DB_Table_Note,contentValues,DB_Table_Note_Id+"=?",new String[]{String.valueOf(notesmodel.getId())});
    }
}
