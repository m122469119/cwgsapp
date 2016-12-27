package com.isoftstone.finance.cwgsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {
    Button btn_in;
    private ImageView[] iv_imageViews;
    private ImageView[] iv_tip;
    private int[] imgidarr;
    public int mposition = 0;
    private ViewPager viewPager;

    private void setTip(int paramInt) {
        this.iv_tip[paramInt].setBackgroundResource(this.imgidarr[paramInt]);
    }

    protected void guidePager() {
        View view = View.inflate(this, R.layout.activity_guide, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        this.btn_in = ((Button) findViewById(R.id.guide_btn));
        this.btn_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                GuideActivity.this.startActivity(intent);
                GuideActivity.this.finish();
            }
        });
//        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.activity_guide);
        this.viewPager = ((ViewPager) findViewById(R.id.guide_vp));
        this.imgidarr = new int[]{R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
        this.iv_tip = new ImageView[this.imgidarr.length];
        for (int i = 0; i < this.iv_tip.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            imageView.setBackgroundResource(imgidarr[i]);
            this.iv_tip[i] = imageView;
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.leftMargin = 5;
//            layoutParams.rightMargin = 5;
//            this.viewPager.addView(imageView, layoutParams);

        }

        this.iv_imageViews = new ImageView[this.imgidarr.length];
        for (int j = 0; j < this.iv_imageViews.length; j++) {
            ImageView imageView = new ImageView(this);
            this.iv_imageViews[j] = imageView;
            this.iv_imageViews[j].setBackgroundResource(this.imgidarr[j]);
        }
        this.viewPager.setAdapter(new MyAdapter());
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mposition = position;
            }

            @Override
            public void onPageSelected(int position) {
                setTip(position % iv_imageViews.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mposition == iv_tip.length-1) {
                    btn_in.setVisibility(View.VISIBLE);
                }else{
                    btn_in.setVisibility(View.GONE);
                }
            }
        });
        this.viewPager.setCurrentItem(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isTaskRoot()) {
            finish();
            return;
        }
//        SharedPreferences preferences = getSharedPreferences("guide", 0);
//        if (preferences.getBoolean("firstlogin", true)) {
//            preferences.edit().putBoolean("firstlogin", false).commit();
            guidePager();
//            return;
//        }else{
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }
    }

    public class MyAdapter extends PagerAdapter {
        public MyAdapter() {

        }

        public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {

        }

        public int getCount() {
            return GuideActivity.this.iv_tip.length;
        }

        public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
            try {
                ((ViewPager) paramViewGroup).addView(GuideActivity.this.iv_imageViews[(paramInt % GuideActivity.this.iv_imageViews.length)], 0);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return GuideActivity.this.iv_imageViews[(paramInt % GuideActivity.this.iv_imageViews.length)];
        }

        public boolean isViewFromObject(View paramView, Object paramObject) {
            return paramView == paramObject;
        }
    }
}
