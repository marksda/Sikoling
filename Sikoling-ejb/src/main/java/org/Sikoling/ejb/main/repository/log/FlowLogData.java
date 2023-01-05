package org.Sikoling.ejb.main.repository.log;

import java.io.Serializable;
import java.time.LocalDate;

import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.permohonan.PosisiTahapPemberkasanData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_flow_log")
@NamedQueries({
	@NamedQuery(name="FlowLogData.findAll", query="SELECT p FROM FlowLogData p"),
	@NamedQuery(name="FlowLogData.findByIdPengakses", 
		query="SELECT d FROM FlowLogData d WHERE d.pengaksesData.id = :idPengakses"),
	@NamedQuery(name="FlowLogData.findByIdKategoriLog", 
		query="SELECT d FROM FlowLogData d WHERE d.kategoriLogData.id = :idKategoriLog"),
})
public class FlowLogData implements Serializable {

	private static final long serialVersionUID = 6708500898590911457L;
	
	@Id
	private String id;
	
	private LocalDate tanggal;	
	
	@JoinColumn(name = "kategori_log", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriLogData kategoriLogData;
	
	@JoinColumn(name = "posisi_tahap_pemberkasan", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PosisiTahapPemberkasanData posisiTahapPemberkasanData;
	
	private String keterangan;
	
	@JoinColumn(name = "pengakses", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private AutorisasiData pengaksesData;
	
	@OneToOne(mappedBy = "flowLog", cascade = CascadeType.PERSIST)
	private FlowLogPermohonanData flowLogPermohonanData;

	public FlowLogData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getTanggal() {
		return tanggal;
	}

	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}

	public KategoriLogData getKategoriLogData() {
		return kategoriLogData;
	}

	public void setKategoriLogData(KategoriLogData kategoriLogData) {
		this.kategoriLogData = kategoriLogData;
	}

	public PosisiTahapPemberkasanData getPosisiTahapPemberkasanData() {
		return posisiTahapPemberkasanData;
	}

	public void setPosisiTahapPemberkasanData(PosisiTahapPemberkasanData posisiTahapPemberkasanData) {
		this.posisiTahapPemberkasanData = posisiTahapPemberkasanData;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public AutorisasiData getPengaksesData() {
		return pengaksesData;
	}

	public void setPengaksesData(AutorisasiData pengaksesData) {
		this.pengaksesData = pengaksesData;
	}
	
	public FlowLogPermohonanData getFlowLogPermohonanData() {
		return flowLogPermohonanData;
	}

	public void setFlowLogPermohonanData(FlowLogPermohonanData flowLogPermohonanData) {
		this.flowLogPermohonanData = flowLogPermohonanData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
