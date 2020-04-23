package cn.pdmi.modianSpider.utils;

public class PYUtils {
    public static String getPinyin(String name) {
        char[] chars = name.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return "t_" + returnStr.replace("\\", "");
    }

    public static String getUrl(String name) {
        char[] chars = name.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return "url_" + returnStr.replace("\\", "");
    }

    public static String getTotal(String name) {
        char[] chars = name.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return "total_" + returnStr.replace("\\", "");
    }

//    public static void main(String[] args) {
//        System.out.println(getTotal("万丽娜"));
//    }
}
