package cn.pdmi.modianSpider.core;

import cn.pdmi.modianSpider.utils.HttpSpiderUtils;
import cn.pdmi.modianSpider.utils.TouTiaoUtils;

import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        Map<String, String> asCp = TouTiaoUtils.getAsCp();
        String s = HttpSpiderUtils.getAjax("https://www.toutiao.com/pgc/ma/?page_type=1&max_behot_time=&uid=50502346173&media_id=50502346173&output=json&is_json=1&count=20&from=user_profile_app&version=2&as=" + asCp.get("as") + "&cp=" + asCp.get("cp") + "&callback=jsonp3");
        System.out.println(s);
    }
}
