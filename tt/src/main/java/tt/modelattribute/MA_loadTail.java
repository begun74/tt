package tt.modelattribute;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="session")
public class MA_loadTail implements IMAmodel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950229997785369170L;
	
	private int row=1;
	private int col_amountTail = 1; //Кол-во
	private int col_firstPrice = 2;  //Первая цена
	private int col_codeProvider = 3; //Код поставщика
	private int col_codeNomencl = 4; //Код номенклатуры
	private int col_size=6;

	private transient boolean save;
	private boolean autoload;


	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public void setRow(int row) {
		// TODO Auto-generated method stub
		this.row = row;
	}
	
	

	

	public int getCol_amountTail() {
		return col_amountTail;
	}

	public void setCol_amountTail(int col_amountTail) {
		this.col_amountTail = col_amountTail;
	}

	public int getCol_firstPrice() {
		return col_firstPrice;
	}

	public void setCol_firstPrice(int col_firstPrice) {
		this.col_firstPrice = col_firstPrice;
	}

	public boolean isSave() {
		return save;
	}
	
	public int getCol_size() {
		return col_size;
	}

	public void setCol_size(int col_size) {
		this.col_size = col_size;
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
	

	public int getCol_codeProvider() {
		return col_codeProvider;
	}

	public void setCol_codeProvider(int col_codeProvider) {
		this.col_codeProvider = col_codeProvider;
	}

	public int getCol_codeNomencl() {
		return col_codeNomencl;
	}

	public void setCol_codeNomencl(int col_codeNomencl) {
		this.col_codeNomencl = col_codeNomencl;
	}


	@Override
	public long getSerialversionuid() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MA_loadTail [row=" + row + ", col_amountTail=" + col_amountTail + ", col_firstPrice=" + col_firstPrice
				+ ", autoload=" + autoload + "]";
	}
	
	
	

}
