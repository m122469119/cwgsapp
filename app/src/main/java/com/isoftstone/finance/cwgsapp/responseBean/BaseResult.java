package com.isoftstone.finance.cwgsapp.responseBean;

public class BaseResult
{
  public String code;
  public String message;
  public int pageNum;
  public int pageSize;
  public int total;

  public String getCode()
  {
    return this.code;
  }

  public String getMessage()
  {
    return this.message;
  }

  public int getPageNum()
  {
    return this.pageNum;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public int getTotal()
  {
    return this.total;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setPageNum(int paramInt)
  {
    this.pageNum = paramInt;
  }

  public void setPageSize(int paramInt)
  {
    this.pageSize = paramInt;
  }

  public void setTotal(int paramInt)
  {
    this.total = paramInt;
  }
}