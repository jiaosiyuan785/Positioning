package weiyou.com.locationlibrary.locationproject.bean;

/**
 * Created by Kate on 2016/1/19.
 */
public class BaiDuLocationBean {
    String time;
    String error_code;
    double latitude;
    double lontitude;
    double radius;
    String address;
    //运营商信息
    int operationers;
    String describe;
    //    error code
    int LocType;
    //    单位：公里每小时
    float speed;
    int satellite;
    //    单位：米
    double height;
    //    单位度
    float direction;
    //    位置语义化信息
    String locationdescribe;
    String Poi;

    public String getPoi() {
        return Poi;
    }

    public void setPoi(String poi) {
        Poi = poi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLocationdescribe() {
        return locationdescribe;
    }

    public void setLocationdescribe(String locationdescribe) {
        this.locationdescribe = locationdescribe;
    }

    public int getLocType() {
        return LocType;
    }

    public void setLocType(int locType) {
        LocType = locType;
    }

    public double getLontitude() {
        return lontitude;
    }

    public void setLontitude(double lontitude) {
        this.lontitude = lontitude;
    }

    public int getOperationers() {
        return operationers;
    }

    public void setOperationers(int operationers) {
        this.operationers = operationers;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getSatellite() {
        return satellite;
    }

    public void setSatellite(int satellite) {
        this.satellite = satellite;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BaiDuLocationBean{\n" +
                "地址信息='" + address + '\'' +
                ",\n 定位时间='" + time + '\'' +
                ", \n错误代码='" + error_code + '\'' +
                ",\n 纬度=" + latitude +
                ",\n 经度=" + lontitude +
                ", \n半径=" + radius +
                ", \n运营商信息=" + operationers +
                ",\n 错误信息='" + describe + '\'' +
                ", \n错误编号=" + LocType +
                ", \nGPS定位速度=" + speed +
                "公里每小时, \n连接的卫星数=" + satellite +
                ", \n高度=" + height +
                "米, \n方向=" + direction +
                "度, \nlocationdescribe='" + locationdescribe + '\'' +
                ",\n Poi='" + Poi + '\'' +
                "\n}";
    }
}
