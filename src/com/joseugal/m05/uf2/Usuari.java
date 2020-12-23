package com.joseugal.m05.uf2;


public class Usuari {
    private  String nom;
    private  String cognom;
    private String nombreCompleto;

    public Usuari(String nom, String cg1, String nC){
        this.nom = nom;
        this.cognom = cg1;
        this.nombreCompleto = nC;
    }

    public String getNom () {
        return nom;
    }

    public void setNom ( String nom ) {
        this.nom = nom;
    }

    public String getCognom () {
        return cognom;
    }

    public void setCognom ( String cognom ) {
        this.cognom = cognom;
    }

    public String generaCorreu(int randomNum){
        return (
                this.nom
            +   "."
            +   this.cognom
            +   randomNum
            +   "@"
        );
    }

}
