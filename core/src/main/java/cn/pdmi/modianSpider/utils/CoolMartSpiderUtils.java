package cn.pdmi.modianSpider.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chen_ on 2018/4/28.
 */
public class CoolMartSpiderUtils {
    private static final String BLANK = " ";
    private static final String COMMAND = "D:/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    private static final String JSCODE = "D:/phantomjs/kupai.js";

    public static String getAjax(String url, String keyword) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process p = runtime.exec(COMMAND + BLANK + JSCODE + BLANK + url + BLANK + keyword);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sbf.append(temp);
        }
        //System.out.println("抓取成功！");
        return sbf.toString();
    }
}
