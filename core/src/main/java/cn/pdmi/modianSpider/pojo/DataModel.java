package cn.pdmi.modianSpider.pojo;

import java.util.Date;

/**
 * Created by chen_ on 2018/4/24.
 */
public class DataModel {
    private String title;
    private Double totalMoney;
    private Double goalMoney;
    private Integer count;
    private String name1;
    private Double money1;
    private String name2;
    private Double money2;
    private String name3;
    private Double money3;
    private String name4;
    private Double money4;
    private String name5;
    private Double money5;
    private String url;
    private String start_time;
    private String end_time;

    @Override
    public String toString() {
        return "DataModel{" +
                "title='" + title + '\'' +
                ", totalMoney=" + totalMoney +
                ", goalMoney=" + goalMoney +
                ", count=" + count +
                ", name1='" + name1 + '\'' +
                ", money1=" + money1 +
                ", name2='" + name2 + '\'' +
                ", money2=" + money2 +
                ", name3='" + name3 + '\'' +
                ", money3=" + money3 +
                ", name4='" + name4 + '\'' +
                ", money4=" + money4 +
                ", name5='" + name5 + '\'' +
                ", money5=" + money5 +
                '}';
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getGoalMoney() {
        return goalMoney;
    }

    public void setGoalMoney(Double goalMoney) {
        this.goalMoney = goalMoney;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public Double getMoney1() {
        return money1;
    }

    public void setMoney1(Double money1) {
        this.money1 = money1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Double getMoney2() {
        return money2;
    }

    public void setMoney2(Double money2) {
        this.money2 = money2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public Double getMoney3() {
        return money3;
    }

    public void setMoney3(Double money3) {
        this.money3 = money3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public Double getMoney4() {
        return money4;
    }

    public void setMoney4(Double money4) {
        this.money4 = money4;
    }

    public String getName5() {
        return name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }

    public Double getMoney5() {
        return money5;
    }

    public void setMoney5(Double money5) {
        this.money5 = money5;
    }
}
