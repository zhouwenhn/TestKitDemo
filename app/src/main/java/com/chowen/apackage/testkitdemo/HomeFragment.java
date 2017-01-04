package com.chowen.apackage.testkitdemo;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return mView;
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
