package com.weex.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;
import java.util.Map;

public class NetworkActivity extends AppCompatActivity implements IWXRenderListener {

  private static String TEST_URL = "http://dotwe.org/raw/dist/6fe11640e8d25f2f98176e9643c08687.bundle.js";
  private WXSDKInstance mWXSDKInstance;
  private FrameLayout mContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_network);

    mContainer = (FrameLayout) findViewById(R.id.container);

    mWXSDKInstance = new WXSDKInstance(this);
    mWXSDKInstance.registerRenderListener(this);
    Map<String, Object> options = new HashMap<>();
    options.put(WXSDKInstance.BUNDLE_URL, TEST_URL);
    mWXSDKInstance.renderByUrl("WXSample", TEST_URL, options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityStart();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityResume();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityPause();
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityStop();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mWXSDKInstance != null) {
      mWXSDKInstance.onActivityDestroy();
    }
  }

  @Override
  public void onViewCreated(WXSDKInstance instance, View view) {
    if (view.getParent() != null) {
      ((ViewGroup) view.getParent()).removeView(view);
    }
    mContainer.addView(view);
  }

  @Override
  public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

  }

  @Override
  public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

  }

  @Override
  public void onException(WXSDKInstance instance, String errCode, String msg) {

  }
}
