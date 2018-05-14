package cn.pdmi.modianSpider.core;


public class JrttSpider_Thread implements Runnable {
    private String uid;
    private String max_behot_time;
    private String name;

    public JrttSpider_Thread(String uid, String max_behot_time, String name) {
        this.uid = uid;
        this.max_behot_time = max_behot_time;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            JrttSpiderCore jrttSpiderCore = new JrttSpiderCore();
            jrttSpiderCore.getData(max_behot_time,uid,name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
