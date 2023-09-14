package org.Sikoling.main.restful;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

import org.Sikoling.main.restful.dokumen.AktaPendirianDTO;
import org.Sikoling.main.restful.dokumen.DokumenGenerikDTO;
import org.Sikoling.main.restful.dokumen.DokumenNibOssDTO;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.dokumen.StatusDokumenDTO;
import org.Sikoling.main.restful.otoritas.OtoritasDTO;
import org.Sikoling.main.restful.perusahaan.RegisterPerusahaanDTO;

import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;

@Provider
@Consumes("application/json")
public class RegisterDokumenProvider implements MessageBodyReader<RegisterDokumenDTO> {	
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == RegisterDokumenDTO.class;
	}

	@Override
	public RegisterDokumenDTO readFrom(Class<RegisterDokumenDTO> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		RegisterDokumenDTO registerDokumenDTO;
		
		try {
			Jsonb jsonb = JsonbBuilder.create();
			JsonObject d = jsonb.fromJson(entityStream, JsonObject.class);
			JsonObject dokumen = d.getJsonObject("dokumen");
			String idDokumen = dokumen.getString("id");
			registerDokumenDTO = new RegisterDokumenDTO();
			
			switch (idDokumen) {
			case "010101":
					registerDokumenDTO.setDokumen(jsonb.fromJson(d.getJsonObject("dokumen").toString(), AktaPendirianDTO.class));
				break;
			case "010301":
				registerDokumenDTO.setDokumen(jsonb.fromJson(d.getJsonObject("dokumen").toString(), DokumenNibOssDTO.class));
				break;
			default:
				registerDokumenDTO.setDokumen(jsonb.fromJson(d.getJsonObject("dokumen").toString(), DokumenGenerikDTO.class));
				break;
			}
			
			registerDokumenDTO.setId(d.getString("id", null));
			registerDokumenDTO.setRegisterPerusahaan(jsonb.fromJson(d.getJsonObject("registerPerusahaan").toString(), RegisterPerusahaanDTO.class));
			registerDokumenDTO.setLokasiFile(d.getString("lokasiFile", null));
			
			try {
				registerDokumenDTO.setStatusDokumen(jsonb.fromJson(d.getJsonObject("statusDokumen").toString(), StatusDokumenDTO.class));
			} catch (Exception e) {
				registerDokumenDTO.setStatusDokumen(null);
			}
			
			
			String tanggalRegistrasi = d.getString("tanggalRegistrasi", null);
			if(tanggalRegistrasi != null) {
				registerDokumenDTO.setTanggalRegistrasi(LocalDate.parse(tanggalRegistrasi));
			}	
			
			try {
				registerDokumenDTO.setUploader(jsonb.fromJson(d.getJsonObject("uploader").toString(), OtoritasDTO.class));
			} catch (Exception e) {
				registerDokumenDTO.setUploader(null);
			}
			
			try {
				registerDokumenDTO.setStatusVerified(d.getBoolean("statusVerified"));
			} catch (Exception e) {
				registerDokumenDTO.setStatusVerified(Boolean.FALSE);
			}
			
			return registerDokumenDTO;
		} catch (Exception e) {
			return null;
		}
	}

}
