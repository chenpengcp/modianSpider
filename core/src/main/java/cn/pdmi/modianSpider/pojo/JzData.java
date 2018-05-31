package cn.pdmi.modianSpider.pojo;

public class JzData {
    private String id;
    private String xm;
    private Double je;
    private Double ps;
    private String dw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public Double getPs() {
        return ps;
    }

    public void setPs(Double ps) {
        this.ps = ps;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    @Override
    public String toString() {
        return "JzData{" +
                "id=" + id +
                ", xm='" + xm + '\'' +
                ", je=" + je +
                ", ps=" + ps +
                ", dw='" + dw + '\'' +
                '}';
    }
}
