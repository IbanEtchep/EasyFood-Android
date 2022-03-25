package com.example.easyfood42;

import android.util.Log;

public class Utilisateur {
	private long idU;
	private String mailU;
	private String passwd;
	private long idTU;

	public Utilisateur(long idU, String mailU, String passwd, long idTU) {
		this.idU = idU;
		this.mailU = mailU;
		this.passwd = passwd;
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

	public long getIdTU() {
		return idTU;
	}

	public void setIdTU(long idTU) {
		this.idTU = idTU;
	}

	@Override
	public String toString() {
		return "Utilisateur{" +
				"idU=" + idU +
				", mailU='" + mailU + '\'' +
				", passwd='" + passwd + '\'' +
				", idTU=" + idTU +
				'}';
	}

	public boolean verifPasswd(String mdpClair){
		return (BdSQLiteOpenHelper.md5(mdpClair).equals(this.passwd));
	}
}
