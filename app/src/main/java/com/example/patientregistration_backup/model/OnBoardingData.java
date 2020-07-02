package com.example.patientregistration_backup.model;

public class OnBoardingData {

    String title;
    String desc;
    Integer ImageUr;

    public OnBoardingData(String title, String desc, Integer imageUr) {
        this.title = title;
        this.desc = desc;
        ImageUr = imageUr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getImageUr() {
        return ImageUr;
    }

    public void setImageUr(Integer imageUr) {
        ImageUr = imageUr;
    }
}
