package cn.pdmi.modianSpider.core;

/**
 * Created by chen_ on 2018/5/3.
 */
public class Test {
    public static void main(String[] args) {
        String s="8655万次下载";
        String str = s.substring(0, s.indexOf("万"));
        System.out.println(str);
    }
}
