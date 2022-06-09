package org.Sikoling.main.restful;

import org.Sikoling.ejb.abstraction.repository.IDesaRepository;
import org.Sikoling.ejb.abstraction.repository.IKabupatenRepository;
import org.Sikoling.ejb.abstraction.repository.IKecamatanRepository;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;
import org.Sikoling.ejb.abstraction.service.propinsi.IPropinsiService;
import org.Sikoling.ejb.abstraction.service.propinsi.PropinsiService;
import org.Sikoling.ejb.abstraction.service.desa.IDesaService;
import org.Sikoling.ejb.abstraction.service.desa.DesaService;
import org.Sikoling.ejb.abstraction.service.kabupaten.IKabupatenService;
import org.Sikoling.ejb.abstraction.service.kabupaten.KabupatenService;
import org.Sikoling.ejb.abstraction.service.kecamatan.IKecamatanService;
import org.Sikoling.ejb.abstraction.service.kecamatan.KecamatanService;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;

@Stateless
@LocalBean
public class RestfulProvider {
	
	@Produces
	public IPropinsiService getPropinsiService(
			@Infrastructure IPropinsiRepository propinsiRepository) {
		return new PropinsiService(propinsiRepository);
	}
	
	@Produces
	public IKabupatenService getKabupatenService(
			@Infrastructure IKabupatenRepository kabupatenRepository) {
		return new KabupatenService(kabupatenRepository);
	}
	
	@Produces
	public IKecamatanService getKecamatanService(
			@Infrastructure IKecamatanRepository kecamatanRepository) {
		return new KecamatanService(kecamatanRepository);
	}
	
	@Produces
	public IDesaService getDesaService(
			@Infrastructure IDesaRepository desaRepository) {
		return new DesaService(desaRepository);
	}
//	
//	@Produces
//	public IServiceProduk getLayananProduk(
//			@Infrastructure IProdukRepository produkRepository) {
//		return new ServiceProduk(produkRepository);
//	}
//	
//	@Produces
//	public IServicePermohonan getLayananPermohonan(
//			@Infrastructure IPermohonanRepository permohonanRepository) {
//		return new ServicePermohonan(permohonanRepository);
//	}
	
}