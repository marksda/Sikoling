package org.Sikoling.main.restful.dokumen;

import java.io.Serializable;
import java.util.List;

public class DetailDokumenDTO implements Serializable {

	private static final long serialVersionUID = 1491203657821713604L;
	private DokumenDTO dokumen;
	private List<ItemAttributeDetailDokumenDTO> attributes;

}
