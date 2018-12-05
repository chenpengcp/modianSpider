package cn.pdmi.modianSpider.thread;

import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import com.mysql.jdbc.util.Base64Decoder;
import org.apache.http.util.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class Test {
    public static void main(String[] args) throws Exception {
        String html = SpiderUtils.getAjax("http://mtrace.qq.com/mkvcollect?a=5.7.10&s=5.1.9.011&k=A3LFCJD983W9&n=10");
        System.out.println(html);
    }
}
