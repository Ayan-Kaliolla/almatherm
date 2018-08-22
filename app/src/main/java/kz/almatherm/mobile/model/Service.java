package kz.almatherm.mobile.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Service {
    @PrimaryKey
    private int id;
    private String image;
    private String link;
    private String name;
    @Embedded
    private ArrayList<SubService> subServices;

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

    public ArrayList<SubService> getSubServices() {
        return subServices;
    }

    public void setSubServices(ArrayList<SubService> subServices) {
        this.subServices = subServices;
    }
}
