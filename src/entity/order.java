package entity;

/**
 * @title: order
 * @Author Xu
 * @Date: 2022/5/29 22:35
 * @Version 1.0
 */
public class order {
    public int id;
    public String partya;
    public String partyb;
    public int date;
    public String address;

    public order() {
    }

    public order(int id, String partya, String partyb, int date, String address) {
        this.id = id;
        this.partya = partya;
        this.partyb = partyb;
        this.date = date;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartya() {
        return partya;
    }

    public void setPartya(String partya) {
        this.partya = partya;
    }

    public String getPartyb() {
        return partyb;
    }

    public void setPartyb(String partyb) {
        this.partyb = partyb;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "order{" +
                "id=" + id +
                ", partya='" + partya + '\'' +
                ", partyb='" + partyb + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                '}';
    }
}