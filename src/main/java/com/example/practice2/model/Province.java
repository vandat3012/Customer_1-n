package com.example.practice2.model;

public class Province {
    private Integer idProvince;
    private String nameProvince;

    public Province() {
    }

    public Province(Integer id, String nameProvince) {
        this.idProvince = id;
        this.nameProvince = nameProvince;
    }

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }
}
