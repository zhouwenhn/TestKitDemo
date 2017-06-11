package com.chowen.apackage.testkitdemo;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.chowen.apackage.testkitdemo.animator.AnimatorPage;
import com.chowen.apackage.testkitdemo.dianshang_home_page.listview.HomeListPage;
import com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview.HomeRecyclerScrollViewPage;
import com.chowen.apackage.testkitdemo.dianshang_home_page.recyclerview.HomeRecyclerViewPage;
import com.chowen.apackage.testkitdemo.horgridview.HorizontalFragment;
import com.chowen.apackage.testkitdemo.horgridview.HorizontalGridViewFragment;
import com.chowen.apackage.testkitdemo.rxbus.RxBus;
import com.chowen.apackage.testkitdemo.rxbus.RxBusTest;
import com.chowen.apackage.testkitdemo.rxbus.StudentEvent;
import com.chowen.apackage.testkitdemo.utils.L;
import com.chowen.apackage.testkitdemo.utils.TimeUtil;
import com.chowen.apackage.testkitdemo.viewpage.ViewPagerFragment;

import java.io.File;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import rx.functions.Action1;
import server.IMyAidlInterface;

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

    private volatile int num = 1;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private IMyAidlInterface iMyAidlInterface;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlInterface=null;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int check = getActivity().checkCallingOrSelfPermission("com.ryg.chapter_2.permission.ACCESS_BOOK_SERVICE");
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

        RxBus.getInstance().toObserverable(StudentEvent.class)
                .subscribe(new Action1<StudentEvent>() {
                    @Override
                    public void call(StudentEvent studentEvent) {
                        L.e("id:" + studentEvent.getId() + "  name:" + studentEvent.getName());
                    }
                });
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

    float startX, startY;

    private void initViews() {

        Intent intent = new Intent();
        intent.setAction("intent.action.service");
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        mView.findViewById(R.id.btn_bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    iMyAidlInterface.sendHandlerMsg("chowen>>>2222277777=");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


        mView.findViewById(R.id.btn_float).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                final View view1 = inflater.inflate(R.layout.float_view, null);

                final WindowManager manager = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                params.format = PixelFormat.RGBA_8888;
//                params.gravity = Gravity.TOP | Gravity.CENTER;
                manager.addView(view1, params);

                view1.setOnTouchListener(new View.OnTouchListener() {

                    float mLastStartX;
                    float mLastStartY;

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        startX = motionEvent.getRawX();
                        startY = motionEvent.getRawY() - 25;

                        L.i("startP", "startX" + startX + "====startY"
                                + startY);

                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                mLastStartX = motionEvent.getX();
                                mLastStartY = motionEvent.getY();
                                L.i("mLastStartX", "mLastStartY" + mLastStartX + "====startY"
                                        + mLastStartY);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                params.x = (int) (startX-mLastStartX);
                                params.y = (int) (startY-mLastStartY);

                                manager.updateViewLayout(view1, params);
                                break;
                            case MotionEvent.ACTION_UP:
                                params.x = (int) (startX-mLastStartX);
                                params.y = (int) (  startY-mLastStartY);

                                manager.updateViewLayout(view1, params);
                                mLastStartX = mLastStartY = 0;
                                break;
                        }


                        return false;
                    }
                });
            }
        });

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
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
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
        mView.findViewById(R.id.btn_grid_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(HorizontalGridViewFragment.newInstance());
            }
        });
        mView.findViewById(R.id.btn_rxbus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(HorizontalFragment.newInstance());
//                L.e("Current##"+TimeUtil.getCurrent(getContext()));
            }
        });


        mView.findViewById(R.id.btn_volatile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long s = System.currentTimeMillis();
                for (int i = 0; i < 5; i++) {
                    new Thread(new MyThread()).start();
                }
                L.e("MyThread>>Valitile= cost_time=" + (System.currentTimeMillis() - s));
            }
        });
        mView.findViewById(R.id.btn_clipdrawable).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(ClipDrawableViewPage.newInstance());
            }
        });
        mView.findViewById(R.id.btn_mutil_proccess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClassName("activity.main.permissiontest", "activity.main.permissiontest.SecondActivity");
                startActivityForResult(intent, 1001);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class MyThread implements Runnable {

        @Override
        public void run() {
            L.e("MyThread>>Valitile=" + getVolatileNum());
        }
    }

    private int getVolatileNum() {
        // TODO: 2017/5/29  //valotile 不具备操作的原子性-依赖本身，在多线程有并发问题 --》synchronized

        return num++;
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

    public void setComponentEnabledSetting(View v) {
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
