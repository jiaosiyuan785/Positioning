package weiyou.com.locationlibrary.locationproject.bean;

/**
 * Created by Kate on 2016/1/22.
 */
public class GaoDeLocationBean {
    //    获取错误原因
    String errInfo;
    //    获取错误码
    int errorCode;
    //    获取当前定位结果来源
    String LocationType;
    //获取纬度
    Double Latitude;
    //获取经度
    Double Longitude;
    //获取精度信息
    float Accuracy;
    //    获取定位时间
    String time;
    //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
    String Address;
    //国家信息
    String Country;
    //省信息
    String Province;
    //城市信息
    String City;
    //城区信息
    String District;
    //街道信息
    String Street;
    //街道门牌号信息
    String StreetNum;
    //城市编码
    String CityCode;
    //地区编码
    String AdCode;

    public float getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(float accuracy) {
        Accuracy = accuracy;
    }

    public String getAdCode() {
        return AdCode;
    }

    public void setAdCode(String adCode) {
        AdCode = adCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public String getLocationType() {
        return LocationType;
    }

    public void setLocationType(String locationType) {
        LocationType = locationType;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getStreetNum() {
        return StreetNum;
    }

    public void setStreetNum(String streetNum) {
        StreetNum = streetNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GaoDeLocationBean{\n" +
                "精度信息=" + Accuracy +
                ", \n错误信息='" + errInfo + '\'' +
                ", \n错误代码=" + errorCode +
                ",\n 定位结果来源='" + LocationType + '\'' +
                ",\n 纬度=" + Latitude +
                ",\n 经度=" + Longitude +
                ",\n 定位时间='" + time + '\'' +
                ",\n 定位地址='" + Address + '\'' +
                ",\n 国家='" + Country + '\'' +
                ",\n 省='" + Province + '\'' +
                ",\n 市='" + City + '\'' +
                ", \n区='" + District + '\'' +
                ",\n 街道信息='" + Street + '\'' +
                ",\n 门牌号码='" + StreetNum + '\'' +
                ",\n 城市编码='" + CityCode + '\'' +
                ", \n地区编码='" + AdCode + '\'' +
                "\n}";
    }
}
