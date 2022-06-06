package org.Sikoling.ejb.main.data.converter;

import java.sql.Timestamp;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.Desa;
import org.Sikoling.ejb.abstraction.entity.Jabatan;
import org.Sikoling.ejb.abstraction.entity.JenisKelamin;
import org.Sikoling.ejb.abstraction.entity.Kabupaten;
import org.Sikoling.ejb.abstraction.entity.KategoriPaket;
import org.Sikoling.ejb.abstraction.entity.KategoriProduk;
import org.Sikoling.ejb.abstraction.entity.Kecamatan;
import org.Sikoling.ejb.abstraction.entity.KelompokBentukUsaha;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.PenanggungJawab;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.abstraction.entity.Produk;
import org.Sikoling.ejb.abstraction.entity.Propinsi;
import org.Sikoling.ejb.abstraction.entity.StatusWali;
import org.Sikoling.ejb.abstraction.entity.User;
import org.Sikoling.ejb.abstraction.entity.WaliPemohon;
import org.Sikoling.ejb.main.data.BentukUsahaData;
import org.Sikoling.ejb.main.data.BidangUsahaData;
import org.Sikoling.ejb.main.data.DesaData;
import org.Sikoling.ejb.main.data.JabatanData;
import org.Sikoling.ejb.main.data.JenisKelaminData;
import org.Sikoling.ejb.main.data.KabupatenData;
import org.Sikoling.ejb.main.data.KategoriPaketData;
import org.Sikoling.ejb.main.data.KategoriProdukData;
import org.Sikoling.ejb.main.data.KecamatanData;
import org.Sikoling.ejb.main.data.KelompokBentukUsahaData;
import org.Sikoling.ejb.main.data.PemrakarsaData;
import org.Sikoling.ejb.main.data.PenanggungJawabData;
import org.Sikoling.ejb.main.data.PermohonanData;
import org.Sikoling.ejb.main.data.ProdukData;
import org.Sikoling.ejb.main.data.PropinsiData;
import org.Sikoling.ejb.main.data.RelasiPenanggungJawabData;
import org.Sikoling.ejb.main.data.StatusWaliData;
import org.Sikoling.ejb.main.data.UserData;

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
		Produk produk = permohonan.getProduk();
		KategoriPaket kategoriPaket = produk.getKategoriPaket();
		KategoriProduk kategoriProduk = produk.getKategoriProduk();
		WaliPemohon waliPemohon = permohonan.getWali();
		StatusWali statusWali = waliPemohon.getStatus();
		User user = waliPemohon.getWali();
		
		BidangUsahaData bidangUsahaData = new BidangUsahaData(bidangUsaha.getId(), bidangUsaha.getKeterangan());
		KelompokBentukUsahaData kelompokBentukUsahaData = new KelompokBentukUsahaData(kelompokBentukUsaha.getId(), kelompokBentukUsaha.getNama());
		BentukUsahaData bentukUsahaData = new BentukUsahaData(bentukUsaha.getId(), kelompokBentukUsahaData, bentukUsaha.getNama());
		PropinsiData propinsiData = new PropinsiData(propinsi.getId(), propinsi.getNama());
		KabupatenData kabupatenData = new KabupatenData(kabupaten.getId(), kabupaten.getNama(), propinsiData);
		KecamatanData kecamatanData = new KecamatanData(kecamatan.getId(), kabupatenData, kecamatan.getNama());
		DesaData desaData = new DesaData(desa.getId(), kecamatanData, desa.getNama());
		JabatanData jabatanData = new JabatanData(jabatan.getId(), jabatan.getNama());
		JenisKelaminData jenisKelaminData = new JenisKelaminData(jenisKelamin.getId(), jenisKelamin.getNama());		
		PenanggungJawabData penanggungJawabPemrakarsaData = new PenanggungJawabData(
				penanggungJawab.getId(), desaData, alamat.getKeterangan(), jabatanData, kabupatenData, kecamatanData,
				penanggungJawab.getNama(), penanggungJawab.getNoHandphone(), penanggungJawab.getNoIdentitas(),
				propinsiData, jenisKelaminData);		
		PemrakarsaData pemrakarsaData = new PemrakarsaData(
				pemrakarsa.getId(), pemrakarsa.getEmail(), bentukUsahaData, pemrakarsa.getNama(), pemrakarsa.getNamaNotaris(), 
				penanggungJawabPemrakarsaData, pemrakarsa.getNomorIndukBerusaha(), pemrakarsa.getNpwp(), pemrakarsa.getTanggalNotaris(),
				pemrakarsa.getTanggalOSS());
		KategoriPaketData kategoriPaketData = new KategoriPaketData(kategoriPaket.getId(), kategoriPaket.getNama());
		KategoriProdukData kategoriProdukData = new KategoriProdukData(kategoriProduk.getId(), kategoriProduk.getNama());
		ProdukData produkData = new ProdukData(produk.getId(), kategoriPaketData, kategoriProdukData, produk.getNama());
		StatusWaliData statusWaliData = new StatusWaliData(statusWali.getId(), statusWali.getNama());
		UserData userData = new UserData(user.getId(), user.getPassword(), user.getStatusInternal(), 
				user.getLoginStatus(), user.getRegisterDate(), user.getEmail());
		RelasiPenanggungJawabData relasiPenanggungJawabData = new RelasiPenanggungJawabData();
		
		PermohonanData permohonanData = new PermohonanData();
		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
		permohonanData.setBidangUsaha(bidangUsahaData);
		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());		
		permohonanData.setPemrakarsa(pemrakarsaData);
		permohonanData.setProduk(produkData);
		permohonanData.setStatusWali(statusWaliData);
		permohonanData.setTanggal(new Timestamp(permohonan.getTanggalPendaftaran().getTime()));
		permohonanData.setWali(userData);
		permohonanData.setPenanggungJawab(relasiPenanggungJawabData);
		
		return permohonanData;
	}
	
	public static Permohonan toClassObject(PermohonanData permohonanData) {
		return null;
	}
}
