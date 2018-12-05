package cn.pdmi.modianSpider.pojo;

public class JrttUrl {
    private String id;
    private String media;
    private String mediaName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "JrttUrl{" +
                "id='" + id + '\'' +
                ", media='" + media + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
