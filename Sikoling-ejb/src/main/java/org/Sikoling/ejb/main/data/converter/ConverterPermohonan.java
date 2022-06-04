package org.Sikoling.ejb.main.data.converter;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.KelompokBentukUsaha;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
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
		
		
		
		
		BidangUsaha bidangUsaha = permohonan.getBidangUsaha();
		Pemrakarsa pemrakarsa = permohonan.getSuratPermohonan().getPemrakarsa();
		BentukUsaha bentukUsaha = pemrakarsa.getBentukUsaha();
		KelompokBentukUsaha kelompokBentukUsaha = bentukUsaha.getKelompokUsaha();
		PenanggungJawab penanggungJawab = pemrakarsa.getPenanggungJawab();
		Alamat alamat =  penanggungJawab.getAlamat();
		Desa desa = alamat.getDesa();
		Kecamatan kecamatan = alamat.getKecamatan();		
		Kabupaten kabupaten = alamat.getKabupaten();
		Propinsi propinsi = alamat.getPropinsi();
		Jabatan jabatan = penanggungJawab.getJabatan();
		JenisKelamin jenisKelamin = penanggungJawab.getJenisKelamin();
		
		BidangUsahaData bidangUsahaData = new BidangUsahaData(bidangUsaha.getId(), bidangUsaha.getKeterangan());
		KelompokBentukUsahaData kelompokBentukUsahaData = new KelompokBentukUsahaData(kelompokBentukUsaha.getId(), kelompokBentukUsaha.getNama());
		BentukUsahaData bentukUsahaData = new BentukUsahaData(bentukUsaha.getId(), kelompokBentukUsahaData, bentukUsaha.getNama());
		PropinsiData propinsiData = new PropinsiData(propinsi.getId(), propinsi.getNama());
		KabupatenData kabupatenData = new KabupatenData(kabupaten.getId(), kabupaten.getNama(), propinsiData);
		KecamatanData kecamatanData = new KecamatanData(kecamatan.getId(), kabupatenData, kecamatan.getNama());
		DesaData desaData = new DesaData(desa.getId(), kecamatanData, desa.getNama());
		JabatanData jabatanData = new JabatanData(jabatan.getId(), jabatan.getNama());
		JenisKelaminData jenisKelaminData = new JenisKelaminData(jenisKelamin.getId(), jenisKelamin.getNama());
		
		PenanggungJawabPemrakarsaData penanggungJawabPemrakarsaData = new PenanggungJawabPemrakarsaData(
				penanggungJawab.getId(), desaData, alamat.getKeterangan(), jabatanData, kabupatenData, kecamatanData,
				penanggungJawab.getNama(), penanggungJawab.getNoHandphone(), penanggungJawab.getNoIdentitas(),
				propinsiData, jenisKelaminData);
		
		PemrakarsaData pemrakarsaData = new PemrakarsaData(
				pemrakarsa.getId(), pemrakarsa.getEmail(), bentukUsahaData, pemrakarsa.getNama(), pemrakarsa.getNamaNotaris(), 
				penanggungJawabPemrakarsaData, pemrakarsa.getNomorIndukBerusaha(), pemrakarsa.getNpwp(), pemrakarsa.getTanggalNotaris(),
				pemrakarsa.getTanggalOSS());
		
		PermohonanData permohonanData = new PermohonanData();
		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
		permohonanData.setBidangUsaha(bidangUsahaData);
		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
		
		permohonanData.setPemrakarsa(pemrakarsaData);
		
		Produk produk = permohonan.getProduk();
		ProdukData produkData = new ProdukData();
		
		
		return permohonanData;
	}
	
	public static Permohonan toClassObject(PermohonanData permohonanData) {
		return null;
	}
}
