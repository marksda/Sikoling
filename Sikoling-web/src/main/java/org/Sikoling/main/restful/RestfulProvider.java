package org.Sikoling.main.restful;

import org.Sikoling.ejb.abstraction.repository.IPermohonanRepository;
import org.Sikoling.ejb.abstraction.repository.IProdukRepository;
import org.Sikoling.ejb.abstraction.repository.IPropinsiRepository;
import org.Sikoling.ejb.abstraction.service.permohonan.ServicePermohonan;
import org.Sikoling.ejb.abstraction.service.produk.IServiceProduk;
import org.Sikoling.ejb.abstraction.service.produk.ServiceProduk;
import org.Sikoling.ejb.abstraction.service.propinsi.IServicePropinsi;
import org.Sikoling.ejb.abstraction.service.propinsi.ServicePropinsi;
import org.Sikoling.ejb.abstraction.service.permohonan.IServicePermohonan;
import org.Sikoling.ejb.main.Infrastructure;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Produces;

@Stateless
@LocalBean
public class RestfulProvider {
	
	@Produces
	public IServicePropinsi getServicePropinsi(
			@Infrastructure IPropinsiRepository propinsiRepository) {
		return new ServicePropinsi(propinsiRepository);
	}
	
	@Produces
	public IServiceProduk getLayananProduk(
			@Infrastructure IProdukRepository produkRepository) {
		return new ServiceProduk(produkRepository);
	}
	
	@Produces
	public IServicePermohonan getLayananPermohonan(
			@Infrastructure IPermohonanRepository permohonanRepository) {
		return new ServicePermohonan(permohonanRepository);
	}
	
}
