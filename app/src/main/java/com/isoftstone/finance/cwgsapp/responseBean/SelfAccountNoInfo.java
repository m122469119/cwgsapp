package com.isoftstone.finance.cwgsapp.responseBean;
/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:16
*/
public class SelfAccountNoInfo
{
  public String accountName;
  public String accountNo;
  public String bankName;

  public String getAccountName()
  {
    return this.accountName;
  }

  public String getAccountNo()
  {
    return this.accountNo;
  }

  public String getBankName()
  {
    return this.bankName;
  }

  public void setAccountName(String paramString)
  {
    this.accountName = paramString;
  }

  public void setAccountNo(String paramString)
  {
    this.accountNo = paramString;
  }

  public void setBankName(String paramString)
  {
    this.bankName = paramString;
  }
}