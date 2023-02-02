import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileProcessing implements Serializable {
    protected Map<String, String> processingTsv;
    protected Map<String, Category> processingJson;

    public FileProcessing() {
        processingJson = new HashMap<>();
        processingTsv = new HashMap<>();
    }

    public void readTsv() throws FileNotFoundException {

        File fileTsv = new File("categories.tsv");
        Scanner scanner = new Scanner(fileTsv);

        while (scanner.hasNextLine()) {
            String feild = scanner.nextLine();
            String[] separator = feild.split("\t");

            Category currentCategory;
            String productName = separator[0];
            String categoryName = separator[1];
            if (processingJson.containsKey(categoryName)) {
                currentCategory = processingJson.get(categoryName);
            } else {
                currentCategory = new Category(categoryName);
            }
            currentCategory.addElement(productName);
            processingTsv.put(productName, categoryName);
            processingJson.put(categoryName, currentCategory);
        }
        scanner.close();
    }


    public void readJson(Purchasess purchasess) {
        if (processingTsv.containsKey(purchasess.title)) {
            Category sum = processingJson.get(processingTsv.get(purchasess.title));
            sum.wholeAmount(purchasess);
        } else {

            Category anotherCategory;
            if (processingJson.containsKey("другое")) {
                anotherCategory = processingJson.get("другое");
            } else {
                anotherCategory = new Category("другое");
            }

            anotherCategory.addElement(purchasess.title);
            anotherCategory.wholeAmount(purchasess);
            processingTsv.put(purchasess.title, "другое");
            processingJson.put("другое", anotherCategory);
        }
        System.out.println("Товар \n" + purchasess.title + "общей суммой" + purchasess.sum + "добавлен");
    }

    public String stringToJson() {
        Category category = valueMax();
        return "\"maxCategory\": {" + "    \"category\": \"" + category.getCategoryName() + "\"," + "    \"sum\": \"" + category.getSum() + "\"" + "  }";
    }

    public Category valueMax() {
        int lagreSum = Integer.MIN_VALUE;
        Category moreCommon = null;

        for (Map.Entry<String, Category> entry : processingJson.entrySet()) {
            if (entry.getValue().getSum() >= lagreSum) {
                lagreSum = entry.getValue().getSum();
                moreCommon = entry.getValue();
            }
        }
        return moreCommon;
    }
    public FileProcessing(){
        processingJson = new HashMap<>();
        processingTsv = new HashMap<>();
    }
}
