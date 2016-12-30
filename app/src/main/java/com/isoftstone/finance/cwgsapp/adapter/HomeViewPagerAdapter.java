package com.isoftstone.finance.cwgsapp.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.external.imageloader.SmartImageTask;
import com.isoftstone.finance.cwgsapp.external.imageloader.SmartImageView;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.HomeAd;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.RollPagerView;
import com.isoftstone.finance.cwgsapp.manage.CwgsApplication;

import java.util.ArrayList;

public class HomeViewPagerAdapter extends LoopPagerAdapter
{
  ArrayList<HomeAd> homeAds;
  private String url = "http://images.cnitblog.com/blog/430074/201302/01220037-4e6a57c1199748fea9f8391e7e0548d7.jpg";

  public HomeViewPagerAdapter(RollPagerView paramRollPagerView, ArrayList<HomeAd> paramArrayList)
  {
    super(paramRollPagerView);
    this.homeAds = paramArrayList;
  }

  public int getRealCount()
  {
    return this.homeAds.size();
  }

  public View getView(ViewGroup paramViewGroup, int paramInt)
  {
    this.url = ((HomeAd)this.homeAds.get(paramInt)).getUrl();
    String str = ((HomeAd)this.homeAds.get(paramInt)).getTitle();
    View localView = View.inflate(CwgsApplication.getAppContext(), R.layout.layout_item_homevp, null);
    SmartImageView localSmartImageView = (SmartImageView)localView.findViewById(R.id.siv_rl);
    TextView localTextView = (TextView)localView.findViewById(R.id.tv_rl_text);
    localSmartImageView.setImageResource(R.mipmap.common_banner);
    localSmartImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    localSmartImageView.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    localSmartImageView.setImageUrl(this.url, new SmartImageTask.OnCompleteListener()
    {
      public void onFail()
      {
      }

      public void onSuccess(Bitmap paramAnonymousBitmap)
      {
      }
    });
    localTextView.setText(str);
    return localView;
  }
}