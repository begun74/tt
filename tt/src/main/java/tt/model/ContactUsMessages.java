package tt.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

public class ContactUsMessages implements  IModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4296852627015558916L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_contact_us_messages")
	private Long id;

	@Size(min = 1, message = "Please enter name.")
	private String name;
	
	@Size(min = 1, message = "Please enter email.")
	private String email;
	
	@Size(min = 1, message = "Please enter subject.")
	private String subject;
	
	@Size(min = 1, message = "Please enter message.")
	private String message;
	
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

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
