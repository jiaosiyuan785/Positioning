# 用于定位的一个简单的方法
## 可以简单的使用百度和高德的定位
####百度定位
```Java
new BaiDuLocation().location(this, 2, "bd09ll", 0, new BaiDuLocationInterFace() {<br/>
            @Override<br/>
            public void baidulocation(BaiDuLocationBean db) {<br/>
                my_lat = db.getLatitude();<br/>
                my_lng = db.getLontitude();<br/>
                my_addr = db.getAddress();<br/>
                Log.v("loction",db.toString());<br/>
            }<br/>
        });<br/>
```
####高德定位
```Java
 new GaoDeLocation().location(this, 1, 0, new GaoDeLocationInterFace() {<br/>
            @Override<br/>
            public void gaodeLocation(GaoDeLocationBean gb) {<br/>
                my_lat = gb.getLatitude();<br/>
                my_lng = gb.getLongitude();<br/>
                my_addr = gb.getAddress();<br/>
                Log.v("loction",gb.toString());<br/>
            }<br/>
        });<br/>
```
