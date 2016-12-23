package tt.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Order implements IModel {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8125750113397192529L;
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_orders")
	private long id;
	
	private Timestamp creation_date; 
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}


	public Timestamp getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}


	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((Order)o).getId().compareTo(getId());
	}

}
