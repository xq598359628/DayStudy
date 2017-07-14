package com.daystudy.daystudy.material.floatbtn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daystudy.daystudy.R;

import java.util.List;

/**
 * Description:
 * Author     : xq
 * Date       : 2017/5/22
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.VH> {
    private List<String> mData;

    public ImageAdapter( List<String> data) {
        mData = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img,parent,false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.mTv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        public final ImageView mIv;
        public final TextView mTv;

        public VH(View itemView) {
            super(itemView);
            mIv = (ImageView) itemView.findViewById(R.id.iv);
            mTv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
