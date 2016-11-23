package tt.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tails")
public class Tail implements  IModel {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3431951539246492099L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_tails")
	private BigInteger id;
	
	@Column(name="amounttail")
	private int amountTail; //Кол-во
	
	@Column(name="firstPrice")
	private double firstPrice;  //Первая цена
	
	private Timestamp create_date;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public int getAmountTail() {
		return amountTail;
	}
	public void setAmountTail(int amountTail) {
		this.amountTail = amountTail;
	}
	public double getFirstPrice() {
		return firstPrice;
	}
	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}
	
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	@Override
	public String toString() {
		return "Tail [id=" + id + ", amountTail=" + amountTail + ", firstPrice=" + firstPrice + "]";
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
