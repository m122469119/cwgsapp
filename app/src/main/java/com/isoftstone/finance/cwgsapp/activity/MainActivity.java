package com.isoftstone.finance.cwgsapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.isoftstone.finance.cwgsapp.R;
import com.isoftstone.finance.cwgsapp.base.BaseActivity;
import com.isoftstone.finance.cwgsapp.fragment.HomeFragment;
import com.isoftstone.finance.cwgsapp.fragment.PersonalFragment;
import com.isoftstone.finance.cwgsapp.fragment.SearchFragment;
import com.isoftstone.finance.cwgsapp.widget.tab.MainBottomTabLayout;
import com.isoftstone.finance.cwgsapp.widget.tab.MainFragmentAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_vp)
    ViewPager vp_main;
    @Bind(R.id.main_bottom_tablayout)
    MainBottomTabLayout mbtl_main;

    private MainFragmentAdapter mMainFragmentAdapter;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        init();
//    }

    protected void init()
    {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.mMainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        HomeFragment localHomeFragment = HomeFragment.newInstance(this);
        this.mMainFragmentAdapter.addFrag(localHomeFragment);
        SearchFragment localSearchFragment = new SearchFragment();
        this.mMainFragmentAdapter.addFrag(localSearchFragment);
        PersonalFragment localPersonalFragment = PersonalFragment.newInstance(this);
        this.mMainFragmentAdapter.addFrag(localPersonalFragment);
        this.vp_main.setOffscreenPageLimit(0);
        this.vp_main.setAdapter(this.mMainFragmentAdapter);
        this.mbtl_main.setViewPager(this.vp_main);
    }

    protected void initView()
    {
    }

//    protected void onResume()
//    {
//        super.onResume();
//    }
}
