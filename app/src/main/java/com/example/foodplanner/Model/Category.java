package com.example.foodplanner.Model;

public class Category {
    private String strCategoryThumb;
    private String strCategory;

    public Category(String strCategoryThumb, String strCategory) {
        this.strCategoryThumb = strCategoryThumb;
        this.strCategory = strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
}
