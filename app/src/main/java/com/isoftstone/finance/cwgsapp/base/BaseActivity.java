package com.isoftstone.finance.cwgsapp.base;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.facebook.stetho.Stetho;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseActivity extends FragmentActivity
{
  public static BaseActivity activity;
  static boolean isActive = true;
  static List<BaseActivity> mActivities = new LinkedList();
  int gesCount = 0;

  protected abstract void init();

  protected abstract void initView();

  public boolean isAppOnForeground()
  {
    ActivityManager localActivityManager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
    String str = getApplicationContext().getPackageName();
    List list = localActivityManager.getRunningAppProcesses();
    if(list != null){
      Iterator iterator = list.iterator();
      ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)iterator.next();
      if((!runningAppProcessInfo.processName.equals(str)) || (runningAppProcessInfo.importance != 100)){
        return true;
      }
    }
    return false;
  }

  public void killAll()
  {
    synchronized (mActivities)
    {
      ArrayList localArrayList = new ArrayList(mActivities);
      Iterator localIterator = localArrayList.iterator();
      if (localIterator.hasNext())
        ((BaseActivity)localIterator.next()).finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    System.out.println("BaseActivity");
    synchronized (mActivities)
    {
      mActivities.add(this);
      init();
      initView();
      Stetho.initializeWithDefaults(this);
      return;
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    synchronized (mActivities)
    {
      mActivities.remove(this);
      return;
    }
  }

  protected void onPause()
  {
    super.onPause();
    activity = null;
  }

  protected void onRestart()
  {
    super.onRestart();
  }

  protected void onResume()
  {
//    super.onResume();
//    activity = this;
//    if ((activity instanceof GestureLockActivity));
//    do
//    {
//      do
//      {
//        return;
//        if (isActive)
//          break;
//        isActive = true;
//      }
//      while (!getSharedPreferences("gesture", 0).getBoolean("gestureLock", false));
//      startActivity(new Intent(activity, GestureLockActivity.class));
//      this.gesCount = (1 + this.gesCount);
//      return;
//    }
//    while ((!(activity instanceof PowerActivity)) || (this.gesCount > 0));
//    SharedPreferences localSharedPreferences1 = getSharedPreferences("login", 0);
//    SharedPreferences localSharedPreferences2 = getSharedPreferences("gesture", 0);
//    if ((localSharedPreferences1.getBoolean("Autologin", false)) && (localSharedPreferences2.getBoolean("gestureLock", false)))
//    {
//      startActivity(new Intent(activity, GestureLockActivity.class));
//      this.gesCount = (1 + this.gesCount);
//    }
//    localSharedPreferences1.edit().putBoolean("Autologin", true).commit();
  }

  protected void onStop()
  {
    super.onStop();
    if (!isAppOnForeground())
      isActive = false;
  }
}