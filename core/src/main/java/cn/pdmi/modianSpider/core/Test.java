package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
        downLoadFromUrl("http://v.qq.com/txp/iframe/player.html?origin=https%3A%2F%2Fmp.weixin.qq.com&amp;vid=b0672z1bnrz&amp;autoplay=false&amp;full=true&amp;show1080p=false", "ddbai", "D://");
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println("info:" + url + " download success");
    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    public static void downLoadFile(String filePath, HttpServletResponse response) {
        File file = null;
        DataInputStream dis = null;
        ServletOutputStream out = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                System.out.println("没有找到资源");
                return;
            }
            out = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            int len = (int) file.length();
            byte[] buffer = new byte[len];
            try {
                dis.readFully(buffer);
            } catch (IOException e) {
            } finally {
                if (dis != null) {
                    try {
                        dis.close();
                    } catch (IOException e) {
                    }
                }
            }
            try {
                out.write(buffer);
                out.flush();
            } catch (IOException e) {
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
