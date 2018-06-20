package com.qsz.elemedemo.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayoutScrollBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qsz.elemedemo.R;
import com.qsz.elemedemo.util.DensityUtil;
import com.qsz.elemedemo.view.fragment.CommentFragment;
import com.qsz.elemedemo.view.fragment.EmptyFragment;
import com.qsz.elemedemo.view.fragment.MenuFragment;
import com.qsz.elemedemo.view.widget.indicator.Indicator;
import com.qsz.elemedemo.view.widget.indicator.IndicatorViewPager;
import com.qsz.elemedemo.view.widget.indicator.OnTransitionTextListener;
import com.qsz.elemedemo.view.widget.indicator.slidebar.ColorBar;
import com.qsz.elemedemo.view.widget.nested.NestedViewPager;

/**
 * @author QSZ
 */
public class MainActivity extends AppCompatActivity implements AppBarLayoutScrollBehavior
        .OnAppBarListener {

    private final String[] TITLES = {
            "点餐",
            "评论",
            "商家"
    };
    // 默认选中
    private final int DEFAULT_SELECTED_TAB = 0;

    private final int PAGE_COUNT = TITLES.length;
    private MenuFragment mMenuFragement;
    private Fragment mCommentFragment;
    private Fragment mShopDetailFragment;

    private NestedViewPager mViewPager;
    private IndicatorViewPager mIndicatorViewPager;

    private AppBarLayout mAppBarLayout;
    private AppBarLayoutScrollBehavior mAppBarLayoutBehavior;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.argb(100, 0, 0, 0));
        }
        initAppBar();
        initViewPager();
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.viewpager);
        Indicator indicator = findViewById(R.id.sliding_tabs);

        final int selectColor = ContextCompat.getColor(this, R.color.colorAccent);
        int unSelectColor = ContextCompat.getColor(this, R.color.home_black);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor,
                unSelectColor));

        mViewPager.setOffscreenPageLimit(PAGE_COUNT);

        mIndicatorViewPager = new IndicatorViewPager(indicator, mViewPager);
        mIndicatorViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mIndicatorViewPager.setCurrentItem(DEFAULT_SELECTED_TAB, false);

        ColorBar fixedWidthColorBar = new ColorBar(this, R.drawable.bg_scrollbar,
                DensityUtil.dip2px(this, 2));
        fixedWidthColorBar.setWidth(DensityUtil.dip2px(this, 30));
        indicator.setScrollBar(fixedWidthColorBar);
    }

    private void initAppBar() {
        mAppBarLayout = findViewById(R.id.appbar_layout);
        mAppBarLayoutBehavior = new AppBarLayoutScrollBehavior(this, this);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        params.setBehavior(mAppBarLayoutBehavior);
        mAppBarLayout.setLayoutParams(params);
    }

    @Override
    public void onTitleBarItemClick(int id) {
        Toast.makeText(this, "onTitleBarItemClick isSearchBtn:" + (id == R.id.iv_title_search)
                , Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void initEnd(int miniTopHeight) {
        if (mViewPager != null) {
            mViewPager.setViewHeight(miniTopHeight);
        }
        findViewById(R.id.tvLoading).setVisibility(View.GONE);
    }

    private class TabAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        public TabAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container,
                                  int currentSelectedIndex) {
            if (convertView == null) {
                convertView = MainActivity.this.getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = convertView.findViewById(R.id.tv_title);
            textView.setText(TITLES[position]);
            textView.setTextColor(getResources().getColor(R.color.home_black));
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            switch (position) {
                case 0:
                    if (mMenuFragement == null) {
                        mMenuFragement = new MenuFragment();
                    }
                    return mMenuFragement;
                case 1:
                    if (mCommentFragment == null) {
                        mCommentFragment = new CommentFragment();
                    }
                    return mCommentFragment;
                case 2:
                    if (mShopDetailFragment == null) {
                        mShopDetailFragment = new EmptyFragment();
                    }
                    return mShopDetailFragment;
                default:
            }
            if (mMenuFragement == null) {
                mMenuFragement = new MenuFragment();
            }
            return mMenuFragement;
        }
    }
}
