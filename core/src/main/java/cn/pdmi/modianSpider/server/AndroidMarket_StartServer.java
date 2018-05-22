package cn.pdmi.modianSpider.server;

import cn.pdmi.modianSpider.core.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AndroidMarket_StartServer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new AndroidMarket_360_Spider());
        executorService.execute(new AndroidMarket_anzhi_Spider());
        executorService.execute(new AndroidMarket_baidu_Spider());
        executorService.execute(new AndroidMarket_coolpad_Spider());
        executorService.execute(new AndroidMarket_huawei_Spider());
        executorService.execute(new AndroidMarket_lenovo_Spider());
        executorService.execute(new AndroidMarket_meizu_Spider());
        executorService.execute(new AndroidMarket_qq_Spider());
        executorService.execute(new AndroidMarket_sogou_Spider());
        executorService.execute(new AndroidMarket_wandoujia_Spider());
        executorService.shutdown();
    }
}
