package cn.pdmi.modianSpider.pojo;

/**
 * Created by chen_ on 2018/4/27.
 */
public class AppRank {
    private Integer id;
    private String name;
    private String totalRank;
    private String listRank;
    private String date;

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

    public String getTotalRank() {
        return totalRank;
    }

    public void setTotalRank(String totalRank) {
        this.totalRank = totalRank;
    }

    public String getListRank() {
        return listRank;
    }

    public void setListRank(String listRank) {
        this.listRank = listRank;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
