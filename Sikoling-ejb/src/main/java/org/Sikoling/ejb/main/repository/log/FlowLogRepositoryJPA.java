package org.Sikoling.ejb.main.repository.log;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.ejb.abstraction.entity.Authority;
import org.Sikoling.ejb.abstraction.entity.DeleteResponse;
import org.Sikoling.ejb.abstraction.entity.PelakuUsaha;
import org.Sikoling.ejb.abstraction.entity.Perusahaan;
import org.Sikoling.ejb.abstraction.entity.RegisterPerusahaan;
import org.Sikoling.ejb.abstraction.entity.log.FlowLog;
import org.Sikoling.ejb.abstraction.entity.log.FlowLogPermohonan;
import org.Sikoling.ejb.abstraction.entity.log.KategoriFlowLog;
import org.Sikoling.ejb.abstraction.entity.permohonan.KategoriPermohonan;
import org.Sikoling.ejb.abstraction.entity.permohonan.PosisiTahapPemberkasan;
import org.Sikoling.ejb.abstraction.entity.permohonan.RegisterPermohonan;
import org.Sikoling.ejb.abstraction.repository.IFlowLogRepository;
import org.Sikoling.ejb.main.repository.authority.AutorisasiData;
import org.Sikoling.ejb.main.repository.pelakuusaha.PelakuUsahaData;
import org.Sikoling.ejb.main.repository.permohonan.KategoriPermohonanData;
import org.Sikoling.ejb.main.repository.permohonan.PosisiTahapPemberkasanData;
import org.Sikoling.ejb.main.repository.permohonan.RegisterPermohonanData;
import org.Sikoling.ejb.main.repository.perusahaan.RegisterPerusahaanData;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class FlowLogRepositoryJPA implements IFlowLogRepository {
	
	private final EntityManager entityManager;	

	public FlowLogRepositoryJPA(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<FlowLog> getAll() {
		return entityManager.createNamedQuery("FlowLogData.findAll", FlowLogData.class)
				.getResultList()
				.stream()
				.map(d -> convertFlowLogDataToFlowLog(d))
				.collect(Collectors.toList());
	}

	@Override
	public FlowLog save(FlowLog t) {
		FlowLogData flowLogData = convertFlowLogToFlowLogData(t);
		entityManager.persist(flowLogData);
		entityManager.flush();
		return convertFlowLogDataToFlowLog(
				entityManager.find(FlowLogData.class, flowLogData.getId())
				);
	}

	@Override
	public FlowLog update(FlowLog t) {
		FlowLogData flowLogData = convertFlowLogToFlowLogData(t);
		flowLogData = entityManager.merge(flowLogData);
		return convertFlowLogDataToFlowLog(
				entityManager.find(FlowLogData.class, flowLogData.getId())
				);
	}

	@Override
	public DeleteResponse delete(String id) {
		FlowLogData flowLogData = entityManager.find(FlowLogData.class, id);
		entityManager.remove(flowLogData);			
		return new DeleteResponse(true, id);
	}

	@Override
	public List<FlowLog> getAllByPage(Integer page, Integer pageSize) {
		return entityManager.createNamedQuery("FlowLogData.findAll", FlowLogData.class)
				.setMaxResults(pageSize)
				.setFirstResult((page-1)*pageSize)
				.getResultList()
				.stream()
				.map(t -> convertFlowLogDataToFlowLog(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<FlowLog> getByIdPengakses(String idPengakses) {
		return entityManager.createNamedQuery("FlowLogData.findByIdPengakses", FlowLogData.class)
				.setParameter("idPengakses", idPengakses)
				.getResultList()
				.stream()
				.map(t -> convertFlowLogDataToFlowLog(t))
				.collect(Collectors.toList());
	}

	@Override
	public List<FlowLog> getByIdKategori(String idKategoriLog) {
		return entityManager.createNamedQuery("FlowLogData.findByIdKategoriLog", FlowLogData.class)
				.setParameter("idKategoriLog", idKategoriLog)
				.getResultList()
				.stream()
				.map(t -> convertFlowLogDataToFlowLog(t))
				.collect(Collectors.toList());
	}
	
	private FlowLog convertFlowLogDataToFlowLog(FlowLogData d) {
		FlowLog flowLog = null;
		KategoriLogData kategoriLogData = d.getKategoriLogData();
		PosisiTahapPemberkasanData posisiTahapPemberkasanData = d.getPosisiTahapPemberkasanData();
		AutorisasiData pengaksesData = d.getPengaksesData();
		
		if(kategoriLogData.getId().equals("1")) {
			KategoriFlowLog kategoriFlowLog = kategoriLogData != null ? new KategoriFlowLog(
					kategoriLogData.getId(), 
					kategoriLogData.getNama()
					) : null;
			PosisiTahapPemberkasan posisiTahapPemberkasan = posisiTahapPemberkasanData != null ?
					new PosisiTahapPemberkasan(
						posisiTahapPemberkasanData.getId(), 
						posisiTahapPemberkasanData.getNama(), 
						null
						) : null;
			Authority pengakses = pengaksesData != null ? 
					new Authority(
						pengaksesData.getId(), 
						null, 
						null, 
						null, 
						null, 
						pengaksesData.getUserName()
						) : null;	
			
			RegisterPermohonanData registerPermohonanData = d.getFlowLogPermohonanData()
					.getRegisterPermohonan();
			KategoriPermohonanData kategoriPermohonanData = registerPermohonanData != null ?
					registerPermohonanData.getKategoriPermohonanData() : null;
			KategoriPermohonan kategoriPermohonan = kategoriPermohonanData != null ?
					new KategoriPermohonan(
						kategoriPermohonanData.getId(), 
						kategoriPermohonanData.getNama()
						) : null;
			RegisterPerusahaanData registerPerusahaanData = registerPermohonanData != null ?
					registerPermohonanData.getPerusahaanData() : null;
			PelakuUsahaData pelakuUsahaData = registerPerusahaanData != null ? 
					registerPerusahaanData.getPelakuUsahaData() : null;
			PelakuUsaha pelakuUsaha = pelakuUsahaData != null ?
					new PelakuUsaha(
							pelakuUsahaData.getId(), 
							pelakuUsahaData.getNama(), 
							pelakuUsahaData.getSingkatan(), 
							null) : null;
			
			Perusahaan perusahaan = registerPerusahaanData != null ?
					new Perusahaan(
							registerPerusahaanData.getNpwp(), 
							registerPerusahaanData.getNama(), 
							null, 
							null, 
							pelakuUsaha, 
							null, 
							null, 
							null, 
							null
							) : null;
			
			RegisterPerusahaan registerPerusahaan = registerPerusahaanData != null ?
					new RegisterPerusahaan(
						registerPerusahaanData.getId(), 
						registerPerusahaanData.getTanggalRegistrasi(), 
						null, 
						null, 
						perusahaan
						) : null;
			
			RegisterPermohonan registerPermohonan = registerPermohonanData!= null ?
					new RegisterPermohonan(
						registerPermohonanData.getId(), 
						kategoriPermohonan, 
						registerPermohonanData.getTanggalRegistrasi(), 
						registerPerusahaan, 
						pengakses, 
						null, 
						posisiTahapPemberkasan, 
						null, 
						null) : null;
			
			flowLog = new FlowLogPermohonan(
					d.getId(), 
					d.getTanggal(), 
					kategoriFlowLog, 
					posisiTahapPemberkasan, 
					d.getKeterangan(), 
					pengakses, 
					registerPermohonan
					);
		}
		
		return flowLog;
	}

	private FlowLogData convertFlowLogToFlowLogData(FlowLog t) {
		FlowLogData flowLogData = new FlowLogData();
		
		if(t.getKategoriFlowLog().getId().equals("1")) {
			FlowLogPermohonan flowLogPermohonan = (FlowLogPermohonan) t;
			flowLogData.setId(flowLogPermohonan.getId() != null ? 
					t.getId():getGenerateIdFlowLogData());
			flowLogData.setTanggal(flowLogPermohonan.getTanggal());
			
			KategoriLogData kategoriLogData = new KategoriLogData();
			kategoriLogData.setId(flowLogPermohonan.getKategoriFlowLog().getId());
			flowLogData.setKategoriLogData(kategoriLogData);
			
			PosisiTahapPemberkasanData posisiTahapPemberkasanData = new PosisiTahapPemberkasanData();
			posisiTahapPemberkasanData.setId(flowLogPermohonan.getPosisiTahapPemberkasan().getId());
			flowLogData.setPosisiTahapPemberkasanData(posisiTahapPemberkasanData);
			
			flowLogData.setKeterangan(flowLogPermohonan.getKeterangan());
			
			AutorisasiData pengaksesData = new AutorisasiData();
			pengaksesData.setId(flowLogPermohonan.getPengakses().getId());
			flowLogData.setPengaksesData(pengaksesData);
			
			FlowLogPermohonanData flowLogPermohonanData = new FlowLogPermohonanData();
			RegisterPermohonanData registerPermohonanData = new RegisterPermohonanData();
			registerPermohonanData.setId(flowLogPermohonan.getRegisterPermohonan().getId());
			flowLogPermohonanData.setRegisterPermohonan(registerPermohonanData);
			flowLogData.setFlowLogPermohonanData(flowLogPermohonanData);
			
		}
		
		return flowLogData;
	}
	
	private String getGenerateIdFlowLogData() {
		int tahun = LocalDate.now().getYear();
		String hasil;
		
		Query q = entityManager.createQuery("SELECT MAX(fl.id) "
				+ "FROM FlowLogData fl "
				+ "WHERE EXTRACT(YEAR FROM fl.tanggal) = :tahun");
		
		q.setParameter("tahun", tahun);
		
		try {
			hasil = (String) q.getSingleResult();
			hasil = hasil.substring(0, 7);
			Long idBaru = Long.valueOf(hasil)  + 1;
			hasil = LPad(Long.toString(idBaru), 7, '0');
			return hasil.concat(Integer.toString(tahun));
		} catch (Exception e) {			
			hasil = "0000001";			
			return hasil.concat(Integer.toString(tahun));
		}		
	}
	
	private String LPad(String str, Integer length, char car) {
		  return (String.format("%" + length + "s", "").replace(" ", String.valueOf(car)) + str).substring(str.length(), length + str.length());
	}
	
}
