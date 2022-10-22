package guru.springframework.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;
    @BeforeEach
    public void setUp(){
        System.out.println("executed now...");
        category = new Category();
    }
    @Test
    void getId() {
        Long id= 4L;
        category.setId(id);
        assertEquals(id,category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}