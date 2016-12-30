package com.isoftstone.finance.cwgsapp.requestBean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 11:07
*/
public class InsideAccountRequest extends BaseRequest
  implements Parcelable
{
  public static final Parcelable.Creator<InsideAccountRequest> CREATOR = new Parcelable.Creator()
  {
    public InsideAccountRequest createFromParcel(Parcel paramAnonymousParcel)
    {
      return new InsideAccountRequest(paramAnonymousParcel);
    }

    public InsideAccountRequest[] newArray(int paramAnonymousInt)
    {
      return new InsideAccountRequest[paramAnonymousInt];
    }
  };
  public String accountEndNo;
  public String accountStartNo;
  public String clientEndCode;
  public String clientStartCode;
  public String currencyId;

  public InsideAccountRequest()
  {
  }

  protected InsideAccountRequest(Parcel paramParcel)
  {
    this.currencyId = paramParcel.readString();
    this.clientStartCode = paramParcel.readString();
    this.clientEndCode = paramParcel.readString();
    this.accountStartNo = paramParcel.readString();
    this.accountEndNo = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAccountEndNo()
  {
    return this.accountEndNo;
  }

  public String getAccountStartNo()
  {
    return this.accountStartNo;
  }

  public String getClientEndCode()
  {
    return this.clientEndCode;
  }

  public String getClientStartCode()
  {
    return this.clientStartCode;
  }

  public String getCurrencyId()
  {
    return this.currencyId;
  }

  public void setAccountEndNo(String paramString)
  {
    this.accountEndNo = paramString;
  }

  public void setAccountStartNo(String paramString)
  {
    this.accountStartNo = paramString;
  }

  public void setClientEndCode(String paramString)
  {
    this.clientEndCode = paramString;
  }

  public void setClientStartCode(String paramString)
  {
    this.clientStartCode = paramString;
  }

  public void setCurrencyId(String paramString)
  {
    this.currencyId = paramString;
  }

  public void setParameter(String paramString1, String paramString2)
  {
    int i = -1;
    switch (paramString1.toString())
    {
      default:
        return;
    case "currencyId":
      if (paramString1.equals("currencyId")) {
        i = 0;
        break;
      }
    case "clientStartCode":
      if (paramString1.equals("clientStartCode")) {
        i = 1;
        break;
      }
    case "clientEndCode":
      if (paramString1.equals("clientEndCode")) {
        i = 2;
        break;
      }
    case "accountStartNo":
      if (paramString1.equals("accountStartNo")) {
        i = 3;
        break;
      }
    case "accountEndNo":
      if (paramString1.equals("accountEndNo")){
        i = 4;
        break;
      }
    }
    while (true)
      switch (i)
      {
      default:
        return;
      case 0:
        setCurrencyId(paramString2);
        return;
      case 1:
        setClientStartCode(paramString2);
        return;
      case 2:
        setClientEndCode(paramString2);
        return;
      case 3:
        setAccountStartNo(paramString2);
        return;
      case 4:
        setAccountEndNo(paramString2);
        return;
      }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.currencyId);
    paramParcel.writeString(this.clientStartCode);
    paramParcel.writeString(this.clientEndCode);
    paramParcel.writeString(this.accountStartNo);
    paramParcel.writeString(this.accountEndNo);
  }
}