package com.isoftstone.finance.cwgsapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.adapter.HomeViewPagerAdapter;
import com.isoftstone.finance.cwgsapp.adapter.MainPagerAdapter;
import com.isoftstone.finance.cwgsapp.base.BaseActivity;
import com.isoftstone.finance.cwgsapp.base.BaseListener;
import com.isoftstone.finance.cwgsapp.external.ImageCycleView;
import com.isoftstone.finance.cwgsapp.external.LazyViewPager;
import com.isoftstone.finance.cwgsapp.external.MyViewPager;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.HomeAd;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.RollPagerView;
import com.isoftstone.finance.cwgsapp.external.rollvviewpager.hintview.IconHintView;
import com.isoftstone.finance.cwgsapp.manage.Constant;
import com.isoftstone.finance.cwgsapp.manage.ServerManager;
import com.isoftstone.finance.cwgsapp.pager.DoneApprovalPager;
import com.isoftstone.finance.cwgsapp.pager.WaitApprovalPager;
import com.isoftstone.finance.cwgsapp.responseBean.ImageIdQueryResult;
import com.isoftstone.finance.cwgsapp.responseBean.PictureInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    public static BaseActivity mactivity;
    HomeViewPagerAdapter adapter;
    boolean flag = true;
    ArrayList<HomeAd> homeAds = new ArrayList();

    List<ImageCycleView.ImageInfo> list;

    ProgressDialog pdg;

    ArrayList pagerList;
    @Bind(R.id.rl_homepager)
    RelativeLayout rl_Homepager;
    @Bind(R.id.roll_homepager)
    RollPagerView rpv_Homepager;
//    @Bind(R.id.rb_approvaled)
//    RadioButton rb_approvaled;
//    @Bind(R.id.rb_completed)
//    RadioButton rb_completed;
//    @Bind(R.id.rg_homepager)
//    RadioGroup rg_Homepager;
//    @Bind(R.id.homepager_img1)
//    ImageView iv_homepagerImg1;
//    @Bind(R.id.homepager_img2)
//    ImageView iv_homepagerImg2;
//    @Bind(R.id.pager_home)
//    MyViewPager mvp_pagerHome;

    private Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            public void onErrorResponse(VolleyError paramAnonymousVolleyError) {
                HomeFragment.this.pdg.dismiss();
                Toast.makeText(HomeFragment.this.getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private BaseListener<ImageIdQueryResult> imageidquerysuccessListener() {
        return new BaseListener<ImageIdQueryResult>(mactivity) {
            public void successResponse(ImageIdQueryResult paramAnonymousImageIdQueryResult) {
                System.out.print(paramAnonymousImageIdQueryResult);
                HomeFragment.this.list.clear();
                HomeFragment.this.homeAds.clear();
                for (int i = 0; i < paramAnonymousImageIdQueryResult.result.size(); i++) {
                    PictureInfo localPictureInfo = (PictureInfo) paramAnonymousImageIdQueryResult.result.get(i);
                    HomeFragment.this.homeAds.add(new HomeAd(localPictureInfo.remark, localPictureInfo.address));
                }
                HomeFragment.this.adapter.notifyDataSetChanged();
                HomeFragment.this.pdg.dismiss();
            }
        };
    }

    private void initPager() {
        if(this.homeAds != null && this.homeAds.size() > 0){
            this.homeAds.clear();
        }
        this.pagerList = new ArrayList();
        this.pagerList.add(new WaitApprovalPager(getActivity()));
        this.pagerList.add(new DoneApprovalPager(getActivity()));
//        this.mvp_pagerHome.setAdapter(new MainPagerAdapter(getActivity(), this.pagerList));
    }

    public static HomeFragment newInstance(BaseActivity paramBaseActivity) {
        HomeFragment localHomeFragment = new HomeFragment();
        mactivity = paramBaseActivity;
        return localHomeFragment;
    }

    public void iniScrollView() {
    }

    public void init() {
        initPager();
//        this.rg_Homepager.setOnCheckedChangeListener(new MyChecledListener());
//        this.mvp_pagerHome.setOnPageChangeListener(new MyPagerChangedListener());
//        this.rg_Homepager.check(R.id.rb_approvaled);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = View.inflate(paramViewGroup.getContext(), R.layout.pager_home, null);
        ButterKnife.bind(this, view);
        init();
        this.rl_Homepager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                HomeFragment.this.getActivity().finish();
            }
        });
        this.list = new ArrayList();
        if (this.flag) {
            this.pdg = new ProgressDialog(getActivity());
            this.pdg.show();
            this.pdg.setCancelable(false);
//            ServerManager.getImageIdQuery(imageidquerysuccessListener(), errorListener());
//            new StringBuilder().append(Constant.REQUEST_ADDRESS).append("mobileStartupLogo/findPictureById?id=").toString();
            this.rpv_Homepager.setPlayDelay(4000);
            this.rpv_Homepager.setAnimationDurtion(500);
            this.homeAds.add(new HomeAd("123", "www.baidu.com"));
            this.homeAds.add(new HomeAd("456", "www.baidu.com"));
            this.homeAds.add(new HomeAd("789", "www.baidu.com"));
            this.adapter = new HomeViewPagerAdapter(this.rpv_Homepager, this.homeAds);
            this.rpv_Homepager.setAdapter(this.adapter);
            this.rpv_Homepager.setHintView(new IconHintView(getContext(), R.mipmap.gesturesmallcircleblue, R.mipmap.gesturesmallcirclewhite));
            this.pdg.dismiss();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class MyChecledListener
            implements RadioGroup.OnCheckedChangeListener {
        MyChecledListener() {
        }

        public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt) {
            switch (paramInt) {
                default:
                    return;
//                case R.id.rb_approvaled:
//                    HomeFragment.this.iv_homepagerImg1.setVisibility(View.VISIBLE);
//                    HomeFragment.this.iv_homepagerImg2.setVisibility(View.INVISIBLE);
//                    HomeFragment.this.mvp_pagerHome.setCurrentItem(View.VISIBLE);
//                    break;
//                case R.id.rb_completed:
//                    HomeFragment.this.iv_homepagerImg1.setVisibility(View.INVISIBLE);
//                    HomeFragment.this.iv_homepagerImg2.setVisibility(View.VISIBLE);
//                    HomeFragment.this.mvp_pagerHome.setCurrentItem(1);
//                    break;
            }

        }
    }

    class MyPagerChangedListener
            implements LazyViewPager.OnPageChangeListener {
        MyPagerChangedListener() {
        }

        public void onPageScrollStateChanged(int paramInt) {
        }

        public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
        }

        public void onPageSelected(int paramInt) {
        }
    }
}