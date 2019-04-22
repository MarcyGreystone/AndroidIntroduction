package com.marcy.viewtest.ui;

import java.util.List;

/**
 * Created by Marcy on 2019/4/22
 */
public class RecyclerViewStatusData<T>{
    private boolean haveNext;
    private RecyclerViewStatus mStatus;
    private T data;

    public boolean isHaveNext() {
        return haveNext;
    }

    public RecyclerViewStatus getmStatus() {
        return mStatus;
    }

    public T getData() {
        return data;
    }
}
