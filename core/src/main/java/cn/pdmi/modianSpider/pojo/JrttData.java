package cn.pdmi.modianSpider.pojo;

public class JrttData {
    private String id;
    private String media;
    private String mediaName;
    private String Dy;

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

    @Override
    public String toString() {
        return "JrttData{" +
                "id='" + id + '\'' +
                ", media='" + media + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", Dy='" + Dy + '\'' +
                '}';
    }
}
