package tt.modelattribute;

public class MA_loadNomencl  implements IMAmodel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -765852209444376374L;


	
	private int row=1;
	private int col_name=1;
	private int col_code=2;
	private int col_article=2;
	
	private boolean save;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MA_loadNomencl [row=" + row + ", col_name=" + col_name + ", col_code=" + col_code + ", col_article="
				+ col_article + ", save=" + save + ", autoload=" + autoload + "]";
	}
	
	

}
