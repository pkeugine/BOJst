package com.example.jhw.exblockdetailapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

public class Detail {
    PoiDetailInfo poiDetailInfo;

    public static class PoiDetailInfo implements Parcelable {
        private String id;
        private String name;
        private String address;
        private String firstNo;
        private String secondNo;
        private String tel;
        private double lat;
        private double lon;
        private String parkFlag;
        private String additionalInfo;
        private String desc;


        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getFirstNo() {
            return firstNo;
        }

        public String getSecondNo() {
            return secondNo;
        }

        public String getTel() {
            return tel;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public String getParkFlag() {
            return parkFlag;
        }

        public String getAdditionalInfo() {
            return additionalInfo;
        }

        public String getDesc() {
            return desc;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.id);
            parcel.writeString(this.name);
            parcel.writeString(this.address);
            parcel.writeString(this.firstNo);
            parcel.writeString(this.secondNo);
            parcel.writeString(this.tel);
            parcel.writeDouble(this.lat);
            parcel.writeDouble(this.lon);
            parcel.writeString(this.parkFlag);
            parcel.writeString(this.additionalInfo);
            parcel.writeString(this.desc);
        }

        protected PoiDetailInfo(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.address = in.readString();
            this.firstNo = in.readString();
            this.secondNo = in.readString();
            this.tel = in.readString();
            this.lat = in.readDouble();
            this.lon = in.readDouble();
            this.parkFlag = in.readString();
            this.additionalInfo = in.readString();
            this.desc = in.readString();
        }

        public static final Creator<PoiDetailInfo> CREATOR = new Creator<PoiDetailInfo>() {
            @Override
            public PoiDetailInfo createFromParcel(Parcel in) {
                return new PoiDetailInfo(in);
            }

            @Override
            public PoiDetailInfo[] newArray(int size) {
                return new PoiDetailInfo[size];
            }
        };

    }
    public PoiDetailInfo getPoiDetailInfo() {return poiDetailInfo;}

}
