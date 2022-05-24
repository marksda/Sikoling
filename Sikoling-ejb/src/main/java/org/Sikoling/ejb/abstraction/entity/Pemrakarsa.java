package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;

public class Pemrakarsa implements Serializable {

	private static final long serialVersionUID = 1008634190691153214L;
	private BentukUsaha bentukUsaha;
	private String nama;
	private Alamat alamat;
	private String telepone;
	private String fax;
	private String npwp;
	private String email;
	private PenanggungJawab penanggungJawab;
	
}
