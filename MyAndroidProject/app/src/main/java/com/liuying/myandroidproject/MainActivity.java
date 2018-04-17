package com.liuying.myandroidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.liuying.myandroidproject.base.BaseActivity;
import com.liuying.myandroidproject.roomdb.DbHelper;
import com.liuying.myandroidproject.roomdb.UserInfo;
import com.liuying.myandroidproject.roomdb.UserInfoDao;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Created by liuying on 2018/4/16 17:04.
 * Email: ly1203575492@163.com
 */
public class MainActivity extends BaseActivity {
  @BindView(R.id.get) Button mGet;
  @BindView(R.id.insert) Button mInsert;
  @BindView(R.id.delete) Button mDelete;
  @BindView(R.id.txt_msg) TextView mTxtMsg;

  //private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
  private UserInfoDao mDao;
  private int userId = 999;
  private String mStringBuilder;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    mDao = DbHelper.getInstance().getAppDataBase().userInfoDao();
  }

  @OnClick({ R.id.get, R.id.delete, R.id.insert }) public void onClickListener(View view) {
    switch (view.getId()) {
      case R.id.get:
        mDao.getUserInfos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<UserInfo>>() {
              @Override public void accept(List<UserInfo> userInfos) throws Exception {
                if (userInfos != null) {
                  mStringBuilder = ("共查询到用户数据：" + userInfos.size() + "条") + ("\n");
                } else {
                  mStringBuilder = ("未查询到用户数据") + ("\n");
                }
                mTxtMsg.setText(mTxtMsg.getText().toString() + mStringBuilder);
              }
            });

        break;
      case R.id.delete:
        Flowable.create(new FlowableOnSubscribe<Integer>() {
          @Override public void subscribe(FlowableEmitter<Integer> e) throws Exception {
            e.onNext(mDao.delete());
          }
        }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Integer>() {
              @Override public void accept(Integer integer) throws Exception {
                mStringBuilder = "共删除" + (integer == null ? "0" : "" + integer) + "条数据" + "\n";
                mTxtMsg.setText(mTxtMsg.getText().toString() + mStringBuilder);
                Toast.makeText(getBaseContext(), "执行了查询", Toast.LENGTH_SHORT).show();
              }
            });

        break;
      case R.id.insert:
        Flowable.create(new FlowableOnSubscribe<Long>() {
          @Override public void subscribe(FlowableEmitter<Long> e) throws Exception {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(++userId);
            userInfo.setUserName("刘颖");
            userInfo.setAddress("浙江杭州");
            e.onNext(mDao.insterUser(userInfo));
          }
        }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<Long>() {
              @Override public void accept(Long integer) throws Exception {
                mStringBuilder = ("插入用户ID：" + (integer == null ? "0" : "" + integer)) + ("\n");
                mTxtMsg.setText(mTxtMsg.getText().toString() + mStringBuilder);
              }
            });
        break;
      default:
        break;
    }
  }

  @Override protected void onStop() {
    super.onStop();
    //mCompositeDisposable.clear();
  }
}
