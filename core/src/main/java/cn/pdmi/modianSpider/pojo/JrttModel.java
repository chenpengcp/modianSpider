package cn.pdmi.modianSpider.pojo;

public class JrttModel {
    private Integer id;
    private String title;
    private Integer comment_count;
    private Integer read_count;
    private String create_time;
    private String name;
    private String insertDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getRead_count() {
        return read_count;
    }

    public void setRead_count(Integer read_count) {
        this.read_count = read_count;
    }


    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public String toString() {
        return "JrttModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comment_count=" + comment_count +
                ", read_count=" + read_count +
                ", create_time='" + create_time + '\'' +
                ", name='" + name + '\'' +
                ", insertDate='" + insertDate + '\'' +
                '}';
    }
}

