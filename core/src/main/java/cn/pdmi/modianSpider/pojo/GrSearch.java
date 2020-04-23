package cn.pdmi.modianSpider.pojo;


public class GrSearch {
    private Integer id;
    private String fansName;
    private String memberName;
    private String money;
    private String totalMoney;
    private String count;
    private String percent;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFansName() {
        return fansName;
    }

    public void setFansName(String fansName) {
        this.fansName = fansName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMoney() {
        if (money.contains(".") && (money.indexOf(".") + 3) < money.length()) {
            return money.substring(0, money.indexOf(".") + 3);
        } else {
            return money;
        }
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
