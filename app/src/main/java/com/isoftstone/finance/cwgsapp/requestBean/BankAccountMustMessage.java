package com.isoftstone.finance.cwgsapp.requestBean;

import android.os.Parcel;
import android.os.Parcelable;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-29 10:22
*/
public class BankAccountMustMessage
        implements Parcelable
{
    public static final Creator<BankAccountMustMessage> CREATOR = new Creator()
    {
        public BankAccountMustMessage createFromParcel(Parcel paramAnonymousParcel)
        {
            return new BankAccountMustMessage(paramAnonymousParcel);
        }

        public BankAccountMustMessage[] newArray(int paramAnonymousInt)
        {
            return new BankAccountMustMessage[paramAnonymousInt];
        }
    };
    public String accountByOpenDateEnd;
    public String accountByOpenDateStart;
    public String accountName;
    public String accountNo;
    public String accountProperty;
    public String accountStatus;
    public String accountType;
    public String bankTypeId;
    public String clientID;
    public String currencyId;
    public String isDirectlink;
    public String isTerritory;
    public String officeId;
    public String openBankId;
    public String ownerType;
    public String requestType;

    protected BankAccountMustMessage(Parcel paramParcel)
    {
        this.requestType = paramParcel.readString();
        this.officeId = paramParcel.readString();
        this.currencyId = paramParcel.readString();
        this.bankTypeId = paramParcel.readString();
        this.openBankId = paramParcel.readString();
        this.ownerType = paramParcel.readString();
        this.accountNo = paramParcel.readString();
        this.accountName = paramParcel.readString();
        this.clientID = paramParcel.readString();
        this.isTerritory = paramParcel.readString();
        this.accountStatus = paramParcel.readString();
        this.accountProperty = paramParcel.readString();
        this.accountType = paramParcel.readString();
        this.isDirectlink = paramParcel.readString();
        this.accountByOpenDateStart = paramParcel.readString();
        this.accountByOpenDateEnd = paramParcel.readString();
    }

    public BankAccountMustMessage(String paramString)
    {
        this.ownerType = paramString;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getOwnerType()
    {
        return this.ownerType;
    }

    public void setOwnerType(String paramString)
    {
        this.ownerType = paramString;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
        paramParcel.writeString(this.requestType);
        paramParcel.writeString(this.officeId);
        paramParcel.writeString(this.currencyId);
        paramParcel.writeString(this.bankTypeId);
        paramParcel.writeString(this.openBankId);
        paramParcel.writeString(this.ownerType);
        paramParcel.writeString(this.accountNo);
        paramParcel.writeString(this.accountName);
        paramParcel.writeString(this.clientID);
        paramParcel.writeString(this.isTerritory);
        paramParcel.writeString(this.accountStatus);
        paramParcel.writeString(this.accountProperty);
        paramParcel.writeString(this.accountType);
        paramParcel.writeString(this.isDirectlink);
        paramParcel.writeString(this.accountByOpenDateStart);
        paramParcel.writeString(this.accountByOpenDateEnd);
    }
}