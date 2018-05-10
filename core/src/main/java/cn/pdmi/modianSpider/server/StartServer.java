package cn.pdmi.modianSpider.server;


import cn.pdmi.modianSpider.core.DataSpider;

/**
 * Created by chen_ on 2018/4/25.
 */
public class StartServer implements Runnable {
    @Override
    public void run() {
        DataSpider dataSpider = new DataSpider();
        try {
            dataSpider.startServer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new StartServer()).start();
    }
}
