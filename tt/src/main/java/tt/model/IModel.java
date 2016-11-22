package tt.model;

import java.io.Serializable;
import java.math.BigInteger;

public interface IModel extends Comparable<Object> , Serializable {

	
	public BigInteger getId();
	public void setId(BigInteger id);

}
