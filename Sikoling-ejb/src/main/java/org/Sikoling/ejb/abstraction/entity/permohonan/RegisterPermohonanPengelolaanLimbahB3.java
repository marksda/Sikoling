package org.Sikoling.ejb.abstraction.entity.permohonan;

import java.io.Serializable;

import org.Sikoling.ejb.abstraction.entity.Alamat;
import org.Sikoling.ejb.abstraction.entity.BidangUsaha;

public class RegisterPermohonanPengelolaanLimbahB3 implements Serializable {

	private static final long serialVersionUID = 6100435173961478177L;
	private final JenisPermohonanPengelolaanLimbahB3 jenisPermohonan;
	private final StatusPermohonanPengelolaanLimbahB3 status;
	private final BidangUsaha bidangUsaha;
	private final String namaUsaha;
	private final Alamat lokasiUsaha;
	
	public RegisterPermohonanPengelolaanLimbahB3(JenisPermohonanPengelolaanLimbahB3 jenisPermohonan,
			StatusPermohonanPengelolaanLimbahB3 status, BidangUsaha bidangUsaha, String namaUsaha, Alamat lokasiUsaha) {
		this.jenisPermohonan = jenisPermohonan;
		this.status = status;
		this.bidangUsaha = bidangUsaha;
		this.namaUsaha = namaUsaha;
		this.lokasiUsaha = lokasiUsaha;
	}
	
	

}
