package com.isoftstone.finance.cwgsapp.responseBean;
/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:17
*/
public class VPAccountNoInfo
{
  public String accountNo;
  public String acountName;
  public String id;

  public String getAccountNo()
  {
    return this.accountNo;
  }

  public String getAcountName()
  {
    return this.acountName;
  }

  public String getId()
  {
    return this.id;
  }

  public void setAccountNo(String paramString)
  {
    this.accountNo = paramString;
  }

  public void setAcountName(String paramString)
  {
    this.acountName = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }
}