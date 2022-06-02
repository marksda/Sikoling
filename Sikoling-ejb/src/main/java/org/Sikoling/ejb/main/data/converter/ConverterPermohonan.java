package org.Sikoling.ejb.main.data.converter;

import org.Sikoling.ejb.abstraction.entity.Permohonan;
import org.Sikoling.ejb.main.data.PermohonanData;

public class ConverterPermohonan {
	
	public static PermohonanData toData(Permohonan permohonan) {
		PermohonanData permohonanData = new PermohonanData();
		
		return permohonanData;
	}
	
	public static Permohonan toClass(PermohonanData permohonanData) {
		return null;
	}
}
