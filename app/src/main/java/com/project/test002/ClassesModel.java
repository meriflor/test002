package com.project.test002;

public class ClassesModel {
    private String mClassName;
    private String mClassSubject;
    private String mClassSection;
    private String mClassRoom;

    public ClassesModel(String mClassName, String mClassSubject, String mClassSection, String mClassRoom) {
        this.mClassName = mClassName;
        this.mClassSubject = mClassSubject;
        this.mClassSection = mClassSection;
        this.mClassRoom = mClassRoom;
    }

    public String getMClassName() {
        return mClassName;
    }
    public void setMClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public String getMClassSubject() {
        return mClassSubject;
    }
    public void setMClassSubject(String mClassSubject) {
        this.mClassSubject = mClassSubject;
    }

    public String getMClassSection() {
        return mClassSection;
    }
    public void setMClassSection(String mClassSection) {
        this.mClassSection = mClassSection;
    }

    public String getMClassRoom() {
        return mClassRoom;
    }
    public void setMClassRoom(String mClassRoom) {
        this.mClassRoom = mClassRoom;
    }


}
