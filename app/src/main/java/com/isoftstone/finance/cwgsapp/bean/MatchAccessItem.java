package com.isoftstone.finance.cwgsapp.bean;

public class MatchAccessItem
{
  public String commpanyname;
  public String creationtime;
  public String creator;
  public String status;

  public MatchAccessItem(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.commpanyname = paramString1;
    this.status = paramString2;
    this.creator = paramString3;
    this.creationtime = paramString4;
  }

  public String getCommpanyname()
  {
    return this.commpanyname;
  }

  public String getCreationtime()
  {
    return this.creationtime;
  }

  public String getCreator()
  {
    return this.creator;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setCommpanyname(String paramString)
  {
    this.commpanyname = paramString;
  }

  public void setCreationtime(String paramString)
  {
    this.creationtime = paramString;
  }

  public void setCreator(String paramString)
  {
    this.creator = paramString;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
}