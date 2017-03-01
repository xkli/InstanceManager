package com.weex.sample.extend.module;

import android.content.Intent;
import android.net.Uri;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.weex.sample.WXFragmentActivity;

/**
 * Created by lixinke on 2017/2/20.
 */

public class EventModule extends WXModule {

  @JSMethod
  public void openUrl(String url){
    Uri data=Uri.parse("http://com.weex.org/example?bundleUrl="+url);
    Intent intent=new Intent(Intent.ACTION_VIEW);
    intent.setData(data);
    intent.addCategory(WXFragmentActivity.CUSTOM_CATEGROY);
    mWXSDKInstance.getContext().startActivity(intent);
  }
}
