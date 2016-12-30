package com.isoftstone.finance.cwgsapp.responseBean;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:15
*/
public class DraftType
{
  String drafttype;
  String id;

  public DraftType(String paramString1, String paramString2)
  {
    this.id = paramString1;
    this.drafttype = paramString2;
  }

  public String getDrafttype()
  {
    return this.drafttype;
  }

  public String getId()
  {
    return this.id;
  }

  public void setDrafttype(String paramString)
  {
    this.drafttype = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }
}