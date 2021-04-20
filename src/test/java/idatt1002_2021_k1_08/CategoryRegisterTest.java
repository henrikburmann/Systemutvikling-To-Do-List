package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CategoryRegisterTest {
    static CategoryRegister catReg;

    @BeforeAll
    static void setUp(){
        catReg = new CategoryRegister();
        catReg.addCategory(new Category("testName"));
    }

    @Test
    public void addCategory_category_added(){
        Category c2 = new Category("testName2");
        Assertions.assertDoesNotThrow(() -> catReg.addCategory(c2));
    }

    @Test
    public void removeCategory_category_added(){
        Category temp = catReg.getCategories().get(0);
        Assertions.assertDoesNotThrow(() -> catReg.removeCategory(temp));
    }

    @Test
    public void addCategory_categoryAlreadyIn_ShouldThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            catReg.addCategory(new Category("testName"));
        });
    }

    @Test
    public void removeCategory_categoryNotIn_ShouldThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            catReg.removeCategory(new Category("testName3"));
        });
    }

}
