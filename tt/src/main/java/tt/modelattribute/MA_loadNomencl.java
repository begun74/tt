package tt.modelattribute;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="session")
public class MA_loadNomencl  implements IMAmodel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -765852209444376374L;


	
	private int row=1;
	private int col_name=1;
	private int col_code=2;
	private int col_article=3;
	private int col_codeNomenclGroup=4;
	private int col_gender=5;
	private int col_size=6;
	
	private transient  boolean save;
	private boolean autoload;
	
	
	
	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public void setRow(int row) {
		this.row=row;
	}

	public int getCol_name() {
		return col_name;
	}

	public void setCol_name(int col_name) {
		this.col_name = col_name;
	}

	public int getCol_code() {
		return col_code;
	}

	public void setCol_code(int col_code) {
		this.col_code = col_code;
	}

	public int getCol_article() {
		return col_article;
	}

	public void setCol_article(int col_article) {
		this.col_article = col_article;
	}
	
	
	public int getCol_codeNomenclGroup() {
		return col_codeNomenclGroup;
	}

	public void setCol_codeNomenclGroup(int col_codeNomenclGroup) {
		this.col_codeNomenclGroup = col_codeNomenclGroup;
	}

	
	public int getCol_gender() {
		return col_gender;
	}

	public void setCol_gender(int col_gender) {
		this.col_gender = col_gender;
	}
	

	public int getCol_size() {
		return col_size;
	}

	public void setCol_size(int col_size) {
		this.col_size = col_size;
	}


	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	public boolean isAutoload() {
		return autoload;
	}

	public void setAutoload(boolean autoload) {
		this.autoload = autoload;
	}

	public long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	@Override
	public String toString() {
		return "MA_loadNomencl [row=" + row + ", col_name=" + col_name + ", col_code=" + col_code + ", col_article="
				+ col_article + ", save=" + save + ", autoload=" + autoload + "]";
	}
	
	

}
