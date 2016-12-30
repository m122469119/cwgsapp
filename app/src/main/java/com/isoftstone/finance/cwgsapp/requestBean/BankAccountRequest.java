package com.isoftstone.finance.cwgsapp.requestBean;

import android.os.Parcel;
import android.os.Parcelable;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-29 10:22
*/
public class BankAccountRequest extends BaseRequest
        implements Parcelable
{
    public static final Creator<BankAccountRequest> CREATOR = new Creator()
    {
        public BankAccountRequest createFromParcel(Parcel paramAnonymousParcel)
        {
            return new BankAccountRequest(paramAnonymousParcel);
        }

        public BankAccountRequest[] newArray(int paramAnonymousInt)
        {
            return new BankAccountRequest[paramAnonymousInt];
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

    public BankAccountRequest()
    {
    }

    public BankAccountRequest(Parcel paramParcel)
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

    public int describeContents()
    {
        return 0;
    }

    public String getAccountByOpenDateEnd()
    {
        return this.accountByOpenDateEnd;
    }

    public String getAccountByOpenDateStart()
    {
        return this.accountByOpenDateStart;
    }

    public String getAccountName()
    {
        return this.accountName;
    }

    public String getAccountNo()
    {
        return this.accountNo;
    }

    public String getAccountProperty()
    {
        return this.accountProperty;
    }

    public String getAccountStatus()
    {
        return this.accountStatus;
    }

    public String getAccountType()
    {
        return this.accountType;
    }

    public String getBankTypeId()
    {
        return this.bankTypeId;
    }

    public String getClientID()
    {
        return this.clientID;
    }

    public String getCurrencyId()
    {
        return this.currencyId;
    }

    public String getIsDirectlink()
    {
        return this.isDirectlink;
    }

    public String getIsTerritory()
    {
        return this.isTerritory;
    }

    public String getOfficeId()
    {
        return this.officeId;
    }

    public String getOpenBankId()
    {
        return this.openBankId;
    }

    public String getOwnerType()
    {
        return this.ownerType;
    }

    public void setAccountByOpenDateEnd(String paramString)
    {
        this.accountByOpenDateEnd = paramString;
    }

    public void setAccountByOpenDateStart(String paramString)
    {
        this.accountByOpenDateStart = paramString;
    }

    public void setAccountName(String paramString)
    {
        this.accountName = paramString;
    }

    public void setAccountNo(String paramString)
    {
        this.accountNo = paramString;
    }

    public void setAccountProperty(String paramString)
    {
        this.accountProperty = paramString;
    }

    public void setAccountStatus(String paramString)
    {
        this.accountStatus = paramString;
    }

    public void setAccountType(String paramString)
    {
        this.accountType = paramString;
    }

    public void setBankTypeId(String paramString)
    {
        this.bankTypeId = paramString;
    }

    public void setClientID(String paramString)
    {
        this.clientID = paramString;
    }

    public void setCurrencyId(String paramString)
    {
        this.currencyId = paramString;
    }

    public void setIsDirectlink(String paramString)
    {
        this.isDirectlink = paramString;
    }

    public void setIsTerritory(String paramString)
    {
        this.isTerritory = paramString;
    }

    public void setOfficeId(String paramString)
    {
        this.officeId = paramString;
    }

    public void setOpenBankId(String paramString)
    {
        this.openBankId = paramString;
    }

    public void setOwnerType(String paramString)
    {
        this.ownerType = paramString;
    }

    public void setParameter(String paramString1, String paramString2)
    {
//        int i = -1;
//        switch (paramString1.hashCode())
//        {
//            default:
//            case 1150097001:
//            case -765294345:
//            case -1089455860:
//            case 121374545:
//            case -155770015:
//            case 17654925:
//            case -1827029810:
//            case 865966680:
//            case 908408358:
//            case -937219568:
//            case -869322625:
//            case -212250846:
//            case 866168583:
//            case -1899156435:
//            case 1347749158:
//            case 640494239:
//        }
//        while (true) {
//            switch (i) {
//                default:
//                    return;
//                case 0:
//                    if (paramString1.equals("requestType")) {
//                        i = 0;
//                        continue;
//                        if (paramString1.equals("officeId")) {
//                            i = 1;
//                            continue;
//                            if (paramString1.equals("currencyId")) {
//                                i = 2;
//                                continue;
//                                if (paramString1.equals("bankTypeId")) {
//                                    i = 3;
//                                    continue;
//                                    if (paramString1.equals("openBankId")) {
//                                        i = 4;
//                                        continue;
//                                        if (paramString1.equals("ownerType")) {
//                                            i = 5;
//                                            continue;
//                                            if (paramString1.equals("accountNo")) {
//                                                i = 6;
//                                                continue;
//                                                if (paramString1.equals("accountName")) {
//                                                    i = 7;
//                                                    continue;
//                                                    if (paramString1.equals("clientID")) {
//                                                        i = 8;
//                                                        continue;
//                                                        if (paramString1.equals("isTerritory")) {
//                                                            i = 9;
//                                                            continue;
//                                                            if (paramString1.equals("accountStatus")) {
//                                                                i = 10;
//                                                                continue;
//                                                                if (paramString1.equals("accountProperty")) {
//                                                                    i = 11;
//                                                                    continue;
//                                                                    if (paramString1.equals("accountType")) {
//                                                                        i = 12;
//                                                                        continue;
//                                                                        if (paramString1.equals("isDirectlink")) {
//                                                                            i = 13;
//                                                                            continue;
//                                                                            if (paramString1.equals("accountByOpenDateStart")) {
//                                                                                i = 14;
//                                                                                continue;
//                                                                                if (paramString1.equals("accountByOpenDateEnd"))
//                                                                                    i = 15;
//                                                                            }
//                                                                        }
//                                                                    }
//                                                                }
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    break;
//                case 1:
//                    setOfficeId(paramString2);
//                    return;
//                case 2:
//                    setCurrencyId(paramString2);
//                    return;
//                case 3:
//                    setBankTypeId(paramString2);
//                    return;
//                case 4:
//                    setOpenBankId(paramString2);
//                    return;
//                case 5:
//                    setOwnerType(paramString2);
//                    return;
//                case 6:
//                    setAccountNo(paramString2);
//                    return;
//                case 7:
//                    setAccountName(paramString2);
//                    return;
//                case 8:
//                    setClientID(paramString2);
//                    return;
//                case 9:
//                    setIsTerritory(paramString2);
//                    return;
//                case 10:
//                    setAccountStatus(paramString2);
//                    return;
//                case 11:
//                    setAccountProperty(paramString2);
//                    return;
//                case 12:
//                    setAccountType(paramString2);
//                    return;
//                case 13:
//                    setIsDirectlink(paramString2);
//                    return;
//                case 14:
//                    setAccountByOpenDateStart(paramString2);
//                    return;
//                case 15:
//                    setAccountByOpenDateEnd(paramString2);
//                    return;
//            }
//        }
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