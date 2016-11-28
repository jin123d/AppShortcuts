package com.jin123d.code;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        //shortCuts();
        HashMap<String, String> maps = new HashMap<>();
        maps.put("aaa", null);
        maps.put("bbb", "bbb");
        maps.put(null, "ccc");
        maps.put("ccc", "ccc");
        tv.setText(getUrl("http://baidu.com/", maps));


        //-----------------
        OkGo.get(getUrl("http://baidu.com/", maps))// 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                });
    }


    private void shortCuts() {
        ShortcutManager shortcutManager = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            shortcutManager = getSystemService(ShortcutManager.class);
            ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                    .setShortLabel("Web site")
                    .setLongLabel("qq")
                    .setIcon(Icon.createWithResource(this, R.drawable.add))
                    .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/")))
                    .build();

            shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));
        }
    }


    /**
     * 获取访问的url
     *
     * @param baseUrl   baseUrl
     * @param urlParams 参数
     * @return url
     */
    public static String getUrl(String baseUrl, HashMap<String, String> urlParams) {
        // 添加url参数
        if (urlParams != null && urlParams.size() > 0) {
            StringBuilder sb = null;
            for (Map.Entry<String, String> it : urlParams.entrySet()) {
                String key = it.getKey();
                String value = it.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    if (sb == null) {
                        sb = new StringBuilder();
                        sb.append("?");
                    } else {
                        sb.append("&");
                    }
                    sb.append(key).append("=").append(value);
                }
            }
            if (sb != null) {
                baseUrl = TextUtils.concat(baseUrl, sb).toString();
            }
        }
        return baseUrl;
    }

    public void btn(View view) {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));
    }
}
