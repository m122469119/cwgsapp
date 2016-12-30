package com.isoftstone.finance.cwgsapp.requestBean;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:13
*/
public class CreditDetailRequest
{
  public String controlStyleJson;
  public String id;
  public String isCircleJson;

  public String getControlStyleJson()
  {
    return this.controlStyleJson;
  }

  public String getId()
  {
    return this.id;
  }

  public String getIsCircleJson()
  {
    return this.isCircleJson;
  }

  public void setControlStyleJson(String paramString)
  {
    this.controlStyleJson = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setIsCircleJson(String paramString)
  {
    this.isCircleJson = paramString;
  }
}