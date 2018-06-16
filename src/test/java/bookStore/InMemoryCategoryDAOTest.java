package bookStore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCategoryDAOTest {
    @Test
    void shouldReturnListOfCategoriesFromFile() {
        //given
        InMemoryCategoryDAO inMemoryCategoryDAO = new InMemoryCategoryDAO();
        Category cat1;
        Integer expectedParentId1 = 1;
        Integer expectedParentId3 = 3;
        Category cat3;
        String expectedNameId1 = "Książki";
        String expectedNameId3 = "Fantastyka";

        //when
        List<Category> categoryList = inMemoryCategoryDAO.initializeCategory();
        cat1 = categoryList.stream()
                .filter(e -> e.getId().equals(1))
                .findFirst()
                .get();
        Integer actualParentId1 = cat1.getParentsId();
        String actualName1 = cat1.getName();

        cat3 = categoryList.stream()
                .filter(e -> e.getId().equals(3))
                .findFirst()
                .get();
        Integer actualParentId3 = cat3.getParentsId();
        String actualName3 = cat3.getName();
        //then
        Assertions.assertEquals(expectedParentId1,actualParentId1);
        Assertions.assertEquals(expectedParentId3,actualParentId3);
        Assertions.assertEquals(expectedNameId1,actualName1);
        Assertions.assertEquals(expectedNameId3,actualName3);
    }

}