package com.marcy.viewtest.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.marcy.viewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcy on 2019/4/22
 */
public class MyBaseAdapter<T> extends RecyclerView.Adapter<BaseBindingViewHolder> {
    private static final int MESSAGE_LOADING_MORE = 111;
    private final String TAG = this.getClass().getSimpleName();

    private final int TYPE_EMPTY = 0;
    private final int TYPE_ERROR = 1;
    private final int TYPE_LOADING = 2;
    private final int TYPE_CONTENT = 3;

    private Context mContext;
    private OnItemClick mOnItemClickListener;

    //是否是反转数据模式
    private boolean mIsReverse;
    //是否可加载更多数据
    private boolean mCanLoadingMore;
    //当前是否正在加载更多
    private boolean mIsLoadingMore = false;
    //当前模式
    protected RecyclerViewStatus mStatus = RecyclerViewStatus.Loading;
    //基础数据
    protected List<T> mData;
    //加载中回调用handler
    private Handler mLoadingHandler;
    //加载更多回调监听
    protected LoadingCallback mLoadingCallback;
    //当前所在页面
    private int mCurrentPage = -1;
    //当前布局
    private int mLayout;

    public MyBaseAdapter(Context context , @LayoutRes int layoutRes) {
        mData = new ArrayList<>();
        mContext = context;
        mIsReverse = false;
        mCanLoadingMore = false;
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        mLoadingHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_LOADING_MORE:
                        if (mLoadingCallback != null) {
                            //加载更多，值为当前页+1。当加载成功后，当前页才进行自增赋值。
                            mLoadingCallback.onLoadingStart(mCurrentPage + 1);
                        }
                        break;
                }
            }
        };
        mLayout = layoutRes;
    }


    @NonNull
    @Override
    public BaseBindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        BaseBindingViewHolder viewHolder;
        View view = LayoutInflater.from(mContext).inflate(viewType , viewGroup , false);
        if(view != null) {
            viewHolder = new BaseBindingViewHolder(view);
            if (viewType == R.layout.item_rv_empty || viewType == R.layout.item_rv_error || viewType == R.layout.activity_main) {

            } else {
                viewHolder.setOnItemClick(mOnItemClickListener);
                viewHolder.setWidth(viewGroup.getWidth());
                viewHolder.setHeight(viewGroup.getHeight());
            }
            return viewHolder;
        }else{
            throw new RuntimeException("viewHolder异常");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder baseBindingViewHolder, int i) {
        baseBindingViewHolder.setItemCount(getItemCount());
        baseBindingViewHolder.setReverse(mIsReverse);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 || position == getItemCount() - 1){
            Object item = getItem(position);
            return item.getLayout;
        }
    }

    private Object getItem(int position){
        int size = mData.size();
        if(size == 0){
            return null;
        }
        if(mCanLoadingMore){
            if(!mIsLoadingMore){
                T item = mData.get(size - 1);
                if (!(item instanceof FooterItem)) {//最后一个条目不是footer item
                    mIsLoadingMore = true;
                    mLoadingHandler.removeMessages(MESSAGE_LOADING_MORE);
                    mLoadingHandler.obtainMessage(MESSAGE_LOADING_MORE,  mCurrentPage).sendToTarget();
                }
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private synchronized void applyData(RecyclerViewStatusData statusData){
        RecyclerViewStatus status = statusData.getmStatus();
        switch (status){
            case Content:
                mCanLoadingMore = statusData.isHaveNext();
                List data = (List) statusData.getData();
                //若此前数据不为包含数据模式（错误或加载或空数据listitem占据页面中），则清空列表
                if(!this.mStatus.equals(RecyclerViewStatus.Content)){
                    this.mData.clear();
                }
                this.mStatus = status;
                this.mData.addAll(data);
                notifyDataSetChanged();
                break;
            case Empty:
                this.mCanLoadingMore = false;
                this.mStatus = status;
                setStatusItem(new EmptyItem());
                break;
            case Error:
                //仅仅当首页无数据时直接替换错误Item
                if (!mCanLoadingMore || mData.size() == 0) {
                    setStatusItem(new ErrorItem());
                }else{
                    Toast.makeText(mContext, "出错", Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
                break;
            case Loading:
                mIsLoadingMore = true;//已经在loading,禁止loading状态
                if (!mCanLoadingMore || mData.size() == 0/*强制更新内容*/) {
                    mStatus = status;
                    if(this.mData.size() == 1){
                        T t = this.mData.get(0);
                        if(!(t instanceof LoadingItem)){
                            setStatusItem(new LoadingItem());
                        }
                    }else{
                        setStatusItem(new LoadingItem());
                    }
                }else{
                    T item = this.mData.get(this.mData.size() - 1);
                    if(item instanceof LoadingMoreItem){

                    }else{
                        this.mData.addAll(new LoadingMoreItem());
                    }
                    notifyDataSetChanged();
                }
                break;
            default:
        }
    }

    protected void setStatusItem(Object item){
        this.mData.clear();
        List data = this.mData;
        data.add(item);
        notifyDataSetChanged();
    }

    public interface LoadingCallback {
        void onLoadingStart(int page);
    }

}
