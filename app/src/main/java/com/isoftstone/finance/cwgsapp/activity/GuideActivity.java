package com.isoftstone.finance.cwgsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.isoftstone.finance.cwgsapp.R;

import butterknife.ButterKnife;

/**
* @项目名称：财务公司App
* @类描述：引导页面对应的activity
* @创建人：zwxiao
* @创建时间：2016/12/29 10:07
*/
public class GuideActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_in;
    private ImageView[] iv_imageViews;
    private ImageView[] iv_tip;
    private int[] imgidarr;
    public int mposition = 0;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        this.btn_in = ((Button) findViewById(R.id.guide_btn));
        this.btn_in.setOnClickListener(this);

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

    private void setTip(int paramInt) {
        for (int i = 0; i < this.iv_tip.length; i++) {
            if(paramInt==i){
                this.iv_tip[i].setBackgroundResource(R.mipmap.point);
            }else{
                this.iv_tip[i].setBackgroundResource(R.mipmap.nopoint);
            }
        }
    }

    protected void guidePager() {
//        this.btn_in = ((Button) findViewById(R.id.guide_btn));
//        this.btn_in.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
//                GuideActivity.this.startActivity(intent);
//                GuideActivity.this.finish();
//            }
//        });
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.guide_tip);
        this.viewPager = ((ViewPager) findViewById(R.id.guide_vp));
        this.imgidarr = new int[]{R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};
        this.iv_tip = new ImageView[this.imgidarr.length];
        //tip作用是提示当前图片是第几页面，根据图片个数添加tip个数
        for (int i = 0; i < this.iv_tip.length; i++) {
            //动态添加tip，第一个tip默认是选中的（使用point图片），其他未选中的（使用nopoint图片）
            ImageView tipImageView = new ImageView(this);
            tipImageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            this.iv_tip[i] = tipImageView;
            if(i == 0){
                this.iv_tip[i].setBackgroundResource(R.mipmap.point);
            }else{
                this.iv_tip[i].setBackgroundResource(R.mipmap.nopoint);
            }
            //每个tip之间的距离是5dp
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            viewGroup.addView(tipImageView, layoutParams);
        }

        this.iv_imageViews = new ImageView[this.imgidarr.length];
        for (int j = 0; j < this.iv_imageViews.length; j++) {
            ImageView backGroundImageView = new ImageView(this);
            this.iv_imageViews[j] = backGroundImageView;
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
                //最后一页显示按钮
                if (mposition == iv_tip.length-1) {
                    btn_in.setVisibility(View.VISIBLE);
                }else{
                    btn_in.setVisibility(View.GONE);
                }
            }
        });
        this.viewPager.setCurrentItem(0);
    }


    /**
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guide_btn:
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
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
