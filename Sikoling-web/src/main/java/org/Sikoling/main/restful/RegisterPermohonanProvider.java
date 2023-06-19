package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.Sikoling.main.restful.autority.AutorityDTO;
import org.Sikoling.main.restful.dokumen.DokumenDTO;
import org.Sikoling.main.restful.dokumen.NibOssDTO;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.log.StatusFlowLogDTO;
import org.Sikoling.main.restful.pegawai.PegawaiPerusahaanDTO;
import org.Sikoling.main.restful.permohonan.KategoriPermohonanSuratArahanDTO;
import org.Sikoling.main.restful.permohonan.KategoriPermohonanDTO;
import org.Sikoling.main.restful.permohonan.PosisiTahapPemberkasanDTO;
import org.Sikoling.main.restful.permohonan.RegisterPermohonanArahanDTO;
import org.Sikoling.main.restful.permohonan.RegisterPermohonanDTO;
import org.Sikoling.main.restful.permohonan.StatusPengurusPermohonanDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonParser;
import jakarta.json.stream.JsonParser.Event;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;

@Provider
@Consumes("application/json")
public class RegisterPermohonanProvider implements MessageBodyReader<RegisterPermohonanDTO> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == RegisterPermohonanDTO.class;
	}

	@Override
	public RegisterPermohonanDTO readFrom(Class<RegisterPermohonanDTO> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		Jsonb jsonb = JsonbBuilder.create();		
		JsonParser parser = Json.createParser(entityStream);
		
		RegisterPermohonanDTO registerPermohonanDTO = null;
		Event event;
		JsonObject jsonObject;
		JsonArray jsonArray;
		
		String id = null;
		KategoriPermohonanDTO kategoriPermohonanDTO = null;
		PegawaiPerusahaanDTO pegawaiPerusahaanDTO = null;
		RegisterPerusahaanDTO registerPerusahaanDTO = null;
		AutorityDTO pengurusPermohonanDTO = null;
		PosisiTahapPemberkasanDTO pengirimBerkasDTO = null;
		PosisiTahapPemberkasanDTO penerimaBerkasDTO = null;
		StatusFlowLogDTO statusFlowLogDTO =null;
		StatusPengurusPermohonanDTO statusWaliDTO = null;
		LocalDate tanggalRegistrasi = null;
		List<RegisterDokumenDTO> daftarDokumenSyarat = null;
		List<RegisterDokumenDTO> daftarDokumenHasil = null;

		KategoriPermohonanSuratArahanDTO jenisPermohonanSuratArahanDTO = null;
		String uraianKegiatan = null;
		
		while (parser.hasNext()) {
			event = parser.next();
			if (event == JsonParser.Event.KEY_NAME ) {
				String key = parser.getString();
				
				switch (key) {
				case "id":
					event = parser.next();
					try {
						id = parser.getString();
					} catch (Exception e) {
						id = null;
					}					
					break;
				case "jenisPermohonanSuratArahan":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						jenisPermohonanSuratArahanDTO = jsonb.fromJson(jsonObject.toString(), KategoriPermohonanSuratArahanDTO.class);
					} catch (Exception e) {
						jenisPermohonanSuratArahanDTO = null;
					}					
					break;
				case "kategoriPermohonan":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						kategoriPermohonanDTO = jsonb.fromJson(jsonObject.toString(), KategoriPermohonanDTO.class);
					} catch (Exception e) {
						kategoriPermohonanDTO = null;
					}		
					break;
				case "penanggungJawabPermohonan":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						pegawaiPerusahaanDTO = jsonb.fromJson(jsonObject.toString(), PegawaiPerusahaanDTO.class);
					} catch (Exception e) {
						pegawaiPerusahaanDTO = null;
					}		
					break;
				case "registerPerusahaan":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						registerPerusahaanDTO = jsonb.fromJson(jsonObject.toString(), RegisterPerusahaanDTO.class);
					} catch (Exception e) {
						registerPerusahaanDTO = null;
					}		
					break;	
				case "pengurusPermohonan":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						pengurusPermohonanDTO = jsonb.fromJson(jsonObject.toString(), AutorityDTO.class);
					} catch (Exception e) {
						pengurusPermohonanDTO = null;
					}		
					break;	
				case "pengirimBerkas":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						pengirimBerkasDTO = jsonb.fromJson(jsonObject.toString(), PosisiTahapPemberkasanDTO.class);
					} catch (Exception e) {
						pengirimBerkasDTO = null;
					}		
					break;	
				case "penerimaBerkas":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						penerimaBerkasDTO = jsonb.fromJson(jsonObject.toString(), PosisiTahapPemberkasanDTO.class);
					} catch (Exception e) {
						penerimaBerkasDTO = null;
					}		
					break;	
				case "statusFlowLog":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						statusFlowLogDTO = jsonb.fromJson(jsonObject.toString(), StatusFlowLogDTO.class);
					} catch (Exception e) {
						statusFlowLogDTO = null;
					}		
					break;	
				case "statusWali":
					event = parser.next();
					try {
						jsonObject = parser.getObject();
						statusWaliDTO = jsonb.fromJson(jsonObject.toString(), StatusPengurusPermohonanDTO.class);
					} catch (Exception e) {
						statusWaliDTO = null;
					}		
					break;	
				case "tanggalRegistrasi":
					event = parser.next();
					try {
						tanggalRegistrasi = LocalDate.parse(parser.getString());
					} catch (Exception e) {
						tanggalRegistrasi = null;
					}		
					break;	
				case "uraianKegiatan":
					event = parser.next();
					try {
						uraianKegiatan = parser.getString();
					} catch (Exception e) {
						uraianKegiatan = null;
					}					
					break;	
				case "daftarDokumenSyarat":
					event = parser.next();
					try {
						jsonArray = parser.getArray();
						daftarDokumenSyarat = jsonArray.stream()
								.map(item -> jsonObjectToRegisterDokumen((JsonObject) item, jsonb))
								.collect(Collectors.toList());
					} catch (Exception e) {
						daftarDokumenSyarat = null;
					}					
					break;	
				case "daftarDokumenHasil":
					event = parser.next();
					try {
						jsonArray = parser.getArray();
						daftarDokumenHasil = jsonArray.stream()
								.map(item -> jsonObjectToRegisterDokumen((JsonObject) item, jsonb))
								.collect(Collectors.toList());
					} catch (Exception e) {
						daftarDokumenHasil = null;
					}					
					break;	
				default:
					break;
				}
			}
		}
		
		String idKategoriPermohonan = kategoriPermohonanDTO != null ? kategoriPermohonanDTO.getId() : "00";
		switch (idKategoriPermohonan) {
		case "01":
			RegisterPermohonanArahanDTO dt = new RegisterPermohonanArahanDTO();
			dt.setId(id);
			dt.setKategoriPermohonan(kategoriPermohonanDTO);
			dt.setTanggalRegistrasi(tanggalRegistrasi);
			dt.setRegisterPerusahaan(registerPerusahaanDTO);
			dt.setPengurusPermohonan(pengurusPermohonanDTO);
			dt.setStatusPengurusPermohonan(statusWaliDTO);
			dt.setPenanggungJawabPermohonan(pegawaiPerusahaanDTO);
			dt.setPengirimBerkas(pengirimBerkasDTO);
			dt.setPenerimaBerkas(penerimaBerkasDTO);
			dt.setStatusFlowLog(statusFlowLogDTO);
			dt.setDaftarDokumenSyarat(daftarDokumenSyarat);
			dt.setDaftarDokumenHasil(daftarDokumenHasil);
			dt.setKategoriPermohonanSuratArahan(jenisPermohonanSuratArahanDTO);
			dt.setUraianKegiatan(uraianKegiatan);
			registerPermohonanDTO = dt;
			break;
		default:
			break;
		}
		
		return registerPermohonanDTO;
		
	}	
	
	private RegisterDokumenDTO jsonObjectToRegisterDokumen(JsonObject jObject, Jsonb jsonb) {
		RegisterDokumenDTO registerDokumenDTO = null;
		
		JsonObject jObjDokumen = jObject.getJsonObject("dokumen");
		String idDokumen = jObjDokumen.getString("id");
		
		switch (idDokumen) {
		 case "010301":
			registerDokumenDTO = jsonb.fromJson(jObject.toString(), RegisterDokumenDTO.class);
			DokumenDTO d = jsonb.fromJson(jObjDokumen.toString(), NibOssDTO.class);
			registerDokumenDTO.setDokumen(d); 
			break;
		 default:
			d = null;
			break;
		}
		
		return registerDokumenDTO;
	}

}
