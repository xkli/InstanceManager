package com.weex.sample;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import java.util.HashMap;


public class WeexFragment extends Fragment implements IWXRenderListener {

  private static final String TAG = "WXFragment";

  private String mBundleUrl;
  private FrameLayout mContainer;
  private WXSDKInstance mWXSDKInstance;

  public WeexFragment() {
  }

  public static WeexFragment newInstance(String bundleUrl) {
    WeexFragment fragment = new WeexFragment();
    Bundle args = new Bundle();
    args.putString(WXSDKInstance.BUNDLE_URL, bundleUrl);
    fragment.setArguments(args);
    return fragment;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    Log.e(TAG, "into--[onAttach]");
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.e(TAG, "into--[onCreate]");

    View view = View.inflate(getActivity(), R.layout.fragment_weex, null);
    mContainer = (FrameLayout) view.findViewById(R.id.fragment_container);

    mBundleUrl = getArguments() != null ? getArguments().getString(WXSDKInstance.BUNDLE_URL) : null;
    mWXSDKInstance = new WXSDKInstance(getActivity());
    mWXSDKInstance.registerRenderListener(this);
    HashMap<String, Object> options = new HashMap<>();
    options.put(WXSDKInstance.BUNDLE_URL, mBundleUrl);
    mWXSDKInstance.renderByUrl("Weex Fragment Sample", mBundleUrl, options, null, WXRenderStrategy.APPEND_ASYNC);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    if (mContainer.getParent() != null) {
      ((ViewGroup) mContainer.getParent()).removeView(mContainer);
    }
    Log.e(TAG, "into--[onCreateView]");
    return mContainer;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Log.e(TAG, "into--[onActivityCreated]");
  }

  @Override
  public void onStart() {
    super.onStart();
    Log.e(TAG, "into--[onStart]");
  }

  @Override
  public void onResume() {
    super.onResume();
    Log.e(TAG, "into--[onResume]");
  }

  @Override
  public void onPause() {
    super.onPause();
    Log.e(TAG, "into--[onPause]");
  }

  @Override
  public void onStop() {
    super.onStop();
    Log.e(TAG, "into--[onStop]");
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    Log.e(TAG, "into--[onDestroyView]");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.e(TAG, "into--[onDestroy]");
  }

  @Override
  public void onDetach() {
    super.onDetach();
    Log.e(TAG, "into--[onDetach]");
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    Log.e(TAG, "into--[onLowMemory]");
  }

  @Override
  public void onTrimMemory(int level) {
    super.onTrimMemory(level);
    Log.e(TAG, "into--[onTrimMemory]");
  }

  @Override
  public void onViewCreated(WXSDKInstance instance, View view) {
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
