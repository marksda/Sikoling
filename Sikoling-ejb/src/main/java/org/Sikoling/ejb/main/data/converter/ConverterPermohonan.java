package org.Sikoling.ejb.main.data.converter;

import org.Sikoling.ejb.abstraction.entity.BentukUsaha;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;
import org.Sikoling.ejb.abstraction.entity.KelompokBentukUsaha;
import org.Sikoling.ejb.abstraction.entity.Pemrakarsa;
import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.main.data.BentukUsahaData;
import org.Sikoling.ejb.main.data.BidangUsahaData;
import org.Sikoling.ejb.main.data.KelompokBentukUsahaData;
import org.Sikoling.ejb.main.data.PemrakarsaData;
import org.Sikoling.ejb.main.data.PermohonanData;

public class ConverterPermohonan {
	
	public static PermohonanData toDataTable(Permohonan permohonan) {
		PermohonanData permohonanData = new PermohonanData();
		
		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
		
		BidangUsaha bidangUsaha = permohonan.getBidangUsaha();
		BidangUsahaData bidangUsahaData = new BidangUsahaData(bidangUsaha.getId(), bidangUsaha.getKeterangan());
		permohonanData.setBidangUsaha(bidangUsahaData);
		
		Pemrakarsa pemrakarsa = permohonan.getSuratPermohonan().getPemrakarsa();
		BentukUsaha bentukUsaha = pemrakarsa.getBentukUsaha();
		KelompokBentukUsaha kelompokBentukUsaha = bentukUsaha.getKelompokUsaha();
		KelompokBentukUsahaData kelompokBentukUsahaData = new KelompokBentukUsahaData(kelompokBentukUsaha.getId(), kelompokBentukUsaha.getNama());
		BentukUsahaData bentukUsahaData = new BentukUsahaData(bentukUsaha.getId(), kelompokBentukUsahaData, bentukUsaha.getNama());
		PemrakarsaData pemrakarsaData = new PemrakarsaData(
				pemrakarsa.getId(), pemrakarsa.getEmail(), bentukUsahaData, pemrakarsa.getNama(), pemrakarsa.getNamaNotaris(), 
				pemrakarsa.getPenanggungJawab().getId(), pemrakarsa.getNomorIndukBerusaha(), pemrakarsa.getNpwp(), pemrakarsa.getTanggalNotaris(),
				pemrakarsa.getTanggalOSS());
		permohonanData.setPemrakarsa(pemrakarsaData);
		
		permohonanData.setNomorPendaftaran(permohonan.getNoPendaftaran());
		
		
		
		return permohonanData;
	}
	
	public static Permohonan toClassObject(PermohonanData permohonanData) {
		return null;
	}
}
