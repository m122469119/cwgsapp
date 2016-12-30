package com.isoftstone.finance.cwgsapp.responseBean;

import java.util.ArrayList;

/**
* @项目名称：财务公司APP
* @类描述：
* @创建人：lukai
* @创建时间：2016-12-30 10:14
*/
public class ClientMessageResult extends BaseResult
{
  public String requestType;
  public ArrayList<ClientMessageInfo> result;

  public ArrayList<ClientMessageInfo> getResult()
  {
    return this.result;
  }

  public void setResult(ArrayList<ClientMessageInfo> paramArrayList)
  {
    this.result = paramArrayList;
  }
}