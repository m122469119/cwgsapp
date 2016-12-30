package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:16
*/
public class NoticeDepositNumResult extends BaseResult
{
  public ArrayList<NoticeDepositNumInfo> result;

  public ArrayList<NoticeDepositNumInfo> getResult()
  {
    return this.result;
  }

  public void setResult(ArrayList<NoticeDepositNumInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}