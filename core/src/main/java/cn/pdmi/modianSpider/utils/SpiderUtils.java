package cn.pdmi.modianSpider.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chen_ on 2018/4/28.
 */
public class SpiderUtils {
    private static final String BLANK = "  ";
    private static final String COMMAND = "D:/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    //private static final String JSCODE = "D:/phantomjs/modian.js";
    private static final String JSCODE = "D:/phantomjs/qq.js";
    //private static final String JSCODE = "D:/phantomjs/apple.js";

    public static String getAjax(String url) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process p = runtime.exec(COMMAND + BLANK + JSCODE + BLANK + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        //System.out.println("抓取成功！");
        return sb.toString();
    }
}
