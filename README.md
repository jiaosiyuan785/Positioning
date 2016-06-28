# 用于定位的一个简单的方法
## 可以简单的使用百度和高德的定位
####百度定位
` ``Java
new BaiDuLocation().location(this, 2, "bd09ll", 0, new BaiDuLocationInterFace() {
            @Override
            public void baidulocation(BaiDuLocationBean db) {
                my_lat = db.getLatitude();
                my_lng = db.getLontitude();
                my_addr = db.getAddress();
                Log.v("loction",db.toString());
            }
        });
` ``
####高德定位
` ``Java
 new GaoDeLocation().location(this, 1, 0, new GaoDeLocationInterFace() {
            @Override
            public void gaodeLocation(GaoDeLocationBean gb) {
                my_lat = gb.getLatitude();
                my_lng = gb.getLongitude();
                my_addr = gb.getAddress();
                Log.v("loction",gb.toString());
            }
        });
` ``
