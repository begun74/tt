package tt.modelattribute;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="session")
public class MA_loadProvider implements IMAmodel {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3311752620276929488L;
	
	
	private int row=1;
	private int col_name=0;
	private int col_code=1;
	
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
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

	

	@Override
	public String toString() {
		return "MA_loadProvider [row=" + row + ", col_name=" + col_name + ", col_code=" + col_code + "]";
	}


	
	
}
