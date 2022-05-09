package com.example.easyfood42.modele;

public class Plat {

    private long idP;
    private String nomP;
    private int prixFournisseurP;
    private String prixClientP;
    private boolean platVisible;
    private String photoP;
    private String descriptionP;

    public Plat(long idP, String nomP, int prixFournisseurP, String prixClientP, boolean platVisible, String photoP, String descriptionP) {
        this.idP = idP;
        this.nomP = nomP;
        this.prixFournisseurP = prixFournisseurP;
        this.prixClientP = prixClientP;
        this.platVisible = platVisible;
        this.photoP = photoP;
        this.descriptionP = descriptionP;
    }

    public long getIdP() {
        return idP;
    }

    public void setIdP(long idP) {
        this.idP = idP;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public int getPrixFournisseurP() {
        return prixFournisseurP;
    }

    public void setPrixFournisseurP(int prixFournisseurP) {
        this.prixFournisseurP = prixFournisseurP;
    }

    public String getPrixClientP() {
        return prixClientP;
    }

    public void setPrixClientP(String prixClientP) {
        this.prixClientP = prixClientP;
    }

    public boolean isPlatVisible() {
        return platVisible;
    }

    public void setPlatVisible(boolean platVisible) {
        this.platVisible = platVisible;
    }

    public String getPhotoP() {
        return photoP;
    }

    public void setPhotoP(String photoP) {
        this.photoP = photoP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }
}
