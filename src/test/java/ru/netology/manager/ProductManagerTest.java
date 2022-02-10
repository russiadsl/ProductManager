package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repo.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private final Product firstProduct = new Product(1, "Milk", 3);
    private final Product secondProduct = new Product(2, "Bread", 3);
    private final Book firstBook = new Book(3, "Harry Potter", 25, "Joanne Kathleen Rowling");
    private final Book secondBook = new Book(4, "A Game of Thrones", 4, "George R. R. Martin");
    private final Smartphone firstSmartphone = new Smartphone(5, "IPhone 11 ProMax", 800, "Apple");
    private final Smartphone secondSmartphone = new Smartphone(6, "IPhone 12", 1000, "Apple");

    private final ProductManager manager = new ProductManager(new ProductRepository());

    @BeforeEach
    void saveGoods() {
        manager.save(firstProduct);
        manager.save(secondProduct);
        manager.save(firstBook);
        manager.save(secondBook);
        manager.save(firstSmartphone);
        manager.save(secondSmartphone);
    }

    @Test
    void shouldReturnByManufacturer() {

        Product[] expected = {firstSmartphone,secondSmartphone};
        Product[] actual = manager.searchBy("Apple");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnByAuthor() {

        Product[] expected = {secondBook};
        Product[] actual = manager.searchBy("Martin");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnByProductName() {

        Product[] expected = {secondProduct};
        Product[] actual = manager.searchBy("Bread");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnByNonExistentManufacturer() {

        Product[] expected = {};
        Product[] actual = manager.searchBy("Samsung");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnByNonExistentProductName() {

        Product[] expected = {};
        Product[] actual = manager.searchBy("Butter");

        assertArrayEquals(expected, actual);
    }
}