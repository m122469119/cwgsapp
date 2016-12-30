package com.isoftstone.finance.cwgsapp.responseBean;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:15
*/
public class IAAccountNoInfo
{
  public String accountName;
  public String accountNo;
  public String id;

  public String getAccountName()
  {
    return this.accountName;
  }

  public String getAccountNo()
  {
    return this.accountNo;
  }

  public String getId()
  {
    return this.id;
  }

  public void setAccountName(String paramString)
  {
    this.accountName = paramString;
  }

  public void setAccountNo(String paramString)
  {
    this.accountNo = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }
}