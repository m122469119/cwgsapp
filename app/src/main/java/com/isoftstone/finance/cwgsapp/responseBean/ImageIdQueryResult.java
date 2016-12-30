package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

public class ImageIdQueryResult extends BaseResult
{
  public String StrirequestType;
  public ArrayList<PictureInfo> result;

  public String getStrirequestType()
  {
    return this.StrirequestType;
  }

  public void setStrirequestType(String paramString)
  {
    this.StrirequestType = paramString;
  }
}