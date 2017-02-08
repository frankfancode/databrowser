package com.frankfancode.databrowser.sharepreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SPListActivity extends Activity implements ExpandableListView.OnChildClickListener {
    private SwipeRefreshLayout mSwipeLayout;
    private ExpandableListView mSpLV;

    private PopupWindow mPopupWindow;
    View popupView;
    private TextView mDetailTv;
    private List<String> mSpNameList;
    private HashMap<String, List<String>> mKeyListMap = null;
    private ExpandableListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_list);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSpLV = (ExpandableListView) findViewById(R.id.sp_list);
        initPopup();

        mSpNameList = new ArrayList<>();
        mKeyListMap = new HashMap<>();
        mAdapter = new ExpandableListAdapter(this, mSpNameList, mKeyListMap);
        mSpLV.setAdapter(mAdapter);

        mSpLV.setOnChildClickListener(this);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        getData();
    }

    private void getData() {
        mSpNameList.clear();
        mKeyListMap.clear();
        File prefsdir = new File(getApplicationInfo().dataDir, "shared_prefs");

        if (prefsdir.exists() && prefsdir.isDirectory()) {
            String[] spFileList = prefsdir.list();
            if (spFileList != null && spFileList.length > 0) {

                for (int i = 0; i < spFileList.length; i++) {
                    String spName = spFileList[i];
                    mSpNameList.add(spName);
                    List<String> keyList = generateList(spName);
                    if (keyList != null && !keyList.isEmpty()) {
                        mKeyListMap.put(spName, keyList);
                    }
                }
            }
        }

        mAdapter.notifyDataSetChanged();
        mSwipeLayout.setRefreshing(false);
    }

    private List<String> generateList(String spName) {
        if (spName.endsWith(".xml")) {
            spName = spName.substring(0, spName.length() - 4);
        }
        List<String> keyList = null;
        SharedPreferences sp = getSharedPreferences(spName, Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sp.getAll();

        if (allEntries != null && !allEntries.isEmpty()) {
            keyList = new LinkedList<>();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                keyList.add(entry.getKey());
            }
        }
        return keyList;

    }


    private void initPopup() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;

        popupView = getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
        mDetailTv = (TextView) popupView.findViewById(R.id.sp_detail_tv);
        mPopupWindow = new PopupWindow(popupView, (int) (w_screen * 0.9), (int) (h_screen * 0.8), true);

        popupView.requestLayout();
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

    }

    private void showPopup(String s) {
        mPopupWindow.showAtLocation(mSwipeLayout, Gravity.CENTER, 0, 0);
        popupView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anm_in));
        if (s == null) {
            s = "";
        }
        if (mDetailTv != null) {
            mDetailTv.setText(s);
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        String spName = mSpNameList.get(groupPosition);
        String key = mKeyListMap.get(spName).get(childPosition);
        if (spName.endsWith(".xml")) {
            spName = spName.substring(0, spName.length() - 4);
        }
        SharedPreferences sp = getSharedPreferences(spName, Context.MODE_PRIVATE);
        Map<String, ?> all = sp.getAll();
        Object value = all.get(key);

        if (value != null) {
            showPopup(String.valueOf(value));

        }


        return true;
    }
}
