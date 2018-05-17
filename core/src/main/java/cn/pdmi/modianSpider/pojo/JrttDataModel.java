package cn.pdmi.modianSpider.pojo;

public class JrttDataModel {
    private Integer id;
    private String name;
    private String uid;
    private String follow;
    private String fans;
    private String enter;

    public String getEnter() {
        return enter;
    }

    public void setEnter(String enter) {
        this.enter = enter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    @Override
    public String toString() {
        return "JrttDataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", follow='" + follow + '\'' +
                ", fans='" + fans + '\'' +
                ", enter='" + enter + '\'' +
                '}';
    }
}
