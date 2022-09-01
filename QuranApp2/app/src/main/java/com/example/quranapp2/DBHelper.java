package com.example.quranapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    String DBName;
    String DBPath;

    public DBHelper(@Nullable Context context) {
        super(context, "quran_database_new.db", null, 1);
        this.context = context;
        this.DBName = "quran_database_new.db";
        this.DBPath = "/data/data/" + this.context.getPackageName() + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CopyDatabase();
    }

    public ArrayList<tsurahModel> getAllSurahs(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM tsurah", null);

        ArrayList<tsurahModel> list = new ArrayList<tsurahModel>();
        tsurahModel surah;
        if(cursorCourses.moveToFirst()) {
            while(!cursorCourses.isAfterLast()) {
                int SurahID = cursorCourses.getInt(0);
                String SurahIntro = cursorCourses.getString(1);
                String SurahNameE = cursorCourses.getString(2);
                String Nazool = cursorCourses.getString(3);
                String SurahNameU = cursorCourses.getString(4);

                surah = new tsurahModel(SurahID, SurahIntro, SurahNameE, Nazool, SurahNameU);
                list.add(surah);
                cursorCourses.moveToNext();
            }
        }
        return list;
    }

    public tayahModel getBismillah(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM tayah WHERE AyaID=0", null);
        cursorCourses.moveToFirst();
        int AyaID = cursorCourses.getInt(0);
        int SuraID = cursorCourses.getInt(1);
        int AyaNo = cursorCourses.getInt(2);
        String ArabicText = cursorCourses.getString(3);
        String TranslationU = cursorCourses.getString(4);
        String TranslationE = cursorCourses.getString(6);
        int RakuID = cursorCourses.getInt(8);
        int PRakuID = cursorCourses.getInt(9);
        int ParaID = cursorCourses.getInt(10);

        tayahModel ayah = new tayahModel( AyaID,  SuraID,  AyaNo,  ArabicText,  TranslationU,  TranslationE,  RakuID,  PRakuID,  ParaID);
        return ayah;
    }

    public ArrayList<tayahModel> getAllAyahs(int surahID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM tayah WHERE SuraID="+surahID, null);

        ArrayList<tayahModel> list = new ArrayList<tayahModel>();
        if(surahID != 1)
            list.add(getBismillah());

        tayahModel ayah;
        if(cursorCourses.moveToFirst()) {
            while(!cursorCourses.isAfterLast()) {
                int AyaID = cursorCourses.getInt(0);
                int SuraID = cursorCourses.getInt(1);
                int AyaNo = cursorCourses.getInt(2);
                String ArabicText = cursorCourses.getString(3);
                String TranslationU = cursorCourses.getString(4);
                String TranslationE = cursorCourses.getString(6);
                int RakuID = cursorCourses.getInt(8);
                int PRakuID = cursorCourses.getInt(9);
                int ParaID = cursorCourses.getInt(10);

                ayah = new tayahModel( AyaID,  SuraID,  AyaNo,  ArabicText,  TranslationU,  TranslationE,  RakuID,  PRakuID,  ParaID);
                list.add(ayah);
                cursorCourses.moveToNext();
            }
        }
        return list;
    }

    public void CheckDatabase(){
        try{
            String path = DBPath + DBName;
            SQLiteDatabase.openDatabase(path, null, 0);
        }catch(Exception e){}

        this.getReadableDatabase();
        CopyDatabase();
    }

    public void CopyDatabase(){
        try{
            InputStream io = context.getAssets().open(DBName);
            String outfilestream = DBPath + DBName;
            OutputStream outputStream = new FileOutputStream(outfilestream);

            int length;
            byte[] buffer = new byte[1024];
            while((length = io.read(buffer)) != -1){
                outputStream.write(buffer, 0, length);
            }
            io.close();
            outputStream.flush();
            outputStream.close();

        }catch(Exception e){}
    }

    public void OpenDatabase(){
        String path = DBPath + DBName;
        SQLiteDatabase.openDatabase(path, null, 0);
    }
}
