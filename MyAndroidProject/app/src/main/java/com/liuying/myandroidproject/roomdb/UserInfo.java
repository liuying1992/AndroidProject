package com.liuying.myandroidproject.roomdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 用户基础表
 *
 * Created by liuying on 2018/4/16 17:36.
 * Email: ly1203575492@163.com
 */
@Entity(tableName = "t_user_info") public class UserInfo {
  @PrimaryKey private int userId;
  @ColumnInfo(name = "user_name") private String userName;
  @ColumnInfo(name = "user_address") private String address;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
