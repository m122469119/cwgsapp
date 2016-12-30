package com.isoftstone.finance.cwgsapp.manage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.isoftstone.finance.cwgsapp.pager.quice.QuiceItem;
import com.litesuits.orm.LiteOrm;
import java.io.File;

public class DataCleanManager
{
  public static void Exit(Context paramContext)
  {
    cleanInternalCache(paramContext);
    cleanFiles(paramContext);
    cleanExternalCache(paramContext);
    CwgsApplication.getLiteOrm().deleteAll(QuiceItem.class);
    paramContext.getSharedPreferences("gesture", 0).edit().clear().commit();
  }

  public static void cleanApplicationData(Context paramContext, String[] paramArrayOfString)
  {
    cleanInternalCache(paramContext);
    cleanExternalCache(paramContext);
    cleanDatabases(paramContext);
    cleanSharedPreference(paramContext);
    cleanFiles(paramContext);
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
      cleanCustomCache(paramArrayOfString[j]);
  }

  public static void cleanCustomCache(String paramString)
  {
    deleteFilesByDirectory(new File(paramString));
  }

  public static void cleanDatabaseByName(Context paramContext, String paramString)
  {
    paramContext.deleteDatabase(paramString);
  }

  public static void cleanDatabases(Context paramContext)
  {
    deleteFilesByDirectory(new File("/data/data/" + paramContext.getPackageName() + "/databases"));
  }

  public static void cleanExternalCache(Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted"))
      deleteFilesByDirectory(paramContext.getExternalCacheDir());
  }

  public static void cleanFiles(Context paramContext)
  {
    deleteFilesByDirectory(paramContext.getFilesDir());
  }

  public static void cleanInternalCache(Context paramContext)
  {
    deleteFilesByDirectory(paramContext.getCacheDir());
  }

  public static void cleanSharedPreference(Context paramContext)
  {
    deleteFilesByDirectory(new File("/data/data/" + paramContext.getPackageName() + "/shared_prefs"));
  }

  private static void deleteFilesByDirectory(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()) && (paramFile.isDirectory()))
    {
      File[] arrayOfFile = paramFile.listFiles();
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
        arrayOfFile[j].delete();
    }
  }
}