package bookStore;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryCategoryDAO {
    public List<Category> initializeCategory() {

        List<String> linesFormFile = null;
        try {
            linesFormFile = Files.readAllLines
                    (Paths.get("C:\\Projekty_java\\sda9intermediate\\src\\main\\resources\\kategorie.txt"), Charset.forName("UNICODE"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return populateCategorys(linesFormFile);
    }

    private List<Category> populateCategorys(List<String> linesFormFile) {
        AtomicInteger idCounter = new AtomicInteger(1);
        List<Category> categoryList = linesFormFile.stream()
                .map(e -> new Category(idCounter.getAndIncrement(), e))
                .collect(Collectors.toList());
        Map<Integer, List<Category>> categoryMap = categoryList.stream()
                .collect(Collectors.groupingBy(e -> countSpaces(e)));

        populateRecursive(0, categoryMap);

        return categoryList;
    }

    private void populateRecursive(int level, Map<Integer, List<Category>> categoryMap) {
        List<Category> categoryList = categoryMap.get(level);
        if (categoryList==null){
            return ;
        }
        for (Category categoryFor : categoryList) {
            //categoryFor.setName(categoryFor.getName().trim());
            if (level == 0) {
                categoryFor.setParentsId(null);
            } else {
                Integer parentId = categoryMap.get(level - 1).stream()
                        .map(Category::getId)
                        .filter(e -> e < categoryFor.getId())
                        .sorted(Comparator.reverseOrder())
                        .findFirst().get();

                categoryFor.setParentsId(parentId);
            }
        }
        populateRecursive(level + 1, categoryMap);
    }


    private int countSpaces(Category category) {
        return category.getName().startsWith(" ") ? category.getName().split("\\S")[0].length() : 0;
    }

}
