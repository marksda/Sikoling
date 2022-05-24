package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Date;

public class Permohonan implements Serializable {

	private static final long serialVersionUID = -2507712010024456804L;
	private String noPendaftaran;
	private Date tanggalPendaftaran;
	private Pemrakarsa pemrakarsa;
	private SuratPermohonan suratPermohonan;
	private BidangUsaha bidangUsaha;
	private String statusWaliPermohonan;

}
