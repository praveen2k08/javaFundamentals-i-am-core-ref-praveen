package fr.epita.iamcorerefprojectpraveen.exceptions;
/**
 * 
 */
public class CreateIdentityException extends Exception {
	 private Object faultObject;

	    /**
	     *
	     * @param Object
	     */
	    public void setFaultObject(Object obj){
	        this.faultObject = obj;
	    }

	       /* (non-Javadoc)
	     * @see java.lang.Throwable#getMessage()
	     */
	    @Override
	    public String getMessage() {
	        return super.getMessage() + String.valueOf(this.faultObject);
	    }
	}


