package cn.pdmi.modianSpider.core;


/**
 * Created by chen_ on 2018/4/25.
 */
public class StartServer implements Runnable {
    @Override
    public void run() {
        CoreSpider coreSpider = new CoreSpider();
        try {
            coreSpider.startServer();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new StartServer()).start();
    }
}
