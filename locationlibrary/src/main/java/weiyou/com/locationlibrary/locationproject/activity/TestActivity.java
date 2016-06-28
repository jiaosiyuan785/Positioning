package weiyou.com.locationlibrary.locationproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import weiyou.com.locationlibrary.locationproject.baidu.BaiDuLocation;
import weiyou.com.locationlibrary.locationproject.bean.BaiDuLocationBean;
import weiyou.com.locationlibrary.locationproject.bean.GaoDeLocationBean;
import weiyou.com.locationlibrary.locationproject.gaode.GaoDeLocation;
import weiyou.com.locationlibrary.locationproject.location.BaiDuLocationInterFace;
import weiyou.com.locationlibrary.locationproject.location.GaoDeLocationInterFace;
import weiyou.com.locationproject.R;


public class TestActivity extends AppCompatActivity {
    TextView gaode_text;
    TextView baidu_text;
    GaoDeLocation gaoDeLocation = new GaoDeLocation();
    BaiDuLocation baiDuLocation = new BaiDuLocation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        gaode_text = (TextView) findViewById(R.id.gaode_text);
        baidu_text = (TextView) findViewById(R.id.baidu_text);
    }

    /*
    * 启动高德定位
    * */
    public void startedGaoDeLocation(View view) {
        gaoDeLocation.location(this, 2, 1000, new GaoDeLocationInterFace() {
            @Override
            public void gaodeLocation(GaoDeLocationBean gb) {
                gaode_text.setText(gb.toString());
            }


        });
    }

    /*
    * 检查高德定位是否还在运行
    * */
    public void isStatGaoDeOnClick(View view) {
        String str = "高德定位已经停止定位";
        if (gaoDeLocation.isStarted()) {
            str = "高德定位正在运行";
        } else {
            str = "高德定位已经停止定位";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /*
    * 停止高德定位
    * */
    public void StopGaoDeRunning(View view) {
        gaoDeLocation.stopLocation();
        gaode_text.setText("高德定位返回数据显示!");
    }

    /*
  * 启动百度定位
  * */
    public void startedBaiDuLocation(View view) {
        baiDuLocation.location(this, 2, "bd09ll", 1000, new BaiDuLocationInterFace() {
            @Override
            public void baidulocation(BaiDuLocationBean db) {
                baidu_text.setText(db.toString());
            }
        });
    }

    /*
    * 检查百度定位是否还在运行
    * */
    public void isStatBaiDuOnClick(View view) {
        String str = "百度定位已经停止定位";
        if (baiDuLocation.isStarted()) {
            str = "百度定位正在运行";
        } else {
            str = "百度定位已经停止定位";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /*
    * 停止百度定位
    * */
    public void StopBaiDuRunning(View view) {
        baiDuLocation.stopLocation();
        baidu_text.setText("百度定位返回数据显示!");
    }
}
