package com.isoftstone.finance.cwgsapp.responseBean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PowerInfo
        implements Parcelable {
    public static final Parcelable.Creator<PowerInfo> CREATOR = new Parcelable.Creator() {
        public PowerInfo createFromParcel(Parcel paramAnonymousParcel) {
            return new PowerInfo(paramAnonymousParcel);
        }

        public PowerInfo[] newArray(int paramAnonymousInt) {
            return new PowerInfo[paramAnonymousInt];
        }
    };
    public String menuCode;
    public String menuName;

    protected PowerInfo(Parcel paramParcel) {
        this.menuCode = paramParcel.readString();
        this.menuName = paramParcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt) {
        paramParcel.writeString(this.menuCode);
        paramParcel.writeString(this.menuName);
    }
}