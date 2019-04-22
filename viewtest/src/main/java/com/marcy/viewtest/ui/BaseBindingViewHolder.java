package com.marcy.viewtest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jeffyu on 2018/4/9.
 */

public class BaseBindingViewHolder extends RecyclerView.ViewHolder {
    protected OnItemClick mOnClickListener;
    protected int itemCount;
    protected boolean isReverse;

    public BaseBindingViewHolder(View itemView) {
        super(itemView);
    }

    public void setHeight(int height) {
        itemView.setMinimumHeight(height);
    }

    public void setWidth(int width) {
        itemView.setMinimumWidth(width);
    }

    public void setOnItemClick(OnItemClick onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void onClick(View view) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(view, getLayoutPosition());
        }
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }

    public boolean isLast() {
        int layoutPosition = getLayoutPosition();
        if (isReverse) {
            return layoutPosition == 0;
        } else {
            return layoutPosition == (getItemCount() - 1);
        }
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int mItemCount) {
        this.itemCount = mItemCount;
    }
}