package cn.pdmi.modianSpider.pojo;

/**
 * Created by chen_ on 2018/4/27.
 */
public class AppSearch {
    private Integer id;
    private String name;
    private String searchIndex;
    private String searchResult;
    private String date;

    @Override
    public String toString() {
        return "AppSearch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", searchIndex=" + searchIndex +
                ", searchResult=" + searchResult +
                ", date='" + date + '\'' +
                '}';
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

    public String getSearchIndex() {
        return searchIndex;
    }

    public void setSearchIndex(String searchIndex) {
        this.searchIndex = searchIndex;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
