package com.isoftstone.finance.cwgsapp.bean;

public class WaitApprovalItem
{
  public String operationName;
  public String operationNum;

  public WaitApprovalItem(String paramString1, String paramString2)
  {
    this.operationName = paramString1;
    this.operationNum = paramString2;
  }

  public String getOperationName()
  {
    return this.operationName;
  }

  public String getOperationNum()
  {
    return this.operationNum;
  }

  public void setOperationName(String paramString)
  {
    this.operationName = paramString;
  }

  public void setOperationNum(String paramString)
  {
  }
}