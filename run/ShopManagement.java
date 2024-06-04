
package ra.Java_collection_BTTH.run;
import ra.Java_collection_BTTH.entity.Categories;
import ra.Java_collection_BTTH.entity.Product;
import java.util.Scanner;

public class ShopManagement {
    public static Categories[] arrCategories = new Categories[100];
    public static int indexCatalog = 0;
    public static Product[] arrProducts = new Product[100];
    public static int indexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**********SHOP MENU***********");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ShopManagement.displayMenuCatalog(scanner);
                    break;
                case 2:
                    ShopManagement.displayProduct(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayMenuCatalog(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("*************CATEGORIES MENU************");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputListCategories(scanner);
                    break;
                case 2:
                    displayListCategories();
                    break;
                case 3:
                    updateCategories(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (isExit);
    }

    public static void inputListCategories(Scanner scanner) {
        System.out.println("Nhập vào số danh mục cần nhập thông tin:");
        int lengthCate = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < lengthCate; i++) {
            arrCategories[indexCatalog] = new Categories();
            arrCategories[indexCatalog].inputData(scanner, arrCategories, indexCatalog, arrProducts, indexProduct);
            indexCatalog++;
        }
    }

    public static void displayListCategories() {
        for (int i = 0; i < indexCatalog; i++) {
            arrCategories[i].displayData();
        }
    }

    public static int getIndexByCatalogId(int catalogId) {
        for (int i = 0; i < indexCatalog; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public static void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật thông tin:");
        int catalogIdUpdate = Integer.parseInt(scanner.nextLine());
        int indexUpdate = getIndexByCatalogId(catalogIdUpdate);
        if (indexUpdate >= 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật trạng thái danh mục");
                System.out.println("3. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên danh mục cần cập nhật:");
                        arrCategories[indexUpdate].setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào trạng thái cần cập nhật:");
                        arrCategories[indexUpdate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 3:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-3");

                }
            } while (isExit);
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void deleteCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogIdDelete = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexByCatalogId(catalogIdDelete);
        if (indexDelete >= 0) {
            for (int i = indexDelete; i < indexCatalog - 1; i++) {
                arrCategories[i] = arrCategories[i + 1];
            }
            arrCategories[indexCatalog - 1] = null;
            indexCatalog--;
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void displayProduct(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("**************PRODUCT MENU****************");
            System.out.println("1. Thêm mới sản phẩm ");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần");
            System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
            System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
            System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputListProduct(scanner);
                    break;
                case 2:
                    displayListProduct();
                    break;
                case 3:
                    updateProductPrice(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    sortProductPrice();
                    break;
                case 6:
                    sortProductName();
                    break;
                case 7:
                    quantityProductByCatalog(scanner);
                    break;
                case 8:
                    searchProductByName(scanner);
                    break;
                case 9:
                    isExit = true;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-9");
            }
        } while (isExit);
    }

    public static void inputListProduct(Scanner scanner) {
        System.out.println("Nhập vào số sản phẩm cần nhập thông tin:");
        int lengthProduct = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < lengthProduct; i++) {
            arrProducts[indexProduct] = new Product();
            arrProducts[indexProduct].inputData(scanner, arrCategories, indexCatalog, arrProducts, indexProduct);
            indexProduct++;
        }
    }

    public static void displayListProduct() {
        for (int i = 0; i < indexProduct; i++) {
            arrProducts[i].displayData();
        }
    }

    public static int getIndexByProductId(String productId) {
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateProductPrice(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật giá:");
        String productId = scanner.nextLine();
        int indexUpdate = getIndexByProductId(productId);
        if (indexUpdate >= 0) {
            arrProducts[indexUpdate].setPrice(arrProducts[indexUpdate].getPrice());
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần xóa:");
        String productIdDelete = scanner.nextLine();
        int indexDelete = getIndexByProductId(productIdDelete);
        if (indexDelete >= 0) {
            for (int i = indexDelete; i < indexProduct - 1; i++) {
                arrProducts[i] = arrProducts[i + 1];
            }
            arrProducts[indexProduct - 1] = null;
            indexProduct--;
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public static void sortProductPrice() {
        for (int i = 0; i < indexProduct - 1; i++) {
            for (int j = i + 1; j < indexProduct; j++) {
                if (arrProducts[i].getPrice() > arrProducts[j].getPrice()) {
                    Product temp = arrProducts[i];
                    arrProducts[i] = arrProducts[j];
                    arrProducts[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp sản phẩm tăng dần theo giá");
    }

    public static void sortProductName() {
        for (int i = 0; i < indexProduct - 1; i++) {
            for (int j = i + 1; j < indexProduct; j++) {
                if (arrProducts[i].getProductName().compareTo(arrProducts[j].getProductName()) > 0) {
                    Product temp = arrProducts[i];
                    arrProducts[i] = arrProducts[j];
                    arrProducts[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp sản phẩm theo tên tăng dần");
    }

    // 7. Thống kê số lượng sản phẩm theo danh mục sản phẩm
    public static void quantityProductByCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int count = 0;
        boolean isCatalog = false;
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getCatalogId() == catalogId) {
                isCatalog = true;
                count++;
            }
        }
        if (!isCatalog) {
            System.err.println("Danh mục" + catalogId + " không có sản phẩm nào nào");
        } else {
            System.out.println("Danh mục" + catalogId + " số lượng sách là: " + count);
        }
    }

    public static void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm:");
        String productNameSearch = scanner.nextLine();
        //Tìm gần đúng
        System.out.println("KẾT QUẢ TÌM KIẾM:");
        for (int i = 0; i < indexProduct; i++) {
            if (arrProducts[i].getProductName().toLowerCase().contains(productNameSearch.toLowerCase())) {
                arrProducts[i].displayData();
            }
        }
    }
}
