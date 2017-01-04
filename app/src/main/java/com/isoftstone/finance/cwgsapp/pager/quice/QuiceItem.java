package com.isoftstone.finance.cwgsapp.pager.quice;

import com.litesuits.orm.db.annotation.Table;
import java.io.Serializable;

//@Table("QuiceItem")
public class QuiceItem extends BaseFunction
  implements Serializable
{
  public int iconResID;
  private boolean isShowHome = true;
  public int itemType;
  private int quiceID;

  public QuiceItem()
  {
  }

  public QuiceItem(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    this.iconResID = paramInt2;
    this.itemType = paramInt3;
    setName(paramString);
    this.quiceID = paramInt1;
  }

  public QuiceItem(String paramString, int paramInt)
  {
    setName(paramString);
    this.iconResID = paramInt;
  }

  public int getIconResID()
  {
    return this.iconResID;
  }

  public int getItemType()
  {
    return this.itemType;
  }

  public int getQuiceID()
  {
    return this.quiceID;
  }

  public boolean isShowHome()
  {
    return this.isShowHome;
  }

  public void setIconResID(int paramInt)
  {
    this.iconResID = paramInt;
  }

  public void setItemType(int paramInt)
  {
    this.itemType = paramInt;
  }

  public void setQuiceID(int paramInt)
  {
    this.quiceID = paramInt;
  }

  public void setShowHome(boolean paramBoolean)
  {
    this.isShowHome = paramBoolean;
  }

  public String toString()
  {
    return "QuiceItem{" + this.name + "isShowHome=" + this.isShowHome + ", itemType=" + this.itemType + ", iconResID=" + this.iconResID + '}';
  }
}