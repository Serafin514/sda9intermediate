package bookStore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class InMemoryCategoryDAOTest {

    //private InMemoryCategoryDAO inMemoryCategoryDAO = Mockito.mock(InMemoryCategoryDAO.class);

    @Test
    void shouldReturnListOfCategoriesFromFile() {
        //given
        InMemoryCategoryDAO inMemoryCategoryDAO = InMemoryCategoryDAO.getInstance();

        Category cat1;
        Integer expectedParentId1 = null;
        Integer expectedParentId3 = 2;
        Category cat3;
        String expectedNameId1 = "Książki";
        String expectedNameId3 = "Fantastyka";

        //when
        List<Category> categoryList = inMemoryCategoryDAO.getCategories();
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
        Assertions.assertEquals(expectedParentId1, actualParentId1);
        Assertions.assertEquals(expectedParentId3, actualParentId3);
        Assertions.assertEquals(expectedNameId1, actualName1);
        Assertions.assertEquals(expectedNameId3, actualName3);
    }

}