package org.Sikoling.ejb.main.data.converter;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.KelompokBentukUsaha;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.main.data.BentukUsahaData;
import org.Sikoling.ejb.main.data.BidangUsahaData;
import org.Sikoling.ejb.main.data.DesaData;
import org.Sikoling.ejb.main.data.JabatanData;
import org.Sikoling.ejb.main.data.JenisKelaminData;
import org.Sikoling.ejb.main.data.KabupatenData;
import org.Sikoling.ejb.main.data.KecamatanData;
import org.Sikoling.ejb.main.data.KelompokBentukUsahaData;
import org.Sikoling.ejb.main.data.PemrakarsaData;
import org.Sikoling.ejb.main.data.PenanggungJawabPemrakarsaData;
import org.Sikoling.ejb.main.data.PermohonanData;
import org.Sikoling.ejb.main.data.ProdukData;
import org.Sikoling.ejb.main.data.PropinsiData;

public class ConverterPermohonan {
	
	public static PermohonanData toDataTable(Permohonan permohonan) {
		PermohonanData permohonanData = new PermohonanData();
		
		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
		
		BidangUsaha bidangUsaha = permohonan.getBidangUsaha();
		BidangUsahaData bidangUsahaData = new BidangUsahaData(bidangUsaha.getId(), bidangUsaha.getKeterangan());
		permohonanData.setBidangUsaha(bidangUsahaData);

		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
		
		Pemrakarsa pemrakarsa = permohonan.getSuratPermohonan().getPemrakarsa();
		BentukUsaha bentukUsaha = pemrakarsa.getBentukUsaha();
		KelompokBentukUsaha kelompokBentukUsaha = bentukUsaha.getKelompokUsaha();
		KelompokBentukUsahaData kelompokBentukUsahaData = new KelompokBentukUsahaData(kelompokBentukUsaha.getId(), kelompokBentukUsaha.getNama());
		BentukUsahaData bentukUsahaData = new BentukUsahaData(bentukUsaha.getId(), kelompokBentukUsahaData, bentukUsaha.getNama());
		PenanggungJawab penanggungJawab = pemrakarsa.getPenanggungJawab();
		PenanggungJawabPemrakarsaData penanggungJawabPemrakarsaData = new PenanggungJawabPemrakarsaData();
		//String id, DesaData desa, String detailAlamat, JabatanData jabatan,
//		KabupatenData kabupaten, KecamatanData kecamatan, String nama, String nomorHandphone, String nomorIdentitas,
//		PropinsiData propinsi, JenisKelaminData sex
		PemrakarsaData pemrakarsaData = new PemrakarsaData(
				pemrakarsa.getId(), pemrakarsa.getEmail(), bentukUsahaData, pemrakarsa.getNama(), pemrakarsa.getNamaNotaris(), 
				penanggungJawabPemrakarsaData, pemrakarsa.getNomorIndukBerusaha(), pemrakarsa.getNpwp(), pemrakarsa.getTanggalNotaris(),
				pemrakarsa.getTanggalOSS());
		permohonanData.setPemrakarsa(pemrakarsaData);
		
		Produk produk = permohonan.getProduk();
		ProdukData produkData = new ProdukData();
		
		
		return permohonanData;
	}
	
	public static Permohonan toClassObject(PermohonanData permohonanData) {
		return null;
	}
}
