package HomeWork.Entity;

import Entity.Book;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float importPrice ;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    public Product() {
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

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(int currentIndex, Product[] arrProduct){
        boolean isNotCorrectProductId=true,isNotCorrectProductName=true;
        Scanner scanner= new Scanner(System.in);
        System.out.println("Please enter your ProductId");
        this.productId=scanner.nextLine();
        while (isNotCorrectProductId){
            if(this.productId.length()==4 && this.productId.charAt(0)=='P'){
                if(currentIndex>1){
                    for (int i = 0; i < currentIndex-1; i++) {
                        if(arrProduct[i].productId.equals(this.productName)){
                            System.out.println("Please re enter your Book Name again");
                            this.productId=scanner.nextLine();
                            break;
                        }
                        isNotCorrectProductId=false;
                    }
                }else {
                    isNotCorrectProductId=false;
                }
            }else {
                System.out.println("Please enter your Book Name again");
                this.productId=scanner.nextLine();
            }
        }

        System.out.println("Please enter your Book Name");
        this.productName=scanner.nextLine();

        if(currentIndex>1){
            while (isNotCorrectProductName){
                for (int i = 0; i < currentIndex-1; i++) {
                    if(arrProduct[i].productName.equals(this.productName)){
                        System.out.println("Please re enter your Book Id again");
                        this.productName=scanner.nextLine();
                        break;
                    }
                    isNotCorrectProductName=false;
                }
            }
        }

        System.out.println("Please enter import Price");
        this.importPrice=Float.parseFloat(scanner.nextLine());
        while (true){
            if(this.importPrice>0){

                break;
            }else {
                System.out.println("Please re enter your import Price");
                this.importPrice=Float.parseFloat(scanner.nextLine());
            }

        }

        System.out.println("Please enter your export Price");
        this.exportPrice=Float.parseFloat(scanner.nextLine());

        while (true){
            if(this.exportPrice>=1.2*this.importPrice){

                break;
            }else {
                System.out.println("Please re enter your import Price");
                this.importPrice=Float.parseFloat(scanner.nextLine());
            }

        }

        System.out.println("Please enter quantity");
        this.quantity =Integer.parseInt(scanner.nextLine());

        while (true){
            if(this.quantity>0){

                break;
            }else {
                System.out.println("Please re enter your quantity");
                this.quantity =Integer.parseInt(scanner.nextLine());
            }

        }

        System.out.println("Please enter description");
        this.descriptions=scanner.nextLine();


        System.out.println("Please enter product status");
        String preInputStatus= scanner.nextLine();

        while(!preInputStatus.equals("true")&& !preInputStatus.equals("false")){
            System.out.println("Status only receive value true or false , please re enter your status");
            preInputStatus=scanner.nextLine();

        }

        this.status=Boolean.parseBoolean(preInputStatus);


    }

    public void calculateInterest(){
        this.profit=this.exportPrice-this.importPrice;
    }
    public void displayData(){
        System.out.printf("Product ID: %s product Name: %s import Price: %f export Price: %f profit : %f quantity: %d description: %s status: %b",productId,productName,importPrice,exportPrice,quantity,descriptions,status);
    }
}
