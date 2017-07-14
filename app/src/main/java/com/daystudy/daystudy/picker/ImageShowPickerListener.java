package com.daystudy.daystudy.picker;

import java.util.List;

/**
 * Author 姚智胜
 * Version V1.0版本
 * Description: picker点击事件监听
 * Date: 2017/4/15
 */

public interface ImageShowPickerListener {
    /**
     * 点击添加按钮的回调
     * @param remainNum 能够添加的剩余图片数量
     */
    void addOnClickListener(int remainNum);

    /**
     * 点击选择图片时的回调
     * @param list 当前图片的总数
     * @param position 点击的位置
     * @param remainNum 还能够添加的剩余图片数量
     */
    void picOnClickListener(List<ImageShowPickerBean> list, int position, int remainNum);

    /**
     * 删除图片时的回调
     * @param position 要删除图片的位置
     * @param remainNum 删除图片后还可以添加的剩余图片数量
     */
    void delOnClickListener(int position, int remainNum);
}
