package com.example.easyfood42;

public class TypeUtilisateur {
    private long idTU;
    private String libelleTU;

    public TypeUtilisateur(long idTU, String libelleTU) {
        this.idTU = idTU;
        this.libelleTU = libelleTU;
    }

    public long getIdTU() {
        return idTU;
    }

    public void setIdTU(long idTU) {
        this.idTU = idTU;
    }

    public String getLibelleTU() {
        return libelleTU;
    }

    public void setLibelleTU(String libelleTU) {
        this.libelleTU = libelleTU;
    }

    @Override
    public String toString() {
        return "TypeUtilisateur{" +
                "idTU=" + idTU +
                ", libelleTU='" + libelleTU + '\'' +
                '}';
    }
}
