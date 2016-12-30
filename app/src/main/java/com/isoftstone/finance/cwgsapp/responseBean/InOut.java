package com.isoftstone.finance.cwgsapp.responseBean;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:16
*/
public class InOut
{
  String code;
  String io;

  public InOut(String paramString1, String paramString2)
  {
    this.code = paramString1;
    this.io = paramString2;
  }

  public String getCode()
  {
    return this.code;
  }

  public String getIo()
  {
    return this.io;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setIo(String paramString)
  {
    this.io = paramString;
  }
}