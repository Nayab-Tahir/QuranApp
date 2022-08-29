package com.example.quranapp2;

public class tayahModel {
    private int AyaID;
    private int SuraID;
    private int AyaNo;
    private String ArabicText;
    private String TranslationU;
    private String TranslationE;
    private int RakuID;
    private int PRakuID;
    private int ParaID;

    public tayahModel(int ayaID, int suraID, int ayaNo, String arabicText, String translationU, String translationE, int rakuID, int PRakuID, int paraID) {
        AyaID = ayaID;
        SuraID = suraID;
        AyaNo = ayaNo;
        ArabicText = arabicText;
        TranslationU = translationU;
        TranslationE = translationE;
        RakuID = rakuID;
        this.PRakuID = PRakuID;
        ParaID = paraID;
    }

    public int getAyaID() {
        return AyaID;
    }

    public void setAyaID(int ayaID) {
        AyaID = ayaID;
    }

    public int getSuraID() {
        return SuraID;
    }

    public void setSuraID(int suraID) {
        SuraID = suraID;
    }

    public int getAyaNo() {
        return AyaNo;
    }

    public void setAyaNo(int ayaNo) {
        AyaNo = ayaNo;
    }

    public String getArabicText() {
        return ArabicText;
    }

    public void setArabicText(String arabicText) {
        ArabicText = arabicText;
    }

    public String getTranslationU() {
        return TranslationU;
    }

    public void setTranslationU(String translationU) {
        TranslationU = translationU;
    }

    public String getTranslationE() {
        return TranslationE;
    }

    public void setTranslationE(String translationE) {
        TranslationE = translationE;
    }

    public int getRakuID() {
        return RakuID;
    }

    public void setRakuID(int rakuID) {
        RakuID = rakuID;
    }

    public int getPRakuID() {
        return PRakuID;
    }

    public void setPRakuID(int PRakuID) {
        this.PRakuID = PRakuID;
    }

    public int getParaID() {
        return ParaID;
    }

    public void setParaID(int paraID) {
        ParaID = paraID;
    }
}
