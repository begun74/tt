package tt.model;


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "dir_nomenclature")
public class DirNomenclature implements  IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4460232450371446581L;

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_global")
	@SequenceGenerator(
			name="seq_global",
			sequenceName="seq_global",
			allocationSize=1
		)
	@Column(name="id_dir_nomenclature")
	private Long id;
	
	
	@Column(name="code")
	@NotNull (message = "Please enter code.") 
	private Long code;

	@NotEmpty (message = "Please enter name.") 
	private String name;

	@NotEmpty (message = "Please enter size.") 
	private String size;
	
	@NotEmpty (message = "Please enter name.") 
	private String article;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_dir_nomencl_group")
	@NotNull
	private DirNomenclGroup dirNomenclGroup;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_dir_gender")
	@NotNull
	private DirGender dirGender;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	
	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}


	public DirNomenclGroup getDirNomenclGroup() {
		return dirNomenclGroup;
	}

	public void setDirNomenclGroup(DirNomenclGroup dirNomenclGroup) {
		this.dirNomenclGroup = dirNomenclGroup;
	}
	

	public DirGender getDirGender() {
		return dirGender;
	}

	public void setDirGender(DirGender dirGender) {
		this.dirGender = dirGender;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	@Override
	public int compareTo(Object o) {
		return ( ((DirNomenclature)o).getCode().compareTo(this.getCode()) );
	}
	
	

}
