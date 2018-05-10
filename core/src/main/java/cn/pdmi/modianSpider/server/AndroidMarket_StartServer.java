package cn.pdmi.modianSpider.server;

import cn.pdmi.modianSpider.core.*;

public class AndroidMarket_StartServer {
    public static void main(String[] args) {
        Thread thread_360 = new Thread(new AndroidMarket_360_Spider());
        Thread thread_anzhi = new Thread(new AndroidMarket_anzhi_Spider());
        Thread thread_baidu = new Thread(new AndroidMarket_baidu_Spider());
        Thread thread_coolpad = new Thread(new AndroidMarket_coolpad_Spider());
        Thread thread_huawei = new Thread(new AndroidMarket_huawei_Spider());
        Thread thread_lenovo = new Thread(new AndroidMarket_lenovo_Spider());
        Thread thread_meizu = new Thread(new AndroidMarket_meizu_Spider());
        Thread thread_qq = new Thread(new AndroidMarket_qq_Spider());
        Thread thread_sogou = new Thread(new AndroidMarket_sogou_Spider());
        Thread thread_wandoujia = new Thread(new AndroidMarket_wandoujia_Spider());
        thread_360.start();
        thread_anzhi.start();
        thread_baidu.start();
        thread_coolpad.start();
        thread_huawei.start();
        thread_lenovo.start();
        thread_meizu.start();
        thread_qq.start();
        thread_sogou.start();
        thread_wandoujia.start();
    }
}
