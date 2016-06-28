package weiyou.com.locationlibrary.locationproject.gaode;

import android.content.Context;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import weiyou.com.locationlibrary.locationproject.bean.GaoDeLocationBean;
import weiyou.com.locationlibrary.locationproject.location.GaoDeLocationInterFace;


/**
 * Created by Kate on 2016/1/22.
 */
public class GaoDeLocation {
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    public AMapLocationListener mLocationListener = null;
    public GaoDeLocationInterFace mylocation;

    private void onCreate(Context context, GaoDeLocationInterFace mylocation) {
        this.mylocation = mylocation;
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                locationResult(aMapLocation);
            }
        };
        mLocationClient = new AMapLocationClient(context);
        mLocationClient.setLocationListener(mLocationListener);
    }

    /**
     * @param context       上下文
     * @param location_Mode 定位精度,0为低功耗模式，1是仅设备模式,2是高精度模式
     * @param setScanSpan   定位时间,需要大于等于1000ms才有效,如果为小于1000则就只定位一次
     * @param mylocation    实现的接口
     */
    public void location(Context context, int location_Mode, int setScanSpan, GaoDeLocationInterFace mylocation) {
        if (!isStarted()) {
            onCreate(context, mylocation);
            initLocation(location_Mode, setScanSpan, true, true, false);
        }

    }

    /**
     * @param context        上下文
     * @param location_Mode  定位精度,0为低功耗模式，1是仅设备模式,2是高精度模式
     * @param setScanSpan    定位时间,需要大于等于1000ms才有效,如果为小于1000则就只定位一次
     * @param NeedAddress    是否需要返回地址信息，建议为true
     * @param WifiActiveScan 是否强制刷新WIFI，建议为true
     * @param MockEnable     设置是否允许模拟位置,建议为false
     * @param mylocation     实现的接口
     */
    public void location(Context context, int location_Mode, int setScanSpan, boolean NeedAddress, boolean WifiActiveScan, boolean MockEnable, GaoDeLocationInterFace mylocation) {
        if (!isStarted()) {
            onCreate(context, mylocation);
            initLocation(location_Mode, setScanSpan, NeedAddress, WifiActiveScan, MockEnable);
        }
    }

    private void initLocation(int location_Mode, int setScanSpan, boolean NeedAddress, boolean WifiActiveScan, boolean MockEnable) {
        mLocationOption = new AMapLocationClientOption();
        if (0 == location_Mode) {
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        } else if (1 == location_Mode) {
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        } else if (2 == location_Mode) {
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        }
        mLocationOption.setNeedAddress(NeedAddress);
        if (setScanSpan == 0) {
            mLocationOption.setOnceLocation(true);
        } else if (setScanSpan >= 1000) {
            mLocationOption.setOnceLocation(false);
        } else if (setScanSpan < 1000) {
            setScanSpan = 0;
            mLocationOption.setOnceLocation(true);
        }
        mLocationOption.setWifiActiveScan(WifiActiveScan);
        mLocationOption.setMockEnable(MockEnable);
        mLocationOption.setInterval(setScanSpan);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    private void locationResult(AMapLocation amapLocation) {
        GaoDeLocationBean gb = new GaoDeLocationBean();
        if (amapLocation != null) {
            gb.setErrorCode(amapLocation.getErrorCode());
            if (amapLocation.getErrorCode() == 0) {
                gb.setErrInfo("定位成功");
                if (amapLocation.getLocationType() == 1) {
                    gb.setLocationType("GPS定位结果");
                } else if (amapLocation.getLocationType() == 2) {
                    gb.setLocationType("前次定位结果");
                } else if (amapLocation.getLocationType() == 4) {
                    gb.setLocationType("缓存定位结果");
                } else if (amapLocation.getLocationType() == 5) {
                    gb.setLocationType("Wifi定位结果");
                } else if (amapLocation.getLocationType() == 6) {
                    gb.setLocationType("基站定位结果");
                } else if (amapLocation.getLocationType() == 8) {
                    gb.setLocationType("离线定位结果");
                }
                gb.setLatitude(amapLocation.getLatitude());
                gb.setLongitude(amapLocation.getLongitude());
                gb.setAccuracy(amapLocation.getAccuracy());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                gb.setTime(df.format(date));
                gb.setAddress(amapLocation.getAddress());
                gb.setCountry(amapLocation.getCountry());
                gb.setProvince(amapLocation.getProvince());
                gb.setCity(amapLocation.getCity());
                gb.setDistrict(amapLocation.getDistrict());
                gb.setStreet(amapLocation.getStreet());
                gb.setStreetNum(amapLocation.getStreetNum());
                gb.setCityCode(amapLocation.getCityCode());
                gb.setAdCode(amapLocation.getAdCode());
            } else if(amapLocation.getErrorCode()==1){
                gb.setErrInfo("一些重要参数为空，如context；请对定位传递的参数进行非空判断");
            } else if(amapLocation.getErrorCode()==2){
                gb.setErrInfo("定位失败，由于仅扫描到单个wifi，且没有基站信息");
            } else if(amapLocation.getErrorCode()==3){
                gb.setErrInfo("获取到的请求参数为空，可能获取过程中出现异常");
            } else if(amapLocation.getErrorCode()==4){
                gb.setErrInfo("请求服务器过程中的异常，多为网络情况差，链路不通导致，请检查设备网络是否通畅");
            } else if(amapLocation.getErrorCode()==5){
                gb.setErrInfo("返回的XML格式错误，解析失败");
            } else if(amapLocation.getErrorCode()==6){
                gb.setErrInfo("定位服务返回定位失败，如果出现该异常，请将errorDetail信息通过API@autonavi.com反馈给我们");
            } else if(amapLocation.getErrorCode()==7){
                gb.setErrInfo("KEY建权失败，请仔细检查key绑定的sha1值与apk签名sha1值是否对应");
            } else if(amapLocation.getErrorCode()==8){
                gb.setErrInfo("Android exception通用错误，请将errordetail信息通过API@autonavi.com反馈给我们");
            } else if(amapLocation.getErrorCode()==9){
                gb.setErrInfo("定位初始化时出现异常，请重新启动定位");
            } else if(amapLocation.getErrorCode()==10){
                gb.setErrInfo("定位客户端启动失败，请检查AndroidManifest.xml文件是否配置了APSService定位服务");
            } else if(amapLocation.getErrorCode()==11){
                gb.setErrInfo("定位时的基站信息错误，请检查是否安装SIM卡，设备很有可能连入了伪基站网络");
            }else if(amapLocation.getErrorCode()==12){
                gb.setErrInfo("缺少定位权限，请在设备的设置中开启app的定位权限");
            }
            mylocation.gaodeLocation(gb);
        }
    }

    /**
     * 如果进行长时间定位，需要停止时,调用该方法可以停止定位
     */
    public void stopLocation() {
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }

    /**
     * 该方法获取定位是否停止,有的移动设备锁屏后为了省电会自动关闭网络连接，此时网络定位模式的定位失效。此外，
     * 锁屏后移动设备若进入cpu休眠，定时定位功能也失效。若您需要实现在cpu休眠状态仍需定时定位，
     * 可以用alarmManager 实现1个cpu可叫醒的timer，定时请求定位。
     */
    public boolean isStarted() {
        boolean flag = false;
        try {
            flag = mLocationClient.isStarted();
        } catch (NullPointerException ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * 定位启动,默认使用时是不需要调用该方法,该方法在第一次初始化时，已经启动，
     * 如果在调用isStarted()方法时，返回为false,需要继续定位,则就调用该方法启动即可
     */
    public void start() {
        mLocationClient.startLocation();
    }
}
