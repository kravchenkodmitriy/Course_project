public class Purchasess {
    String title;
    String date;
    Integer sum;

    public Purchasess(String title, String date, Integer sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public boolean getTitle() {
        return title;
    }

    public Integer getSum() {
        return sum;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

}
