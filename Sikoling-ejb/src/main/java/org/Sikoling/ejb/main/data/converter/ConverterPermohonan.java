package org.Sikoling.ejb.main.data.converter;

import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.main.repository.permohonan.PermohonanData;

public class ConverterPermohonan {
	
	public static PermohonanData toDataTable(Permohonan permohonan) {		
//		BidangUsaha bidangUsaha = permohonan.getBidangUsaha();
//		Pemrakarsa pemrakarsa = permohonan.getSuratPermohonan().getPemrakarsa();
//		BentukUsaha bentukUsaha = pemrakarsa.getBentukUsaha();
//		KelompokBentukUsaha kelompokBentukUsaha = bentukUsaha.getKelompokUsaha();
//		PenanggungJawab penanggungJawabPemrakarsa = pemrakarsa.getPenanggungJawab();
//		Alamat alamat =  penanggungJawabPemrakarsa.getAlamat();
//		Desa desa = alamat.getDesa();
//		Kecamatan kecamatan = alamat.getKecamatan();		
//		Kabupaten kabupaten = alamat.getKabupaten();
//		Propinsi propinsi = alamat.getPropinsi();
//		Jabatan jabatan = penanggungJawabPemrakarsa.getJabatan();
//		JenisKelamin jenisKelamin = penanggungJawabPemrakarsa.getJenisKelamin();
//		Produk produk = permohonan.getProduk();
//		KategoriPaket kategoriPaket = produk.getKategoriPaket();
//		KategoriProduk kategoriProduk = produk.getKategoriProduk();
//		WaliPemohon waliPemohon = permohonan.getWali();
//		StatusWali statusWali = waliPemohon.getStatus();
//		User user = waliPemohon.getWali();
//		
//		BidangUsahaData bidangUsahaData = new BidangUsahaData(bidangUsaha.getId(), bidangUsaha.getKeterangan());
//		KelompokBentukUsahaData kelompokBentukUsahaData = new KelompokBentukUsahaData(kelompokBentukUsaha.getId(), kelompokBentukUsaha.getNama());
//		BentukUsahaData bentukUsahaData = new BentukUsahaData(bentukUsaha.getId(), kelompokBentukUsahaData, bentukUsaha.getNama());
//		PropinsiData propinsiData = new PropinsiData(propinsi.getId(), propinsi.getNama());
//		KabupatenData kabupatenData = new KabupatenData(kabupaten.getId(), kabupaten.getNama(), propinsiData);
//		KecamatanData kecamatanData = new KecamatanData(kecamatan.getId(), kabupatenData, kecamatan.getNama());
//		DesaData desaData = new DesaData(desa.getId(), kecamatanData, desa.getNama());
//		JabatanData jabatanData = new JabatanData(jabatan.getId(), jabatan.getNama());
//		JenisKelaminData jenisKelaminData = new JenisKelaminData(jenisKelamin.getId(), jenisKelamin.getNama());		
//		
//		PenanggungJawabData penanggungJawabPemrakarsaData = new PenanggungJawabData(
//				penanggungJawabPemrakarsa.getId(), desaData, alamat.getKeterangan(), jabatanData, kabupatenData, kecamatanData,
//				penanggungJawabPemrakarsa.getNama(), penanggungJawabPemrakarsa.getNoHandphone(), penanggungJawabPemrakarsa.getNoIdentitas(),
//				propinsiData, jenisKelaminData);
//		KategoriPenanggungJawabData kategoriPenanggungJawabPemrakarsaData = new KategoriPenanggungJawabData();
//		RelasiPenanggungJawabData relasiPenanggungJawaPemrakarsaData = new RelasiPenanggungJawabData();
//		
//		PemrakarsaData pemrakarsaData = new PemrakarsaData(
//				pemrakarsa.getId(), pemrakarsa.getEmail(), bentukUsahaData, pemrakarsa.getNama(), pemrakarsa.getNamaNotaris(), 
//				relasiPenanggungJawaPemrakarsaData, pemrakarsa.getNomorIndukBerusaha(), pemrakarsa.getNpwp(), pemrakarsa.getTanggalNotaris(),
//				pemrakarsa.getTanggalOSS());
//		KategoriPaketData kategoriPaketData = new KategoriPaketData(kategoriPaket.getId(), kategoriPaket.getNama());
//		KategoriProdukData kategoriProdukData = new KategoriProdukData(kategoriProduk.getId(), kategoriProduk.getNama());
//		ProdukData produkData = new ProdukData(produk.getId(), kategoriPaketData, kategoriProdukData, produk.getNama());
//		StatusWaliData statusWaliData = new StatusWaliData(statusWali.getId(), statusWali.getNama());
//		UserData userData = new UserData(user.getId(), user.getPassword(), user.getStatusInternal(), 
//				user.getLoginStatus(), user.getRegisterDate(), user.getEmail());
//		RelasiPenanggungJawabData relasiPenanggungJawabData = new RelasiPenanggungJawabData();
		
		PermohonanData permohonanData = new PermohonanData();
//		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
//		permohonanData.setBidangUsaha(bidangUsahaData);
//		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());		
//		permohonanData.setPemrakarsa(pemrakarsaData);
//		permohonanData.setProduk(produkData);
//		permohonanData.setStatusWali(statusWaliData);
//		permohonanData.setTanggal(new Timestamp(permohonan.getTanggalPendaftaran().getTime()));
//		permohonanData.setWali(userData);
//		permohonanData.setPenanggungJawab(relasiPenanggungJawabData);
		
		return permohonanData;
	}
	
	public static Permohonan toClassObject(PermohonanData permohonanData) {
		return null;
	}
}
