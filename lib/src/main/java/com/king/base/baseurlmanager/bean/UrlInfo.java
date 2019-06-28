package com.king.base.baseurlmanager.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.NonNull;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class UrlInfo implements Parcelable,Comparable<UrlInfo> {

    private String baseUrl;

    private long time;

    public UrlInfo(@NonNull String baseUrl) {
        this(baseUrl,System.currentTimeMillis());
    }

    public UrlInfo(@NonNull String baseUrl,long time) {
        this.baseUrl = baseUrl;
        this.time = time;
    }

    public String getBaseUrl() {
        if(TextUtils.isEmpty(baseUrl)){
            return "";
        }
        return baseUrl;
    }

    public void setBaseUrl(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UrlInfo{" +
                "baseUrl='" + baseUrl + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlInfo urlInfo = (UrlInfo) o;

        return baseUrl.equals(urlInfo.baseUrl);
    }

    @Override
    public int hashCode() {
        return baseUrl.hashCode();
    }


    @Override
    public int compareTo(@NonNull UrlInfo o) {
        if(time > o.time){
            return 1;
        }
        if(time < o.time){
            return -1;
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.baseUrl);
        dest.writeLong(this.time);
    }

    protected UrlInfo(Parcel in) {
        this.baseUrl = in.readString();
        this.time = in.readLong();
    }

    public static final Creator<UrlInfo> CREATOR = new Creator<UrlInfo>() {
        @Override
        public UrlInfo createFromParcel(Parcel source) {
            return new UrlInfo(source);
        }

        @Override
        public UrlInfo[] newArray(int size) {
            return new UrlInfo[size];
        }
    };
}

