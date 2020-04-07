package com.sajithan.android.srilankavscorona;

public class ItemApi {
    private String ItemUrl;
    private String CategoryUrl;
    private String InsertIMEInum;
    private String GetCompanyCode;
    private String CompanyMenuLoadURL;
    private String CompositeItemUrl;
    private String CoronaUrl;


    public ItemApi(){
        ItemUrl= "Login/GetAllItems?CKy=";
        CategoryUrl = "Login/GetCategories?CKy=";
        CompositeItemUrl="Login/CompositeItem?mainItemKey=";
        CoronaUrl="https://hpb.health.gov.lk/api/get-current-statistical?";
    }
    public String getGetCompanyCode(){
        return "Login/GetCompanyCode/";
    }

    public String getItems() {
        return ItemUrl;
    }

    public String getCorona(){return CoronaUrl;}

    public String getCompositeItem() {
        return CompositeItemUrl;
    }

    public String getItemsByCategory() {
        return "Login/GetAllItemsByCategory?CategoryKey=";
    }

    public String searchTable() {
        return "Login/SearchTable?CKy=";
    }

    public String getCategoryUrl() {
        return CategoryUrl;
    }

    public String ItemSearch(int Cky,String Search) {
        return "Login/SearchItem?CKy="+Cky+"&searchquery="+Search;
    }

    public void setCategoryUrl(String categoryUrl) {
        CategoryUrl = "Login/GetCategories?CKy=";
    }
}
