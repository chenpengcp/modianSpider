package cn.pdmi.modianSpider.server;

import cn.pdmi.modianSpider.core.*;

public class AppleSearch_StartServer {
    public static void main(String[] args) {
        Thread thread = new Thread(new AppleSearchSpider());
        thread.start();
    }
}
