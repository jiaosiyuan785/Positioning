package weiyou.com.locationlibrary.locationproject.baidu;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.List;

import weiyou.com.locationlibrary.locationproject.bean.BaiDuLocationBean;
import weiyou.com.locationlibrary.locationproject.location.BaiDuLocationInterFace;


/**
 * Created by Kate on 2016/1/19.
 */
public class BaiDuLocation {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = null;
    public BaiDuLocationInterFace mylocation;
    private int ScanSpan;

    /*
    * 初始化部分数据
    * */
    private void onCreate(Context context, int setScanSpan, BaiDuLocationInterFace location) {
        this.mylocation = location;
        this.ScanSpan = setScanSpan;
        this.myListener = new MyLocationListener();
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(myListener);
    }

    /**
     * @param context       上下文
     * @param location_Mode 设置定位模式，0=低功耗，1=GPS,2=高精度
     * @param coorType      设置返回国测局经纬度坐标系：gcj02 返回百度墨卡托坐标系 ：bd09 返回百度经纬度坐标系 ：bd09ll
     * @param setScanSpan   默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的,为0时，定位完成后会默认stop()定位
     * @param location      定位类型的接口
     */
    public void location(Context context, int location_Mode, String coorType, int setScanSpan, BaiDuLocationInterFace location) {
        if (!isStarted()) {
            onCreate(context, setScanSpan, location);
            initLocation(location_Mode, coorType, setScanSpan, true, true, true, true, true, false, false, false);
            mLocationClient.start();
        }
    }


    /**
     * @param context                上下文
     * @param location_Mode          设置定位模式，0=低功耗，1=GPS,2=高精度
     * @param coorType               设置返回国测局经纬度坐标系：gcj02 返回百度墨卡托坐标系 ：bd09 返回百度经纬度坐标系 ：bd09ll
     * @param setScanSpan            默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的,为0时，定位完成后会默认stop()定位
     * @param IsNeedAddress          是否需要地址信息,默认需要，默认true
     * @param OpenGps                设置是否使用gps,默认需要，默认true
     * @param LocationNotify         设置是否当gps有效时按照1S1次频率输出GPS结果,默认需要，默认true
     * @param IsNeedLocationDescribe 设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”,默认需要，默认true
     * @param IsNeedLocationPoiList  设置是否需要POI结果，可以在BDLocation.getPoiList里得到,默认需要，默认true
     * @param IgnoreKillProcess      定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死,默认false
     * @param IgnoreCacheException   设置是否收集CRASH信息，默认收集,默认false
     * @param EnableSimulateGps      设置是否需要过滤gps仿真结果，默认需要,默认false
     * @param location               定位类型的接口
     */
    public void location(Context context, int location_Mode, String coorType, int setScanSpan,
                         boolean IsNeedAddress, boolean OpenGps, boolean LocationNotify, boolean IsNeedLocationDescribe
            , boolean IsNeedLocationPoiList, boolean IgnoreKillProcess, boolean IgnoreCacheException, boolean EnableSimulateGps,
                         BaiDuLocationInterFace location) {
        if (!isStarted()) {
            onCreate(context, setScanSpan, location);
            initLocation(location_Mode, coorType, setScanSpan, IsNeedAddress, OpenGps, LocationNotify, IsNeedLocationDescribe,
                    IsNeedLocationPoiList, IgnoreKillProcess, IgnoreCacheException, EnableSimulateGps);
            mLocationClient.start();
        }
    }

    /*
    *配置定位SDK参数
    * */
    private void initLocation(int location_Mode, String coorType, int setScanSpan, boolean IsNeedAddress, boolean OpenGps, boolean LocationNotify, boolean IsNeedLocationDescribe
            , boolean IsNeedLocationPoiList, boolean IgnoreKillProcess, boolean IgnoreCacheException, boolean EnableSimulateGps) {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，0=低功耗，1=GPS,2=高精度
        if (0 == location_Mode) {
            option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        } else if (1 == location_Mode) {
            option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        } else if (2 == location_Mode) {
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        }
        option.setCoorType(coorType);//可选，默认gcj02，设置返回的定位结果坐标系   bd09ll
        option.setScanSpan(setScanSpan);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(IsNeedAddress);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(OpenGps);//可选，默认false,设置是否使用gps
        option.setLocationNotify(LocationNotify);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(IsNeedLocationDescribe);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(IsNeedLocationPoiList);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(IgnoreKillProcess);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(IgnoreCacheException);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(EnableSimulateGps);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    /*
    * 实现BDLocationListener接口,接收异步返回的定位结果
    * */
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            BaiDuLocationBean db = new BaiDuLocationBean();
            db.setTime(location.getTime());
            db.setLocType(location.getLocType());
            db.setLatitude(location.getLatitude());
            db.setLontitude(location.getLongitude());
            db.setRadius(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                db.setSpeed(location.getSpeed());
                db.setSatellite(location.getSatelliteNumber());
                db.setHeight(location.getAltitude());// 单位：米
                db.setDirection(location.getDirection());// 单位度
                db.setAddress(location.getAddrStr());
                db.setDescribe("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                db.setAddress(location.getAddrStr());
                //运营商信息
                db.setOperationers(location.getOperators());
                db.setDescribe("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                db.setDescribe("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                db.setDescribe("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                db.setDescribe("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                db.setDescribe("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            } else if (location.getLocType() == BDLocation.TypeCacheLocation) {
                db.setDescribe("定位缓存的结果");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocationFail) {
                db.setDescribe("离线定位失败");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocationNetworkFail) {
                db.setDescribe("网络连接失败时，查找本地离线定位时对应的返回结果");
            } else if (location.getLocType() == 162) {
                db.setDescribe("请求串密文解析失败，一般是由于客户端SO文件加载失败造成");
            } else if (location.getLocType() == 502) {
                db.setDescribe("key参数错误，请按照说明文档重新申请KEY");
            } else if (location.getLocType() == 505) {
                db.setDescribe("key不存在或者非法，请按照说明文档重新申请KEY");
            } else if (location.getLocType() == 601) {
                db.setDescribe("key服务被开发者自己禁用，请按照说明文档重新申请KEY");
            } else if (location.getLocType() == 602) {
                db.setDescribe("key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，请按照说明文档重新申请KEY");
            } else if (location.getLocType() >= 501 && location.getLocType() <= 700) {
                db.setDescribe("key验证失败，请按照说明文档重新申请KEY");
            } else if (location.getLocType() > 162 && location.getLocType() < 167) {
                db.setDescribe("请将错误码、imei和定位时间反馈至loc-bugs@baidu.com，以便我们跟进追查问题");
            } else {
                db.setDescribe("如果不能定位，请记住这个返回值，并到百度LBS开放平台论坛Andriod定位SDK版块中进行交流http://bbs.lbsyun.baidu.com/forum.php?mod=forumdisplay&fid=10");
            }

            db.setLocationdescribe(location.getLocationDescribe());
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                for (Poi p : list) {
                    db.setPoi(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            if (ScanSpan == 0) {
                mLocationClient.stop();
            }
            mylocation.baidulocation(db);
        }
    }

    /**
     * 如果进行长时间定位，需要停止时,调用该方法可以停止定位
     */
    public void stopLocation() {
        mLocationClient.stop();
    }

    /**
     * 该方法获取定位是否停止,有的移动设备锁屏后为了省电会自动关闭网络连接，此时网络定位模式的定位失效。此外，
     * 锁屏后移动设备若进入cpu休眠，定时定位功能也失效。若您需要实现在cpu休眠状态仍需定时定位，
     * 可以用alarmManager 实现1个cpu可叫醒的timer，定时请求定位。
     */
    public boolean isStarted() {
        boolean ifag = false;
        try {
            ifag = mLocationClient.isStarted();
        } catch (NullPointerException ex) {
            ifag = false;
        }
        return ifag;
    }

    /**
     * 定位启动,默认使用时是不需要调用该方法,该方法在第一次初始化时，已经启动，
     * 如果在调用isStarted()方法时，返回为false,需要继续定位,则就调用该方法启动即可
     */
    public void start() {
        mLocationClient.start();
    }
}
