package Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date createdDate;
    private int catalogId;

    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date createdDate, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.createdDate = createdDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int currentIndex, int currentIndexCategory, Categories[] arrCategories, Product product) {

        boolean isNotCorrectName = true;
        boolean isNotCorrectId = true;
        boolean isNotCorrectCategoryId = true;


        do {
            System.out.println("Please enter your product Id");
            this.productId = scanner.nextLine();
            if (product.isFirstCharacterValid(this.productId)) {
                isNotCorrectId = true;
            } else {
                for (int i = 0; i < currentIndex; i++) {
                    if (this.productId.equals(arrProduct[i].getProductId())) {
                        break;
                    }
                    isNotCorrectId = true;
                }
            }
        } while (isNotCorrectId);

        do {
            System.out.println("Please enter your Product Name");
            this.productName = scanner.nextLine();
            if (this.productName.length() < 10 || this.productName.length() > 50) {
                isNotCorrectName = true;
            } else {
                for (int i = 0; i < currentIndex; i++) {
                    if (this.productName.equals(arrProduct[i].getProductName())) {
                        break;
                    }
                    isNotCorrectName = false;
                }
            }
        } while (isNotCorrectName);

        System.out.println("Please enter price");
        this.price = Float.parseFloat(scanner.nextLine());

        System.out.println("Please enter product description");
        this.description = scanner.nextLine();

        System.out.println("Please enter created Date");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            this.createdDate = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        while (isNotCorrectCategoryId) {
            System.out.println("Please enter Category Id");
            for (int i = 0; i < currentIndexCategory; i++) {
                System.out.printf("%d . %s have ID %d", i + 1, arrCategories[i].getCatalogName(), arrCategories[i].getCatalogId());
            }
            this.catalogId = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < currentIndexCategory; i++) {
                if (arrCategories[i].getCatalogId() == this.catalogId) {
                    isNotCorrectId = false;
                }
            }
        }

        do {
            System.out.println("Please enter Product Status");
            this.productStatus = Integer.parseInt(scanner.nextLine());
            while (this.productStatus != 1 && this.productStatus != 2 && this.productStatus != 0) {
                System.out.println("Please enter your choice from 0-2");
                this.productStatus = Integer.parseInt(scanner.nextLine());
            }
        } while (true);


    }

    public void displayData() {
        System.out.printf("productId: %s productName: %s price: %f description: %s createDate: %tc catalogId: %d Status: %s", this.productId, this.productName, this.price, this.description, this.createdDate, this.catalogId, this.productStatus == 0 ? "Selling" : this.productStatus == 1 ? "Out of products" : "Doesn't sell");
    }

    public void updateProduct(int index, int currentIndexProduct, Product[] arrProduct,int currentIndexCategory, Categories[] arrCategory) {
        Scanner scanner = new Scanner(System.in);
        boolean isNotCorrectProductName = true;

        String preUpdateProductName = scanner.nextLine();
        while (isNotCorrectProductName) {

            for (int i = 0; i < currentIndexProduct; i++) {
                if (arrProduct[i].productName.equals(preUpdateProductName)) {
                    System.out.println("Please re enter your Product Name");
                    preUpdateProductName=scanner.nextLine();
                    break;
                }
                isNotCorrectProductName = false;
            }


        }
        this.productName=preUpdateProductName;

        System.out.println("Please enter product Price");
        String preUpdateProductPrice= scanner.nextLine();
        if(!Objects.equals(preUpdateProductPrice, "")){
            this.price=Float.parseFloat(preUpdateProductPrice);
        }

        System.out.println("Please enter description:");
        String preUpdateDescription = scanner.nextLine();
        if(!Objects.equals(preUpdateDescription,"")){
            this.description= preUpdateDescription;
        }


        //Todo: Please fix scannerDate

        boolean isNotCorrectId = true;
        int preUpdateCatalogId=-1;
        while (isNotCorrectId){
            System.out.println("Please update CatalogId");
            for (int i = 0; i < currentIndexCategory; i++) {
                System.out.println(arrCategory[i].getCatalogId());
            }

            preUpdateCatalogId = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < currentIndexCategory; i++) {
                if (arrCategory[i].getCatalogId() == preUpdateCatalogId) {
                    isNotCorrectId = false;
                }
            }
        }
        this.catalogId= preUpdateCatalogId;

        boolean isNotCorrectProductStatus=true;
        System.out.println("Please enter product Status");
        int preUpdateProductStatus=Integer.parseInt(scanner.nextLine());
        if(preUpdateProductStatus==1||preUpdateProductStatus==2||preUpdateProductStatus==0){
            this.productStatus=preUpdateProductStatus;
        }else {
            while (isNotCorrectProductStatus){
                System.out.println("Please re enter your productId");
                preUpdateProductStatus=Integer.parseInt(scanner.nextLine());
                if(preUpdateProductStatus==0||preUpdateProductStatus==1||preUpdateProductStatus==2){
                    this.productStatus=preUpdateProductStatus;
                    break;
                }
            }
        }


    }


    public boolean isFirstCharacterValid(String input) {
        if (input != null && !input.isEmpty()) {
            char firstChar = input.charAt(0);
            return firstChar == 'C' || firstChar == 'S' || firstChar == 'A';
        }
        return false;
    }
}
