package com.marcy.androidintroduction.activitylifecycle.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Marcy on 2019/4/18
 */
public class SampleProvider extends ContentProvider {
    private Context mContext;
    DBHelper mDBHelper = null;
    SQLiteDatabase mDB = null;
    //唯一标识符
    public static final String AUTOHORITY = "com.marcy.sampleprovider";

    public static final int User_Code = 1;
    public static final int Job_Code = 2;

    //注册uri
    private static final UriMatcher mMatcher;
    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //若资源路径="content://com.marcy.sampleprovider/user，返回注册码User_Code"
        mMatcher.addURI(AUTOHORITY , "user" , User_Code);
        mMatcher.addURI(AUTOHORITY , "job" , Job_Code);

    }
    @Override
    public boolean onCreate() {
        mContext = getContext();
        //初始化
        mDBHelper = new DBHelper(mContext);
        mDB = mDBHelper.getWritableDatabase();
        mDB.execSQL("delete from user");
        mDB.execSQL("insert into user values(1,'Carson');");
        mDB.execSQL("insert into user values(2,'Kobe');");

        mDB.execSQL("delete from job");
        mDB.execSQL("insert into job values(1,'Android');");
        mDB.execSQL("insert into job values(2,'iOS');");
        return true;
    }

    @Nullable
    @Override
    public Cursor query( @NonNull Uri uri,  @Nullable String[] projection,  @Nullable String selection,  @Nullable String[] selectionArgs,  @Nullable String sortOrder) {
        String table = getTableName(uri);
        return mDB.query(table , projection , selection , selectionArgs , null , null , sortOrder , null);
    }

    
    @Nullable
    @Override
    public String getType( @NonNull Uri uri) {
        return null;
    }

    
    @Nullable
    @Override
    public Uri insert( @NonNull Uri uri,  @Nullable ContentValues values) {
        String table = getTableName(uri);
        mDB.insert(table , null , values);
        mContext.getContentResolver().notifyChange(uri , null);
        return uri;
    }

    @Override
    public int delete( @NonNull Uri uri,  @Nullable String selection,  @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( @NonNull Uri uri,  @Nullable ContentValues values,  @Nullable String selection,  @Nullable String[] selectionArgs) {
        return 0;
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (mMatcher.match(uri)){
            case User_Code:
                tableName = DBHelper.USER_TABLE_NAME;
                break;
            case Job_Code:
                tableName = DBHelper.JOB_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
