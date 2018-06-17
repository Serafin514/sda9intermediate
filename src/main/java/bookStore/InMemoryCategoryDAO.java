package bookStore;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryCategoryDAO implements CategorySource {
    //DAO DATA ACCESS OBJECT
    //DTO DATA TRANSFER OBJECT

    private static InMemoryCategoryDAO instance;
    private List<Category> categoryInMemorys;

    private InMemoryCategoryDAO() {
        categoryInMemorys = initializeCategory();
    }

    public static InMemoryCategoryDAO getInstance() {
        if (instance == null) {//sprawdzamy na wydajnosc pracy wielowatkowej
            synchronized (InMemoryCategoryDAO.class) {
                if (instance == null) {
                    instance = new InMemoryCategoryDAO();
                }
            }
        }
        return instance;
    }

    private List<Category> initializeCategory() {
        List<String> linesFormFile = readDataFromFile();
        return populateCategorys(linesFormFile);
    }

    public List<String> readDataFromFile() {
        List<String> linesFormFile = null;
        try {
            linesFormFile = Files.readAllLines
                    (Paths.get("C:\\Projekty_java\\sda9intermediate\\src\\main\\resources\\kategorie.txt"), Charset.forName("UNICODE"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesFormFile;
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
        if (categoryList == null) {
            return;
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

    @Override
    public void updateCategory(Category category) {
        throw new NotImplementedException();
    }

    @Override
    public List<Category> findCategoriesByName(String name) {
        return categoryInMemorys.stream()
                .filter(category -> category.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getCategories() {
        return categoryInMemorys;
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoryInMemorys.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }
}
