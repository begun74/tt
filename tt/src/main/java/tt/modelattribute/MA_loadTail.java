package tt.modelattribute;

public class MA_loadTail implements IMAmodel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950229997785369170L;
	
	private int row=1;
	
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
