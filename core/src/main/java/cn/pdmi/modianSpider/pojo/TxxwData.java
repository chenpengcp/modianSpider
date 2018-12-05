package cn.pdmi.modianSpider.pojo;

public class TxxwData {
    private String id;
    private String media;
    private String mediaName;
    private String dy;
    private String article;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TxxwData{" +
                "id='" + id + '\'' +
                ", media='" + media + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", dy='" + dy + '\'' +
                ", article='" + article + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
