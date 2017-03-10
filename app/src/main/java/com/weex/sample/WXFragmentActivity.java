package com.weex.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class WXFragmentActivity extends AppCompatActivity {

  public static final String CUSTOM_CATEGROY = "weex.intent.category.fragment";
  private static final int MAX_INSTANCE = 1;
  private List<String> mPages = new ArrayList<>();
  private List<String> mTemps = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Uri uri = getIntent().getData();
    createNewWXInstance(uri, false);

  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    createNewWXInstance(intent.getData(), true);
  }

  private void createNewWXInstance(Uri uri, boolean addToBackStack) {
    if (uri != null) {
      String bundleUrl = uri.getQueryParameter("bundleUrl");
      if (!TextUtils.isEmpty(bundleUrl)) {
        mPages.add(bundleUrl);
        mTemps.add(bundleUrl);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mPages.size() > MAX_INSTANCE) {
          Fragment fragment = manager.findFragmentByTag(mTemps.remove(0));
          transaction.remove(fragment);
        }
        WeexFragment weexFragment = WeexFragment.newInstance(bundleUrl);
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.add(R.id.content_fragment, weexFragment, bundleUrl);
        transaction.commit();
      }
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      if (onWXBackPressed()) {
        return true;
      }
    }
    return super.onKeyDown(keyCode, event);
  }

  public boolean onWXBackPressed() {

    if (mPages.size() <= 1) {
      return false;
    }

    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentByTag(mPages.remove(mPages.size() - 1));
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.remove(fragment);
    Fragment fragment1 = manager.findFragmentByTag(mPages.get(mPages.size() - 1));
    if (fragment1 != null) {
      transaction.show(fragment1);
    } else if (fragment1 == null) {
      WeexFragment weexFragment = WeexFragment.newInstance(mPages.get(mPages.size() - 1));
      transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
      transaction.add(R.id.content_fragment, weexFragment, mPages.get(mPages.size() - 1));
    }
    transaction.commit();
    return true;
  }
}
