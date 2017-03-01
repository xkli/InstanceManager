package com.weex.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IndexActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_index);

    findViewById(R.id.btn_local).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(IndexActivity.this, LocalActivity.class));
      }
    });

    findViewById(R.id.btn_network).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(IndexActivity.this, NetworkActivity.class));
      }
    });

    findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Uri data=Uri.parse("http://com.weex.org/example?bundleUrl=http://dotwe.org/raw/dist/f44d4664392584889268f1602d78671f.bundle.js");
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(data);
        intent.addCategory(WXFragmentActivity.CUSTOM_CATEGROY);
        startActivity(intent);
      }
    });
  }
}
