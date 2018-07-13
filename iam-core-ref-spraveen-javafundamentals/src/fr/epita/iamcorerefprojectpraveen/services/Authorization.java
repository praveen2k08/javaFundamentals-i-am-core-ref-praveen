package fr.epita.iamcorerefprojectpraveen.services;
import fr.epita.iamcorerefprojectpraveen.configuration.Configuration;

/**
 * @author Praveen Kumar
 *
 */
public class Authorization implements Verification {
	 private String username;
	    private String password;

	    /**Get a default username and password created by the user. It is created in the JDBCIdentity DAO class.
	     */
	    public Authorization() {
	        this.username = Configuration.authenticateuname;
	        this.password = Configuration.authenticatep;
	    }

	    /**
	     * @param username 
	     * @param password 
	     */
	    public boolean authenticate(String username, String password) {
	        return username.equals(this.username) && password.equals(this.password);
	    }

}
