package com.example.tp_java_fx_jdbc;

public class Users {
    private String nom ;
    private String prenom ;
    private String Adresse ;
    private String tel ;
    private String email ;
    private String function ;

    public Users(String nom, String prenom, String adresse, String tel, String email, String function) {
        this.nom = nom;
        this.prenom = prenom;
        Adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.function = function;
    }
    // les getters et les setters :

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
