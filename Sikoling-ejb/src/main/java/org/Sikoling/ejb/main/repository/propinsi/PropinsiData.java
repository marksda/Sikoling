package org.Sikoling.ejb.main.repository.propinsi;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_propinsi database table.
 * 
 */
@Entity
@Table(name="master.tbl_propinsi")
@NamedQueries({
@NamedQuery(name="PropinsiData.findAll", query="SELECT p FROM PropinsiData p"),
@NamedQuery(name="PropinsiData.findAllByName", query="SELECT p FROM PropinsiData p WHERE p.nama ILIKE :nama")})
public class PropinsiData implements Serializable {
	private static final long serialVersionUID = -299594010656178333L;

	@Id
	private String id;

	@Column(name="nama")
	private String nama;

	public PropinsiData() {
	}

	public PropinsiData(String id, String nama) {
		this.id = id;
		this.nama = nama;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}