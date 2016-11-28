package tt.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "dir_nomencl_group_root")
public class DirNomenclGroupRoot implements IModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1479184845704836945L;


	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_dir_nomencl_group_root")
	private BigInteger id;

	
	@Column(name="code")
	@NotNull (message = "Please enter code.") 
	private Long code;

	@NotEmpty (message = "Please enter name.") 
	private String name;
	
	
	@Override
	public BigInteger getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(BigInteger id) {
		// TODO Auto-generated method stub
		this.id = id;
	}



	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return ((DirNomenclGroupRoot)o).getId().compareTo(this.getId());
	}
	
}
