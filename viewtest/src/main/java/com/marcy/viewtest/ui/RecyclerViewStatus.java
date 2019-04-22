package com.marcy.viewtest.ui;

/**
 * Created by Marcy on 2019/4/22
 * 状态定义，加载中，包含数据，空数据，错误数据
 */
public enum RecyclerViewStatus {
    Loading,
    Content,
    Empty,
    Error;

    public boolean hasContent(){return this == Content;}
}
