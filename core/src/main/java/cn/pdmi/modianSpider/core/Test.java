package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.utils.SpiderUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
        String html = Test.unicodeToString(SpiderUtils.getAjax("https://zhongchou.modian.com/realtime/ajax_dialog_user_list?" +
                "jsonpcallback=jQuery11110984149251830938_1526284807212&origin_id=16464&type=backer_list&page=1&page_size=20&cate=2&_=1526284807216"));
        String regexStr ="<html>^.*?$<html>";
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }else {
            System.out.println("no");
        }
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

}
