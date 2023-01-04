package org.Sikoling.ejb.main.repository.log;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="transaksi.tbl_flow_log")
@NamedQueries({
	@NamedQuery(name="FlowLogData.findAll", query="SELECT p FROM FlowLogData p")
})
public class FlowLogData implements Serializable {

	private static final long serialVersionUID = 6708500898590911457L;
	
	@Id
	private String id;
	
	private LocalDate tanggal;	
	
	@JoinColumn(name="kategori_log", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne
	private KategoriLogData kategoriLogData;
	
	
}
