package com.example.gestionscolarite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gestionscolarite.model.User;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "gestionscolar.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(iduser INTEGER primary key AUTOINCREMENT,username TEXT , password TEXT)");
        db.execSQL("create Table filliere(idfillere INTEGER primary key AUTOINCREMENT, nomfilliere TEXT)");
        db.execSQL("create Table evaluation(ideva INTEGER primary key AUTOINCREMENT, cneetu INTEGER, id_mod INTEGER, note FLOAT, CONSTRAINT fk_eva FOREIGN KEY(id_mod) REFERENCES module(idmod)," +
                "CONSTRAINT fk_etu FOREIGN KEY(cneetu) REFERENCES etudiants(cneetu))");
        db.execSQL("create Table inscription(idins INTEGER primary key AUTOINCREMENT, niveau TEXT)");
        db.execSQL("create Table module(idmod INTEGER primary key AUTOINCREMENT,nom TEXT)");
        db.execSQL("create Table planning(idplan INTEGER primary key AUTOINCREMENT, idfilliere INTEGER, idmod INTEGER,niveau TEXT," +
                "CONSTRAINT fk_fill FOREIGN KEY(idfilliere) REFERENCES filliere(idfilliere), " +
                "CONSTRAINT fk_mod FOREIGN KEY(idmod) REFERENCES module(idmod))");
        db.execSQL("create Table etudiants(cneetu INTEGER primary key AUTOINCREMENT,nom TEXT , prenom TEXT, idfilliere,CONSTRAINT fk_filliere FOREIGN KEY(idfilliere) REFERENCES filliere(idfilliere))");


        db.execSQL("insert into etudiants VALUES (15,'Sou','Mondal', 1);");
        db.execSQL("insert into etudiants VALUES (16,'test','Ml', 2);");

        db.execSQL("insert into module VALUES (13,'reseau');");
        db.execSQL("insert into module VALUES (22,'OS');");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists filliere");
        db.execSQL("drop Table if exists evaluation");
        db.execSQL("drop Table if exists inscription");
        db.execSQL("drop Table if exists module");
        db.execSQL("drop Table if exists planning");



    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean addFilliere(String nomfilliere ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("nomfilliere",nomfilliere);

        long res = sqLiteDatabase.insert("filliere",null,contentValues);
        sqLiteDatabase.close();
        if(res==-1)
        {
            return false;
        }
        return true;
    }

 //afficher les etudiants

    public Cursor showData1()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "select * from etudiants";
        Cursor res = sqLiteDatabase.rawQuery(query,null);
        return res;
    }


    public boolean addMark(String subjecta,String subjectb,String subjectc,String subjectd){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ideva",subjecta);
        contentValues.put("cneetu",subjectb);
        contentValues.put("id_mod",subjectc);
        contentValues.put("note",subjectd);
        long res = sqLiteDatabase.insert("evaluation",null,contentValues);
        sqLiteDatabase.close();
        if(res==-1)
        {
            return false;
        }
        return true;
    }

    public Cursor showData2()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "select * from evaluation";
        Cursor res = sqLiteDatabase.rawQuery(query,null);
        return res;
    }

}
