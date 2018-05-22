package cn.pdmi.modianSpider.server;

import cn.pdmi.modianSpider.core.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppleSearch_StartServer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new AppleSearchSpider());
        executorService.shutdown();
    }
}
