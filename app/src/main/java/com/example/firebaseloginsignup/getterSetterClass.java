package com.example.firebaseloginsignup;

public class getterSetterClass {
    String id;
    String code;
    String name;
    String phone;
    String department;

    public getterSetterClass(){

    }

    public getterSetterClass(String id, String code, String name, String phone, String department) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.department = department;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }
}
