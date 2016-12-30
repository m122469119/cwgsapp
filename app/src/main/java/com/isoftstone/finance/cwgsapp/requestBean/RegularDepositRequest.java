package com.isoftstone.finance.cwgsapp.requestBean;

import android.os.Parcel;
import android.os.Parcelable;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:13
*/
public class RegularDepositRequest extends BaseRequest
  implements Parcelable
{
  public static final Creator<RegularDepositRequest> CREATOR = new Creator()
  {
    public RegularDepositRequest createFromParcel(Parcel paramAnonymousParcel)
    {
      return new RegularDepositRequest(paramAnonymousParcel);
    }

    public RegularDepositRequest[] newArray(int paramAnonymousInt)
    {
      return new RegularDepositRequest[paramAnonymousInt];
    }
  };
  public String accountId;
  public String amountMax;
  public String amountMin;
  public String depositNo;
  public String startDateMax;
  public String startDateMin;
  public String termMax;
  public String termMin;

  public RegularDepositRequest()
  {
  }

  protected RegularDepositRequest(Parcel paramParcel)
  {
    this.depositNo = paramParcel.readString();
    this.termMin = paramParcel.readString();
    this.termMax = paramParcel.readString();
    this.startDateMin = paramParcel.readString();
    this.startDateMax = paramParcel.readString();
    this.amountMin = paramParcel.readString();
    this.amountMax = paramParcel.readString();
    this.accountId = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAccountId()
  {
    return this.accountId;
  }

  public String getAmountMax()
  {
    return this.amountMax;
  }

  public String getAmountMin()
  {
    return this.amountMin;
  }

  public String getDepositNo()
  {
    return this.depositNo;
  }

  public String getStartDateMax()
  {
    return this.startDateMax;
  }

  public String getStartDateMin()
  {
    return this.startDateMin;
  }

  public String getTermMax()
  {
    return this.termMax;
  }

  public String getTermMin()
  {
    return this.termMin;
  }

  public void setAccountId(String paramString)
  {
    this.accountId = paramString;
  }

  public void setAmountMax(String paramString)
  {
    this.amountMax = paramString;
  }

  public void setAmountMin(String paramString)
  {
    this.amountMin = paramString;
  }

  public void setDepositNo(String paramString)
  {
    this.depositNo = paramString;
  }

  public void setParameter(String paramString1, String paramString2)
  {
    int i = -1;
    switch (paramString1.hashCode())
    {
    default:
    case -414661431:
    case -2063881164:
    case 780275757:
    }
    while (true)
      switch (i) {
      default:
        if (paramString1.equals("rdticktnumberId")) {
          i = 0;
          continue;
        }
          if (paramString1.equals("rdlimitstartId")) {
            i = 1;
            continue;
          }
            if (paramString1.equals("rdlimitendId")){
              i = 2;
          }


      case 0:
        setDepositNo(paramString2);
        return;
      case 1:
        setTermMin(paramString2);
        return;
      case 2:
        setTermMax(paramString2);
        return;
      }



  }

  public void setStartDateMax(String paramString)
  {
    this.startDateMax = paramString;
  }

  public void setStartDateMin(String paramString)
  {
    this.startDateMin = paramString;
  }

  public void setTermMax(String paramString)
  {
    this.termMax = paramString;
  }

  public void setTermMin(String paramString)
  {
    this.termMin = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.depositNo);
    paramParcel.writeString(this.termMin);
    paramParcel.writeString(this.termMax);
    paramParcel.writeString(this.startDateMin);
    paramParcel.writeString(this.startDateMax);
    paramParcel.writeString(this.amountMin);
    paramParcel.writeString(this.amountMax);
    paramParcel.writeString(this.accountId);
  }
}