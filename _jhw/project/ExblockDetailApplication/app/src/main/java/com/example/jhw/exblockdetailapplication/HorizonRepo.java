package com.example.jhw.exblockdetailapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HorizonRepo implements Serializable {
    private int firstCategory;
    private String secondCategory;
    private ArrayList<PoiRepo> poiList;
    private int vIndex;
    private int hIndex = 0;

    public int getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(int firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
    }

    public ArrayList<PoiRepo> getPoiList() {
        return poiList;
    }

    public void setPoiList(ArrayList<PoiRepo> poiList) {
        this.poiList = poiList;
    }

    public int getvIndex() {
        return vIndex;
    }

    public void setvIndex(int vIndex) {
        this.vIndex = vIndex;
    }

    public int gethIndex() {
        return hIndex;
    }

    public void sethIndex(int hIndex) {
        this.hIndex = hIndex;
    }

    public HorizonRepo(int firstCategory, String secondCategory, ArrayList<PoiRepo> poiList) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.poiList = poiList;
    }

    public HorizonRepo(int firstCategory, String secondCategory, ArrayList<PoiRepo> poiList, int vIndex) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.poiList = poiList;
        this.vIndex = vIndex;
    }

    public HorizonRepo(int firstCategory, String secondCategory, ArrayList<PoiRepo> poiList, int vIndex, int hIndex) {
        this.firstCategory = firstCategory;
        this.secondCategory = secondCategory;
        this.poiList = poiList;
        this.vIndex = vIndex;
        this.hIndex = hIndex;
    }
}