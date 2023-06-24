package org.Sikoling.ejb.main.repository.log;

import java.io.Serializable;
import java.time.LocalDate;

import org.Sikoling.ejb.main.repository.otoritas.AutorisasiData;
import org.Sikoling.ejb.main.repository.permohonan.PosisiTahapPemberkasanData;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_flow_log")
public class FlowLogData implements Serializable {

	private static final long serialVersionUID = 6708500898590911457L;
	
	@Id
	private String id;
	
	private LocalDate tanggal;	
	
	@JoinColumn(name = "kategori_log", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriLogData kategoriLogData;
	
	@JoinColumn(name = "posisi_tahap_pemberkasan_pengirim", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PosisiTahapPemberkasanData posisiTahapPemberkasanPengirimData;
	
	@JoinColumn(name = "posisi_tahap_pemberkasan_penerima", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private PosisiTahapPemberkasanData posisiTahapPemberkasanPenerimaData;
	
	@JoinColumn(name="status_flow", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private StatusFlowLogData statusFlowData;
	
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

	public PosisiTahapPemberkasanData getPosisiTahapPemberkasanPengirimData() {
		return posisiTahapPemberkasanPengirimData;
	}

	public void setPosisiTahapPemberkasanPengirimData(PosisiTahapPemberkasanData posisiTahapPemberkasanPengirimData) {
		this.posisiTahapPemberkasanPengirimData = posisiTahapPemberkasanPengirimData;
	}

	public PosisiTahapPemberkasanData getPosisiTahapPemberkasanPenerimaData() {
		return posisiTahapPemberkasanPenerimaData;
	}

	public void setPosisiTahapPemberkasanPenerimaData(PosisiTahapPemberkasanData posisiTahapPemberkasanPenerimaData) {
		this.posisiTahapPemberkasanPenerimaData = posisiTahapPemberkasanPenerimaData;
	}

	public StatusFlowLogData getStatusFlowData() {
		return statusFlowData;
	}

	public void setStatusFlowData(StatusFlowLogData statusFlowData) {
		this.statusFlowData = statusFlowData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
