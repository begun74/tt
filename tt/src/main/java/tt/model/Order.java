package tt.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "orders")
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
	@NotNull
	private Long id;
	
	@NotNull
	private Timestamp creation_date; 
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="order")
	@NotNull
	private List<OrderItems> orderItems ;
	
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

	
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((Order)o).getId().compareTo(getId());
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", creation_date=" + creation_date + ", orderItems=" + orderItems + "]";
	}
	
	

}
