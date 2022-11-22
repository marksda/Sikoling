package org.Sikoling.main.restful.entityprovider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.Sikoling.main.restful.dokumen.DokumenDTO;
import org.Sikoling.main.restful.dokumen.DokumenOssDTO;
import org.Sikoling.main.restful.dokumen.KategoriDokumenDTO;
import org.Sikoling.main.restful.dokumen.KbliDTO;
import org.Sikoling.main.restful.dokumen.RegisterDokumenDTO;
import org.Sikoling.main.restful.perusahaan.PerusahaanDTO;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;


@Provider
@Consumes("application/json")
@Produces("application/json")
public class RegisterDokumenProvider implements MessageBodyReader<RegisterDokumenDTO>, MessageBodyWriter<RegisterDokumenDTO> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return RegisterDokumenDTO.class == type;
	}

	@Override
	public RegisterDokumenDTO readFrom(Class<RegisterDokumenDTO> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		Jsonb jsonb = JsonbBuilder.create();
		JsonParser jsonParser = Json.createParser(entityStream);
		
		JsonObject rootDTOJsonObject = jsonParser.getObject();		
		JsonObject perusahaanDTOJsonObject = rootDTOJsonObject.getJsonObject("perusahaan");
		JsonObject detailDokumenDTOJsonObject = rootDTOJsonObject.getJsonObject("detailDokumen");
		JsonObject dokumenDTOJsonObject = detailDokumenDTOJsonObject.getJsonObject("dokumen");
		
		DokumenDTO dokumenDTO = new DokumenDTO();
		dokumenDTO.setId(dokumenDTOJsonObject.getString("id"));
		dokumenDTO.setNama(dokumenDTOJsonObject.getString("nama"));
		JsonObject kategoriDokumenDTOJsonObject = detailDokumenDTOJsonObject.getJsonObject("kategori");
		KategoriDokumenDTO kategoriDokumenDTO = new KategoriDokumenDTO();
		kategoriDokumenDTO.setId(kategoriDokumenDTOJsonObject.getString("id"));
		kategoriDokumenDTO.setNama(kategoriDokumenDTOJsonObject.getString("nama"));
		dokumenDTO.setKategori(kategoriDokumenDTO);
		
		RegisterDokumenDTO registerDokumenDTO = new RegisterDokumenDTO();
		PerusahaanDTO perusahaanDTO = jsonb.fromJson(perusahaanDTOJsonObject.toString(), PerusahaanDTO.class);
		registerDokumenDTO.setPerusahaan(perusahaanDTO);
		
		String dateStr;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate tanggal;
		
		switch (dokumenDTO.getId()) {
		case "010301":
			DokumenOssDTO dokumenOssDTO = new DokumenOssDTO();
			dokumenOssDTO.setDokumen(dokumenDTO);
			dokumenOssDTO.setNib(detailDokumenDTOJsonObject.getString("nib"));	
			
			dateStr = detailDokumenDTOJsonObject.getString("tanggal");	
			tanggal = LocalDate.parse(dateStr, formatter);
			dokumenOssDTO.setTanggal(tanggal);	//tanggal penerbitan oss
			
			
			JsonArray kbliJsonArray = detailDokumenDTOJsonObject.getJsonArray("daftarKbli");
			List<KbliDTO> daftarKbliDTO = new ArrayList<KbliDTO>();
			KbliDTO	itemKbli;
			JsonObject explrObject;		
			
			for(int i=0; i<kbliJsonArray.size(); i++) {				
				explrObject = kbliJsonArray.getJsonObject(i);
				itemKbli = new KbliDTO();
				itemKbli.setKode(explrObject.getString("kode"));
				itemKbli.setNama(explrObject.getString("nama"));
				daftarKbliDTO.add(itemKbli);
			}
			
			dokumenOssDTO.setDaftarKbli(daftarKbliDTO);		//daftar kbli	
			registerDokumenDTO.setDetailDokumen(dokumenOssDTO);
			break;
		default:
			break;
		}
				
		//tanggal registrasi dokumen			
		dateStr = rootDTOJsonObject.getString("tanggal");
		tanggal = LocalDate.parse(dateStr, formatter);
		registerDokumenDTO.setTanggal(tanggal);
						
		registerDokumenDTO.setIsBerlaku(rootDTOJsonObject.getBoolean("isBerlaku"));
		
		return registerDokumenDTO;
	}

	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return RegisterDokumenDTO.class == type;
	}

	
	@Override
	public void writeTo(RegisterDokumenDTO t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		Jsonb jsonb = JsonbBuilder.create();
		String hasil = jsonb.toJson(t);
		
		entityStream.write(hasil.getBytes());
	}

}
