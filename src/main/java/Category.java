import java.util.*;

public class Category {
    protected Set<String> items =  new HashSet<>();
    private String categoryName;
    private int sum;
    private List addItems = new LinkedList<Purchasess>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean itemCategory(String elementName){
        return items.contains(elementName);
    }

    public void addElement(String elementName){
        items.add(elementName);
    }

    public boolean wholeAmount(Purchasess purchasess){
        if (itemCategory(purchasess.title)){
            sum += purchasess.sum;
            return true;
        }
        return false;
    }

    public int getSum(){
        return sum;
    }

    public String getCategoryName(){
        return categoryName;
    }
}
