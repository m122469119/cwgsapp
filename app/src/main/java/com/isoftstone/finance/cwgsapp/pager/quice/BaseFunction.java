package com.isoftstone.finance.cwgsapp.pager.quice;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;
import java.io.Serializable;

public abstract class BaseFunction
  implements Serializable
{

  @PrimaryKey(AssignType.AUTO_INCREMENT)
  public int id;
  private String menuCode;
  protected String name;

  public int getId()
  {
    return this.id;
  }

  public String getMenuCode()
  {
    return this.menuCode;
  }

  public String getName()
  {
    return this.name;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setMenuCode(String paramString)
  {
    this.menuCode = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }
}