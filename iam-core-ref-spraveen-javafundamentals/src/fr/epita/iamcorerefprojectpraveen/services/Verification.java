package fr.epita.iamcorerefprojectpraveen.services;
/**
 * 
 */
/**
 * @author Praveen Kumar
 *
 */
public interface Verification {
	 /**
     * Authenticate with user name and password.
     */
    boolean authenticate(String uname, String pword);
}
