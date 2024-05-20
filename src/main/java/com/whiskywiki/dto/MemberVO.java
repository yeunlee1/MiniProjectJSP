package com.whiskywiki.dto;

public class MemberVO {
    private String userid;
    private String pwd; 
    private String name; 
    private String birthdate; 
    private String email; 
    private int admin; // 상수로 지정하여 기본값을 0(관리자)로 설정

    // Getter 메서드
    public String getUserid() {
        return userid;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public int getAdmin() {
        return admin;
    }

    // Setter 메서드
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    // toString() 메서드 오버라이드
    @Override
    public String toString() {
        return "MemberVO{" +
                "userid='" + userid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
