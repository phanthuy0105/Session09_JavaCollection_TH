package ra.Java_collection_BTTH.entity;

import ra.Java_collection_BTTH.IShop;
import ra.Java_collection_BTTH.run.ShopManagement;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IShop {
    private String productId;
    private String productName;
    private float price;
    private String productTitle;
    private int catalogId;
    private boolean productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String productTitle, int catalogId, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productTitle = productTitle;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public boolean checkUnique(String strCheck, Product[] arrProduct, int indexProduct, String attName) {
        for (int i = 0; i < indexProduct; i++) {
            switch (attName) {
                case "productId":
                    if (arrProduct[i].getProductId().equals(strCheck)) {
                        return true;
                    }
                    break;
                case "productName":
                    if (arrProduct[i].getProductName().equals(strCheck)){
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public String inputProductId(Scanner scanner, Product[] arrProduct, int indexProduct) {
        System.out.println("Nhập vào mã sản phẩm: ");
        do {
            String productId = scanner.nextLine();
            String productIdRegex = "[P][\\w]{4}";
            //Kiểm tra trùng lặp
            boolean isExist = checkUnique(productId, arrProduct, indexProduct, "productId");
            if(Pattern.matches(productIdRegex, productId)) {
                if(isExist) {
                    System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productId;
                }
            } else {
                System.err.println("Mã sản phẩm gồm 5 ký tự, bắt đầu bằng ký tự P, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner, Product[] arrProduct, int indexProduct) {
        System.out.println("Nhập vào tên sản phẩm");
        do {
            String productName = scanner.nextLine();
            boolean isExit = checkUnique(productName, arrProduct, indexProduct, "productName");
            if (isExit) {
                System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
            } else {
                break;
            }
        } while (true);
        return productName;
    }

    public float inputProductPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sản phẩm");
        do {
            try {
                float productPrice = Float.parseFloat(scanner.nextLine());
                if (productPrice > 0) {
                    return productPrice;
                } else {
                    System.err.println("Giá sản phẩm phải lớn hơn 0");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Giá nhập vào phải là số thực");
            }
        } while (true);
    }

    public String inputProductTitle(Scanner scanner) {
        System.out.println("Nhập vào tiêu đề sản phẩm");
        return productTitle = scanner.nextLine();
    }


    public int inputCatalogId(Scanner scanner) {
        //1. Hiển thị danh sách menu danh mục đang quản lý
        //2. Cho phép chọn danh mục mà sản phẩm thuộc về
        //3. Trả về mã danh mục mà người dùng chọn
        for (int i = 0; i < ShopManagement.indexCatalog; i++) {
            System.out.printf("%d.%s\n", (i + 1), ShopManagement.arrCategories[i].getCatalogName());
        }
        System.out.print("Chọn danh mục:");
        int choice = Integer.parseInt(scanner.nextLine());
        return ShopManagement.arrCategories[choice - 1].getCatalogId();
    }

    public String getCatalogNameById(int catalogId) {
        for (int i = 0; i < ShopManagement.indexCatalog; i++) {
            if (ShopManagement.arrCategories[i].getCatalogId() == catalogId) {
                return ShopManagement.arrCategories[i].getCatalogName();
            }
        }
        return "";
    }

    public boolean inputProductStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm");
        do {
            String productStatus = scanner.nextLine();
            if(productStatus.equals("true") || productStatus.equals("false")) {
                return Boolean.parseBoolean(productStatus);
            } else {
                System.err.println("Trạng thái sản phẩm chỉ nhận giá trị true | false, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void inputData(Scanner scanner, Categories[] arrCategories, int indexCatalog, Product[] arrProduct, int indexProduct) {
            this.productId = inputProductId(scanner, arrProduct, indexProduct);
            this.productName = inputProductName(scanner, arrProduct, indexProduct);
            this.price = inputProductPrice(scanner);
            this.productTitle = inputProductTitle(scanner);
            this.catalogId = inputCatalogId(scanner);
            this.productStatus = inputProductStatus(scanner);
    }

    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá sản phẩm: %f\n",
                this.productId, this.productName, this.price);
        System.out.printf("Tiêu đề sản phẩm: %s - Danh mục sản phẩm: %s - Trạng thái: %s\n",
                this.productTitle, getCatalogNameById(this.catalogId), this.productStatus ? "Đang bán" : "Hết hàng");
    }
}
