package com.example.easyfood42.modele.user;

import android.util.Log;

import com.example.easyfood42.modele.BdSQLiteOpenHelper;

public class Utilisateur {
	private long idU;
	private String mailU;
	private String passwd;
	private String pseudoU;
	private String nomU;
	private String prenomU;
	private String numAdrU;
	private String nomAdrU;
	private String cpU;
	private String villeU;
	private long idTU;

	public Utilisateur(long idU, String mailU, String pseudoU, String passwd, String nomU, String prenomU, String numAdrU, String nomAdrU, String cpU, String villeU, long idTU) {
		this.idU = idU;
		this.mailU = mailU;
		this.passwd = passwd;
		this.pseudoU = pseudoU;
		this.prenomU = prenomU;
		this.nomU = nomU;
		this.numAdrU = numAdrU;
		this.nomAdrU = nomAdrU;
		this.cpU = cpU;
		this.villeU = villeU;
		this.idTU = idTU;
	}

	public long getIdU() {
		return idU;
	}

	public void setIdU(long idU) {
		this.idU = idU;
	}

	public String getMailU() {
		return mailU;
	}

	public void setMailU(String mailU) {
		this.mailU = mailU;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPseudoU() {
		return pseudoU;
	}

	public void setPseudoU(String pseudoU) {
		this.pseudoU = pseudoU;
	}

	public String getNomU() {
		return nomU;
	}

	public void setNomU(String nomU) {
		this.nomU = nomU;
	}

	public String getPrenomU() {
		return prenomU;
	}

	public void setPrenomU(String prenomU) {
		this.prenomU = prenomU;
	}

	public String getNumAdrU() {
		return numAdrU;
	}

	public void setNumAdrU(String numAdrU) {
		this.numAdrU = numAdrU;
	}

	public String getNomAdrU() {
		return nomAdrU;
	}

	public void setNomAdrU(String nomAdrU) {
		this.nomAdrU = nomAdrU;
	}

	public String getCpU() {
		return cpU;
	}

	public void setCpU(String cpU) {
		this.cpU = cpU;
	}

	public String getVilleU() {
		return villeU;
	}

	public void setVilleU(String villeU) {
		this.villeU = villeU;
	}

	public long getIdTU() {
		return idTU;
	}

	public void setIdTU(long idTU) {
		this.idTU = idTU;
	}

	public boolean verifPasswd(String mdpClair){
		Log.d("testLog",this.passwd + " " + BdSQLiteOpenHelper.md5(mdpClair));
		return (BdSQLiteOpenHelper.md5(mdpClair).equals(this.passwd));
	}
}
