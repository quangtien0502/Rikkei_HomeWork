package Entity;

import java.util.Objects;
import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private  boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
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

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner,Categories[] arrCategories,int currentIndex){
        int max;
        boolean isNotCorrectName=true;
        if(currentIndex<1){
            this.catalogId=1;
        }else {
            max=arrCategories[0].getCatalogId();
            for (int i = 0; i < currentIndex; i++) {
                if(arrCategories[i].getCatalogId()>max){
                    max=arrCategories[i].getCatalogId();
                }
            }


            this.catalogId=max+1;
        }

        do {
            System.out.println("Please enter Catalog name: ");
            this.catalogName=scanner.nextLine();
            if(currentIndex>0){
                for (int i = 0; i < currentIndex; i++) {
                    if(arrCategories[i].getCatalogName().equals(this.catalogName)){
                        isNotCorrectName=true;
                        break;
                    }
                    isNotCorrectName=false;
                }
            }else {
                isNotCorrectName=false;
            }


        }while (isNotCorrectName);

        System.out.println("Please enter Catalog description");
        this.descriptions= scanner.nextLine();

        do {
            String preCatalogStatus;
            System.out.println("Please enter Catalog Status");
            preCatalogStatus=scanner.nextLine();
            if(preCatalogStatus.equals("true")||preCatalogStatus.equals("false")){
                this.catalogStatus=Boolean.parseBoolean(preCatalogStatus);
                break;
            }
        }while (true);
    }

    public void displayData(){
        System.out.printf("Catalog Id: %d Catalog name: %s Catalog Descriptions: %s Catalog Status: %s \n",this.catalogId,this.catalogName,this.descriptions,this.catalogStatus?"Active":"Un Active");
    }

    public void updateData(int index,Scanner scanner,Categories[] arrCategories,int currentIndex){
        boolean isNotCorrectCategoryName=true;
        while (isNotCorrectCategoryName){
            System.out.println("Please enter Category name: ");
            String preUpdateCatalogName=scanner.nextLine();
            if(!Objects.equals(preUpdateCatalogName, "")){
                arrCategories[index].catalogName=preUpdateCatalogName;
                for (int i = 0; i < currentIndex; i++) {
                    if(i!=index){
                        if(arrCategories[index].catalogName.equals(arrCategories[i].getCatalogName())){

                            break;
                        }
                    }
                    isNotCorrectCategoryName=false;
                }

            }


        }
        System.out.println("Please enter Description");
        String preUpdateDescription=scanner.nextLine();
        if(!Objects.equals(preUpdateDescription, "")){
            arrCategories[index].descriptions=preUpdateDescription;
        }

        String preUpdateCatalogStatus=scanner.nextLine();

        if(!Objects.equals(preUpdateCatalogStatus, "")){
            while ((!preUpdateCatalogStatus.equals("true"))&&(!preUpdateCatalogStatus.equals("false"))){
                System.out.println("Please enter Status only with true or false");
                preUpdateCatalogStatus=scanner.nextLine();
            }
            arrCategories[index].catalogStatus=Boolean.parseBoolean(preUpdateCatalogStatus);
        }



    }

    public void updateStatus(int index,Scanner scanner,Categories[] arrCategories){

            System.out.println("Please enter status");
            String preUpdateCatalogStatus=scanner.nextLine();
            if(!Objects.equals(preUpdateCatalogStatus, "")){
                while ((!preUpdateCatalogStatus.equals("true"))&&(!preUpdateCatalogStatus.equals("false"))){
                    System.out.println("Please enter Status only with true or false");
                    preUpdateCatalogStatus=scanner.nextLine();
                }
                arrCategories[index].catalogStatus=Boolean.parseBoolean(preUpdateCatalogStatus);
            }
        }

}
