package kz.almatherm.mobile.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Category {
    @PrimaryKey
    private int id;
    private String image;
    private String link;
    private String name;
    @Embedded
    private ArrayList<SubCategory> subCatalogs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubCategory> getSubCatalogs() {
        return subCatalogs;
    }

    public void setSubCatalogs(ArrayList<SubCategory> subCatalogs) {
        this.subCatalogs = subCatalogs;
    }
}
