package com.example.louis.weinschmeckeroffenburg.Datenbank.Item;

import org.bytedeco.javacpp.presets.opencv_core;

import java.sql.Blob;

/**
 * Created by louis on 21.12.2017.
 */

public class Item {

    private String id;
    private String weinname;
    private String jahrgang;
    private String land;
    private String preis;
    private String geschmack;
    private String art;
    private String laden;
    private String servierVorschlag;
    private String content;
    private String img;




    int mIsFavourite;



    public int getmIsFavourite() {
        return mIsFavourite;
    }



    public Item(String id, String weinname, String jahrgang, String land, String preis, String geschmack, String art, String laden, String servierVorschlag, String content, String img, int mIsFavourite) {
        this.id = id;
        this.weinname = weinname;
        this.jahrgang = jahrgang;
        this.land = land;
        this.preis = preis;
        this.geschmack = geschmack;
        this.art = art;
        this.laden = laden;
        this.servierVorschlag = servierVorschlag;
        this.content = content;
        this.img = img;
        this.mIsFavourite = mIsFavourite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeschmack() {
        return geschmack;
    }

    public void setGeschmack(String geschmack) {
        this.geschmack = geschmack;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getLaden() {
        return laden;
    }

    public void setLaden(String laden) {
        this.laden = laden;
    }

    public String getServierVorschlag() {
        return servierVorschlag;
    }

    public void setServierVorschlag(String servierVorschlag) {
        this.servierVorschlag = servierVorschlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int ismIsFavourite() {
        return mIsFavourite;
    }

    public void setmIsFavourite(int mIsFavourite) {
        this.mIsFavourite = mIsFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        mIsFavourite = isFavourite;
    }

    public int getIsFavourite() {
        return mIsFavourite;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }


    public String getWeinname() {
        return weinname;
    }

    public void setWeinname(String weinname) {
        this.weinname = weinname;
    }

    public String getJahrgang() {
        return jahrgang;
    }

    public void setJahrgang(String jahrgang) {
        this.jahrgang = jahrgang;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

}
