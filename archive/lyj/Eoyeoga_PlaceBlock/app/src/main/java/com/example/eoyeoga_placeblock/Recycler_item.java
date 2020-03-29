package com.example.eoyeoga_placeblock;

public class Recycler_item {
    int deleteimg, addimg;
    String tname, tstar, tprice, treview;

    Recycler_item(){}

    String getTname(){
        return this.tname;
    }

    String getTstar(){
        return this.tstar;
    }

    String getTprice(){
        return this.tprice;
    }

    String getTreview(){
        return this.treview;
    }

    Recycler_item(String tname, String tstar, String tprice, String treview){
        this.tname = tname;
        this.tstar = tstar;
        this.tprice = tprice;
        this.treview = treview;
    }

}
