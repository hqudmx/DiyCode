package com.plusend.diycode.model.user.presenter;

import android.util.Log;
import com.plusend.diycode.model.base.BaseData;
import com.plusend.diycode.model.base.BasePresenter;
import com.plusend.diycode.model.user.event.UserTopicsEvent;
import com.plusend.diycode.model.topic.view.TopicsView;
import com.plusend.diycode.model.user.event.UserFavoriteTopicsEvent;
import com.plusend.diycode.model.user.model.UserDataNetwork;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UserTopicsBasePresenter extends BasePresenter {
  private static final String TAG = "UserTopicsPresenter";

  private BaseData data;
  private TopicsView topicsView;

  public UserTopicsBasePresenter(TopicsView topicsView) {
    this.data = UserDataNetwork.getInstance();
    this.topicsView = topicsView;
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void showUserCreateTopics(UserTopicsEvent userTopicsEvent) {
    Log.d(TAG, "showUserCreateTopics: " + userTopicsEvent.getTopicList().size());
    topicsView.showTopics(userTopicsEvent.getTopicList());
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void showUserFavoriteTopics(UserFavoriteTopicsEvent event) {
    Log.d(TAG, "showUserFavoriteTopics: " + event.getTopicList().size());
    topicsView.showTopics(event.getTopicList());
  }

  public void getUserCreateTopics(String loginName, Integer offset) {
    ((UserDataNetwork) data).getUserCreateTopics(loginName, offset, null);
  }

  public void getUserFavoriteTopics(String loginName, Integer offset) {
    ((UserDataNetwork) data).getUserFavoriteTopics(loginName, offset, null);
  }

  @Override public void start() {
    Log.d(TAG, "register");
    EventBus.getDefault().register(this);
  }

  @Override public void stop() {
    Log.d(TAG, "unregister");
    EventBus.getDefault().unregister(this);
  }
}
