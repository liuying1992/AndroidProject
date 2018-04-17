package com.liuying.myandroidproject.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by liuying on 2018/4/16 17:39.
 * Email: ly1203575492@163.com
 */
@Dao public interface UserInfoDao {
  @Query("select * from t_user_info") Flowable<List<UserInfo>> getUserInfos();

  @Query("delete from t_user_info") int delete();

  @Query("delete from t_user_info where userId = :id") int deleteUser(int id);

  @Insert(onConflict = OnConflictStrategy.REPLACE) long insterUser(UserInfo user);
}
