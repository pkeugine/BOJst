package com.example.jhw.exblockdetailapplication.data;

public class Detail {
    PoiDetailInfo poiDetailInfo;

    public static class PoiDetailInfo {
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
    }

    public PoiDetailInfo getPoiDetailInfo() {
        return poiDetailInfo;
    }

}
