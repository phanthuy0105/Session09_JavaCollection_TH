package ra.Java_collection_BTTH.entity;

import ra.Java_collection_BTTH.IShop;

import java.util.Scanner;

public class Categories implements IShop {
    private int catalogId;
    private String catalogName;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public int inputCatalogId(Scanner scanner, Categories[] arrCategories, int indexCatalog) {
        System.out.println("Nhập vào mã danh mục:");
        do {
            try {
                int catalogId = Integer.parseInt(scanner.nextLine());
                boolean isExist = false;
                for (int i = 0; i < indexCatalog; i++) {
                    if (arrCategories[indexCatalog].getCatalogId() == catalogId) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                } else {
                    return catalogId;
                }
                if (catalogId > 0) {
                    return catalogId;
                } else {
                    System.err.println("Mã danh mục lớn hơn 0");
                }
            } catch (NumberFormatException ex) {
                System.err.println("Mã danh mục phải là số nguyên");
            }
        } while (true);
    }

    public String inputCatalogName(Scanner scanner, Categories[] arrCategories, int indexCatalog) {
        System.out.println("Nhập vào tên danh mục");
        String catalogName = scanner.next();
        do {
            boolean isExist = false;
            for (int i = 0; i < indexCatalog; i++) {
                if (arrCategories[indexCatalog].getCatalogName().equals(catalogName)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
            } else {
                return catalogName;
            }
        } while (true);
    }

    public boolean inputCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục: ");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận giá trị true | false, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void inputData(Scanner scanner, Categories[] arrCategories, int indexCatalog, Product[] arrProduct, int indexProduct) {
        this.catalogId = inputCatalogId(scanner, arrCategories, indexCatalog);
        this.catalogName = inputCatalogName(scanner, arrCategories, indexCatalog);
        this.catalogStatus = inputCatalogStatus(scanner);
    }

    public void displayData() {
        System.out.printf("Catalog Id: %d - Catalog Name: %s - Catalog Status: %s", this.catalogId, this.catalogName, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
