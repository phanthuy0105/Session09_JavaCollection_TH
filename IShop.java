package ra.Java_collection_BTTH;

import ra.Java_collection_BTTH.entity.Categories;
import ra.Java_collection_BTTH.entity.Product;

import java.util.Scanner;

public interface IShop {
    void inputData(Scanner scanner, Categories[] arrCategories, int indexCatalog, Product[] arrProduct, int indexProduct);
    void displayData();
}
