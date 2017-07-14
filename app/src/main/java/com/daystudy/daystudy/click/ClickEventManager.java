package com.daystudy.daystudy.click;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.View;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/7/3
 */

public class ClickEventManager {

    private SparseArray<ClickEventType> mClickEventTypes = new SparseArray<>();

    /**
     * 添加点击事件
     *
     * @param clickId 点击view的Id
     * @param type    点击事件的具体实现子类
     */
    public void putClickType(@IdRes int clickId, @NonNull ClickEventType type) {
        mClickEventTypes.put(clickId, type);
        try {
            type.getActivity().findViewById(clickId).setOnClickListener((View.OnClickListener) type.getActivity());
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement OnClickListener");
        }
    }

    public void destory() {
        mClickEventTypes.clear();
        mClickEventTypes = null;
    }

    public void doClick(@IdRes int id){
        ClickEventType type = mClickEventTypes.get(id);
        if (type != null) {
            type.click();
        }
    }
}
