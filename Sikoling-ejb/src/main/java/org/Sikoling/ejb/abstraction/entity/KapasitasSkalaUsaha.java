package org.Sikoling.ejb.abstraction.entity;

import java.io.Serializable;
import java.util.Objects;

public class KapasitasSkalaUsaha implements Serializable {

	private static final long serialVersionUID = 1088341685122527994L;
	private final String Kapasitas;
	private final Double luasLahan;
	private final Double luasBangunan;
	
	public KapasitasSkalaUsaha(String kapasitas, Double luasLahan, Double luasBangunan) {
		super();
		Kapasitas = kapasitas;
		this.luasLahan = luasLahan;
		this.luasBangunan = luasBangunan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getKapasitas() {
		return Kapasitas;
	}

	public Double getLuasLahan() {
		return luasLahan;
	}

	public Double getLuasBangunan() {
		return luasBangunan;
	}
	
	@Override
	public int hashCode() {
		int hash = 117;
		hash = 131 * hash + Objects.hashCode(luasLahan.toString());
		hash = 131 * hash + Objects.hashCode(luasBangunan.toString());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final KapasitasSkalaUsaha other = (KapasitasSkalaUsaha) obj;
        
        if (!luasBangunan.equals(other.getLuasBangunan())) {
            return false;
        }
        
        if (!luasLahan.equals(other.getLuasLahan())) {
            return false;
        }
        
        return true;
	}

	@Override
	public String toString() {
		return "KapasitasSkalaUsaha{luasLahan=" + luasLahan.toString() + "luasBangunan=" + luasBangunan.toString() + "}";
	}
	
}
