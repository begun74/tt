package tt.model;

import java.io.Serializable;
import java.math.BigInteger;

public interface IModel extends Comparable , Serializable {

	
	public BigInteger getId();
	public void setId(BigInteger id);

}
