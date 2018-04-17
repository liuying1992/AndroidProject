package com.liuying.myandroidproject.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * 数据库
 *
 * Created by liuying on 2018/4/16 17:40.
 * Email: ly1203575492@163.com
 */
@Database(entities = { UserInfo.class }, version = 1) public abstract class AppDatabase
    extends RoomDatabase {
  public abstract UserInfoDao userInfoDao();
}
