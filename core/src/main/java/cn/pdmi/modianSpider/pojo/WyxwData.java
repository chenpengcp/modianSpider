package cn.pdmi.modianSpider.pojo;

public class WyxwData {
    private String id;
    private String media;
    private String mediaName;
    private String Dy;
    private String article;

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
        return Dy;
    }

    public void setDy(String dy) {
        Dy = dy;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "WyxwData{" +
                "id='" + id + '\'' +
                ", media='" + media + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", Dy='" + Dy + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
