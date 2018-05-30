package cn.pdmi.modianSpider.server;

import cn.pdmi.modianSpider.core.AppleRankSpider;
import cn.pdmi.modianSpider.core.AppleSearchSpider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppleRank_StartServer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new AppleRankSpider());
        executorService.shutdown();
    }
}
