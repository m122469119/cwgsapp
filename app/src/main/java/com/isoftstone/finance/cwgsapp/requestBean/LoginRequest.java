package com.isoftstone.finance.cwgsapp.requestBean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LoginRequest
  implements Parcelable
{
  public static final Parcelable.Creator<LoginRequest> CREATOR = new Parcelable.Creator()
  {
    public LoginRequest createFromParcel(Parcel paramAnonymousParcel)
    {
      return new LoginRequest(paramAnonymousParcel);
    }

    public LoginRequest[] newArray(int paramAnonymousInt)
    {
      return new LoginRequest[paramAnonymousInt];
    }
  };
  public String bindingNumber;
  public String password;
  public String username;

  protected LoginRequest(Parcel paramParcel)
  {
    this.username = paramParcel.readString();
    this.password = paramParcel.readString();
    this.bindingNumber = paramParcel.readString();
  }

  public LoginRequest(String paramString1, String paramString2, String paramString3)
  {
    this.username = paramString1;
    this.password = paramString2;
    this.bindingNumber = paramString3;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getBindingNumber()
  {
    return this.bindingNumber;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getUsername()
  {
    return this.username;
  }

  public void setBindingNumber(String paramString)
  {
    this.bindingNumber = paramString;
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }

  public void setUsername(String paramString)
  {
    this.username = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.username);
    paramParcel.writeString(this.password);
    paramParcel.writeString(this.bindingNumber);
  }
}