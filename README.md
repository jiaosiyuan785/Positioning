# 用于定位的一个简单的方法
## 可以简单的使用百度和高德的定位
####前期准备
  首先，如果你需要申请key，[百度的在这里]{http://lbsyun.baidu.com/apiconsole/key/create},[高德的在这里]{http://lbs.amap.com/dev/#/}
####  `重点是你需要在AndroidManifest中做一些准备`
#####百度AndroidManifest
    application中添加
    ```Java
      <service android:name="com.baidu.location.f" android:enabled="true" android:process=":remote"></service>
      <meta-data
            android:name="com.baidu.lbsapi.API_KEY"
            android:value="key" />       //key:开发者申请的key
```
    application外添加权限
    ```Java
       <!-- 这个权限用于进行网络定位-->
       <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
       <!-- 这个权限用于访问GPS定位-->
       <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
       <!-- 用于访问wifi网络信息，wifi信息会用于进行网络定位-->
       <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
       <!-- 获取运营商信息，用于支持提供运营商信息相关的接口-->
       <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
       <!-- 这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
       <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
       <!-- 用于读取手机当前的状态-->
       <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
       <!-- 写入扩展存储，向扩展卡写入数据，用于写入离线定位数据-->
       <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
       <!-- 访问网络，网络定位需要上网-->
       <uses-permission android:name="android.permission.INTERNET" />
       <!-- SD卡读取权限，用户写入离线定位数据-->
       <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"></uses-permission>
    ```
#####高德AndroidManifest
    application中添加
    ```Java
      <service android:name="com.amap.api.location.APSService"></service>
      <meta-data android:name="com.amap.api.v2.apikey" android:value="key">//开发者申请的key      
            </meta-data>
```
 application外添加权限
    ```Java
     <!--用于进行网络定位-->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
<!--用于访问GPS定位-->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
<!--获取运营商信息，用于支持提供运营商信息相关的接口-->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
<!--用于访问wifi网络信息，wifi信息会用于进行网络定位-->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
<!--这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
<!--用于访问网络，网络定位需要上网-->
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
<!--用于读取手机当前的状态-->
<uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
<!--写入扩展存储，向扩展卡写入数据，用于写入缓存定位数据-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
    ```
    `Android6.0及以上系统可以参考`[Android6.0权限说明章节]{http://lbs.amap.com/api/android-location-sdk/guide/android-6-0-permission/#t1}
####百度定位
```Java
new BaiDuLocation().location(this, 2, "bd09ll", 0, new BaiDuLocationInterFace() {
            @Override
            public void baidulocation(BaiDuLocationBean db) {
                my_lat = db.getLatitude();
                my_lng = db.getLontitude();
                my_addr = db.getAddress();
                Log.v("loction",db.toString());
            }
        });
```
####高德定位
```Java
 new GaoDeLocation().location(this, 1, 0, new GaoDeLocationInterFace() {
            @Override<br/>
            public void gaodeLocation(GaoDeLocationBean gb) {
                my_lat = gb.getLatitude();
                my_lng = gb.getLongitude();
                my_addr = gb.getAddress();
                Log.v("loction",gb.toString());
            }
        });
```
