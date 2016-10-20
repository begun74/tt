package tt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tt.user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 565L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id_user")
	private long id;
	
	@NotEmpty (message = "Please enter name.") 
	private String name;
	
	@NotEmpty (message = "Please enter password.") 
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}

	
}
