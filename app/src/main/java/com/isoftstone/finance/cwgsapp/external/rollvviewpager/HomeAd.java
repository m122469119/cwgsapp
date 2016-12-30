package com.isoftstone.finance.cwgsapp.external.rollvviewpager;

public class HomeAd
{
  private String title;
  private String url;

  public HomeAd(String paramString1, String paramString2)
  {
    this.title = paramString1;
    this.url = paramString2;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}