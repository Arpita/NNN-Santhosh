package com.example.kvsan.restapi.datamodel;

public class DataModel {
    private String categoryName;
    private String uri;
    private String link;

    public DataModel() {
        //Default Constructor
    }

    public DataModel(String categoryName, String uri, String link) {
        this.categoryName = categoryName;
        this.uri = uri;
        this.link = link;
    }

    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
