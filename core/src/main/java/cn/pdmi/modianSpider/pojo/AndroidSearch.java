package cn.pdmi.modianSpider.pojo;

/**
 * Created by chen_ on 2018/5/3.
 */
public class AndroidSearch {
    private Integer id;
    private String name;
    private String mediaName;
    private String downloads;//下载量
    private Integer enter;//是否入驻
    private String insertDate;

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public Integer getEnter() {
        return enter;
    }

    public void setEnter(Integer enter) {
        this.enter = enter;
    }

    @Override
    public String toString() {
        return "AndroidSearch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", downloads='" + downloads + '\'' +
                ", enter=" + enter +
                ", insertDate='" + insertDate + '\'' +
                '}';
    }
}
