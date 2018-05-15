package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.pojo.SingletonModel;
import cn.pdmi.modianSpider.utils.JDBCUtils;
import cn.pdmi.modianSpider.utils.SpiderUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewCoreSpider implements Runnable {
    private String id;
    private double size;

    public NewCoreSpider() {
    }

    public NewCoreSpider(String id, double size) {
        this.id = id;
        this.size = size;
    }

    public Map<String, String> getData(String id, double size) throws IOException {
        Map<String, String> map = new HashMap<>();
        for (int j = 1; j < Math.ceil(size / 20) + 1; j++) {
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
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "INSERT INTO lzy (name,money) " +
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

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(new NewCoreSpider("16546", 110));
        Thread thread2 = new Thread(new NewCoreSpider("10586", 98));
        Thread thread3 = new Thread(new NewCoreSpider("11030", 245));
        Thread thread4 = new Thread(new NewCoreSpider("11964", 279));
        Thread thread5 = new Thread(new NewCoreSpider("13968", 257));
        Thread thread6 = new Thread(new NewCoreSpider("14510", 297));
        Thread thread7 = new Thread(new NewCoreSpider("16252", 205));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
//fqy
//        newCoreSpider.insert(newCoreSpider.getData("16464", 83));
//        newCoreSpider.insert(newCoreSpider.getData("17044", 410));
//        newCoreSpider.insert(newCoreSpider.getData("10520", 103));
//        newCoreSpider.insert(newCoreSpider.getData("10703", 527));
//        newCoreSpider.insert(newCoreSpider.getData("11696", 232));
//        newCoreSpider.insert(newCoreSpider.getData("12767", 374));
//        newCoreSpider.insert(newCoreSpider.getData("14630", 530));
//        newCoreSpider.insert(newCoreSpider.getData("15445", 197));
//        newCoreSpider.insert(newCoreSpider.getData("16259", 557));
    }
}
