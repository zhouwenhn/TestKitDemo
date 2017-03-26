package com.chowen.apackage.testkitdemo;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.chowen.apackage.testkitdemo.animator.AnimatorPage;
import com.chowen.apackage.testkitdemo.dianshang_home_page.listview.HomeListPage;
import com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview.HomeRecyclerViewPage;
import com.chowen.apackage.testkitdemo.viewpage.ViewPagerFragment;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by zhouwen on 2016/tmall_12/3.
 * Description:
 */

public class HomeFragment extends SupportFragment {

    private View mView = null;

    private ComponentName mDefault;
    private ComponentName mDouble11;
    private ComponentName mDouble12;
    private PackageManager mPm;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (mView == null) {
            mView = inflater.inflate(R.layout.home_page, container, false);
        }

        mDefault = getActivity().getComponentName();

        mDouble11 = new ComponentName(
                getActivity(),
                "com.chowen.apackage.testkitdemo.Test11");
        mDouble12 = new ComponentName(
                getActivity(),
                "com.chowen.apackage.testkitdemo.Test12");
        mPm = getActivity().getPackageManager();
        initViews();

        //判断SDK版本是否大于等于19，大于就让他显示，小于就要隐藏，不然低版本会多出来一个
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //setTranslucentStatus(true);
        }
        return mView;
    }

    //test 沉寂式状态栏
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getActivity().getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initViews() {
        mView.findViewById(R.id.btn_view_pager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(ViewPagerFragment.newInstance());
            }
        });
        mView.findViewById(R.id.btn_11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeIcon11(view);
            }
        });

        mView.findViewById(R.id.btn_12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeIcon12(view);
            }
        });

        View view = mView.findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view);

        mView.findViewById(R.id.btn_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        //TextInputLayout
        final TextInputLayout textInputLayout = (TextInputLayout) mView.findViewById(R.id.til_pwd);

        EditText editText = textInputLayout.getEditText();
        textInputLayout.setHint("Password");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 4) {
                    textInputLayout.setError("Password error");
                    textInputLayout.setErrorEnabled(true);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mView.findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(HomeListPage.newInstance());
            }
        });

        mView.findViewById(R.id.btn_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(AnimatorPage.newInstance());
            }
        });

        mView.findViewById(R.id.btn_recycler_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(HomeRecyclerViewPage.newInstance());
            }
        });

        mView.findViewById(R.id.btn_animator_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(AnimatorPage.newInstance());
            }
        });

    }

    public void changeIcon11(View view) {
        disableComponent(mDefault);
        disableComponent(mDouble12);
        enableComponent(mDouble11);
    }

    public void changeIcon12(View view) {
        disableComponent(mDefault);
        disableComponent(mDouble11);
        enableComponent(mDouble12);
    }


    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void setComponentEnabledSetting(View v){
        PackageManager pm = getActivity().getPackageManager();
        pm.setComponentEnabledSetting(getActivity().getComponentName(),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(getActivity(),
                "com.chowen.apackage.testkitdemo.MainActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                ActivityManager am = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
    }
}
