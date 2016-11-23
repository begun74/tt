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

	public void setSave(boolean save) {
		this.save = save;
	}

	public boolean isAutoload() {
		return autoload;
	}

	public void setAutoload(boolean autoload) {
		this.autoload = autoload;
	}
	
	

	@Override
	public long getSerialversionuid() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}

}
