package cn.pdmi.modianSpider.pojo;

public class Model {
    private String id;
    private String name;
    private String money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        if (money.contains(".")&&(money.indexOf(".")+3)<money.length()) {
            return money.substring(0,money.indexOf(".")+3);
        } else {
            return money;
        }
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
