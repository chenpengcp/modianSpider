package cn.pdmi.modianSpider.pojo;

public class ShxwData {
    private String id;
    private String media;
    private String mediaName;
    private String Dy;
    private String article;
    private String read;

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

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "ShxwData{" +
                "id='" + id + '\'' +
                ", media='" + media + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", Dy='" + Dy + '\'' +
                ", article='" + article + '\'' +
                ", read='" + read + '\'' +
                '}';
    }
}
