package cn.pdmi.modianSpider.pojo;

/**
 * Created by chen_ on 2018/4/27.
 */
public class SingletonModel {
    private Integer sid;
    private String name;
    private Double money;
    private String url;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
