package com.example.quranapp3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.InputStream;
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

    public String[] getSurahName(int surahID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT SurahNameE, SurahNameU FROM tsurah WHERE SurahID="+surahID, null);
        String[] list = new String[2];
        cursorCourses.moveToFirst();
        list[0] = cursorCourses.getString(0);
        list[1] = cursorCourses.getString(1);
        return list;
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

    public ArrayList<tayahModel> getParah(int p){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM tayah WHERE ParaID = "+ p + " ORDER BY SuraID, AyaNo", null);

        tayahModel ayah;
        ArrayList<tayahModel> surah = new ArrayList<tayahModel>();

        int prev = 0;
        if(cursorCourses.moveToFirst()) {
            int AyaID = cursorCourses.getInt(0);
            int SuraID = cursorCourses.getInt(1);
            int AyaNo = cursorCourses.getInt(2);
            String ArabicText = cursorCourses.getString(3);
            String TranslationU = cursorCourses.getString(4);
            String TranslationE = cursorCourses.getString(6);
            int RakuID = cursorCourses.getInt(8);
            int PRakuID = cursorCourses.getInt(9);
            int ParaID = cursorCourses.getInt(10);

            String name = getSurahName(SuraID)[1];

            if(AyaNo != 1 || SuraID == 1){
                ArabicText = name  + "\n\n" + ArabicText;
            }
            else{
                tayahModel temp = getBismillah();
                temp.setArabicText(name + "\n\n" + temp.getArabicText());
                surah.add(temp);
            }

            ayah = new tayahModel( AyaID,  SuraID,  AyaNo,  ArabicText,  TranslationU,  TranslationE,  RakuID,  PRakuID,  ParaID);
            surah.add(ayah);

            prev = SuraID;
            cursorCourses.moveToNext();
        }

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
            if(SuraID != prev){
                String name = getSurahName(SuraID)[1];
                tayahModel temp = getBismillah();
                temp.setArabicText("\n" + name + "\n\n" + temp.getArabicText());
                surah.add(temp);
                surah.add(ayah);
                prev = SuraID;
            }
            else{
                surah.add(ayah);
            }
            cursorCourses.moveToNext();
        }

        return surah;
    }

    public tayahModel searchAyah(int surah_no, int ayah_no){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM tayah WHERE SuraID="+surah_no+" AND AyaNo="+ ayah_no, null);

        tayahModel ayah = null;
        if(cursorCourses.moveToFirst()) {
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
        }

        return ayah;
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
