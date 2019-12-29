package com.imodule.dao.provider;


import com.imodule.dao.entity.Category;

import java.io.Serializable;
import java.util.ArrayList;

public class Inputparam implements Serializable{
    private ArrayList<String> ids;
    private Category category;

    public Inputparam() {

    }

    public Inputparam(ArrayList<String> ids, Category category) {
        this.ids = ids;
        this.category = category;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
