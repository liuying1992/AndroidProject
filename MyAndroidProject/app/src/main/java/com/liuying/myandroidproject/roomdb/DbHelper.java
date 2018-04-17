package com.liuying.myandroidproject.roomdb;

import android.arch.persistence.room.Room;
import com.liuying.myandroidproject.MyApplication;

/**
 * Created by liuying on 2018/4/16 17:42.
 * Email: ly1203575492@163.com
 */
public class DbHelper {

  public static DbHelper mDbHelper = null;
  public static AppDatabase mAppDatabase;

  public DbHelper() {
    mAppDatabase =
        Room.databaseBuilder(MyApplication.getInstance(), AppDatabase.class, "MyRoom.db").build();
  }

  public static final DbHelper getInstance() {
    synchronized (DbHelper.class) {
      if (mDbHelper == null) {
        mDbHelper = new DbHelper();
      }
    }
    return mDbHelper;
  }

  public AppDatabase getAppDataBase() {
    return mAppDatabase;
  }
}
