package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.SingletonModel;
import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.Modian_JDBCUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewCoreSpider implements Runnable {
    private String id;
    private String size;

    public NewCoreSpider() {
    }

    public NewCoreSpider(String id, String size) {
        this.id = id;
        this.size = size;
    }

    public Map<String, String> getData(String id, String size) throws IOException {
        double count = 0;
        try {
            count = Double.parseDouble(size);
        } catch (Exception e) {
            count = 20;
        }
        Map<String, String> map = new HashMap<>();
        for (int j = 1; j < Math.ceil(count / 20) + 1; j++) {
            String html = NewCoreSpider.unicodeToString(SpiderUtils.getAjax("https://zhongchou.modian.com/realtime/ajax_dialog_user_list?" +
                    "jsonpcallback=jQuery11110984149251830938_1526284807212&origin_id=" + id + "&type=backer_list&page=" + j + "&page_size=20&cate=2&_=1526284807216"));
            String regexStr = "&lt;p&gt;(.+?)&lt;";
            Pattern pattern = Pattern.compile(regexStr);
            Matcher matcher = pattern.matcher(html);
            List<String> list = new ArrayList<>();
            while (matcher.find()) {
                list.add(matcher.group().substring(9, matcher.group().lastIndexOf("&")));
            }

            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    map.put(list.get(i), list.get(i + 1).replace("¥", ""));
                }
                if (i % 2 == 0) {
                    map.put(list.get(i), list.get(i + 1).replace("¥", ""));
                }
            }
        }
        return map;
    }

    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    public void insert(Map<String, String> map) throws Exception {
        QueryRunner queryRunner = new QueryRunner(Modian_JDBCUtils.getDataSource());
        String sql = "INSERT INTO zjr (name,money) " +
                "VALUES (?,?)";
        for (String key : map.keySet()
                ) {
            int update = queryRunner.update(sql, key, map.get(key).contains(",") ? map.get(key).replace(",", "") : map.get(key));
            if (update == 1) {
                System.out.println("success!");
            } else {
                System.out.println("插入失败！");
            }
        }
    }

    @Override
    public void run() {
        try {
            this.insert(this.getData(id, size));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getUrlId() throws Exception {
        Map<String, String> map = new HashMap<>();
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:/phantomjs/zjr.properties"));
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = properties.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        NewCoreSpider newCoreSpider = new NewCoreSpider();
        Map<String, String> map = newCoreSpider.getUrlId();
        for (String key : map.keySet()
                ) {
            executorService.execute(new NewCoreSpider(key, map.get(key)));
        }
        executorService.shutdown();
    }
}
