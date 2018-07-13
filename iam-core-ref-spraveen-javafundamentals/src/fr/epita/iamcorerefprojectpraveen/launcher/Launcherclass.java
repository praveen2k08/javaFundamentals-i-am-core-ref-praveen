package fr.epita.iamcorerefprojectpraveen.launcher;
/**
 * 
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import java.util.logging.Logger;

import fr.epita.iamcorerefprojectpraveen.datamodel.Identity;
import fr.epita.iamcorerefprojectpraveen.exceptions.CreateIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.DeleteIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.SearchIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.UpdateIdentityException;
import fr.epita.iamcorerefprojectpraveen.services.Verification;
import fr.epita.iamcorerefprojectpraveen.services.Authorization;
import fr.epita.iamcorerefprojectpraveen.services.IdentityDAO;
import fr.epita.iamcorerefprojectpraveen.services.JDBCIdentityDAO;

/**
 * @author Praveen Kumar
 *
 */
/**
 * @author Praveen Kumar
 *
 */
public class Launcherclass {
	private static Scanner scan;
	static Logger logger = Logger.getLogger(Launcherclass.class.getName());
	public static void main(String[] args) throws Exception {
		 scan = new Scanner(System.in);


System.out.println("Bonjour/Welcome");
       if (!authenticate()) {
	        	System.out.println("Wrong Username/Password.Authorization failure.");
	            System.exit(0);
	        }

	        System.out.println("Authorized successfully.Welcome to the Main Screen");

	        mainfunction();
	    }

	    private static boolean authenticate() {
	        Verification authenticator = new Authorization();

	        System.out.println("Please enter username and password to proceed:");
	        System.out.println("Username: ");
	        String username = scan.nextLine();
	        System.out.println("Password: ");
	        String password = scan.nextLine();

	        return authenticator.authenticate(username, password);
	    }

	    /**
	     * @throws Exception
	     */
	    private static void mainfunction() throws Exception {
	        System.out.println("");
	        System.out.println("1) Create a new Identity");
	        System.out.println("2) Search for Identities");
	        System.out.println("3) Update an Identity");
	        System.out.println("4) Delete a Identity");
	        System.out.println("5) Quit");

	       System.out.println("Please Choose your task: ");

	        String chooseoption = scan.nextLine().trim();

	        switch (chooseoption) {
	            case "1":
	                createIdentityMenu();
	                break;
	            case "2":
	                searchIdentityMenu();
	                break;
	            case "3":
	                updateIdentityMenu();
	                break;
	            case "4":
	                deleteIdentityMenu();
	                break;
	            case "5":
	             
	                System.out.println("Thanks for Using the application");
	                System.exit(0);
	                break;
	            default:
	                System.out.println("Please enter the correct option within the choice.");
	                mainfunction();
	        }
	    }

	    /**
	     * @throws Exception
	     */
	    private static void createIdentityMenu() throws Exception {
	        System.out.println("You are going to Create a new identity: ");
	        System.out.println("Please enter the Profile Name: ");
	        String displayname = scan.nextLine();
	        System.out.println("Please enter the Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(displayname, email, null);

	        createIdentity(identity);
	        mainfunction();
	    }

	    private static void searchIdentityMenu() throws Exception {
	        System.out.println("Search identities: ");
	        System.out.println("Enter the UID to Search: ");
	        String uid = scan.nextLine();
	        System.out.println("Enter the Display Name to search: ");
	        String displayname = scan.nextLine();
	        System.out.println("Email: ");
	        String email = scan.nextLine();
	        Identity identity = new Identity(null, null, null);

	        if (uid.length() != 0) {
	            identity.setUid(Integer.parseInt(uid));
	        }

	        if (displayname.length() != 0) {
	            identity.setDisplayName(displayname);
	        }

	        if (email.length() != 0) {
	            identity.setEmail(email);
	        }

	        searchIdentity(identity);
	        mainfunction();
	    }

	    /**
	     * @throws Exception
	     */
	    private static void updateIdentityMenu() throws Exception {
	        System.out.println("Update Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("Choose Identity by UID to be updated:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);
	        System.out.println("New Display Name to be updated:");
	        identity.setDisplayName(scan.nextLine());
	        System.out.println("New Email to be Updated:");
	        identity.setEmail(scan.nextLine());

	        updateIdentity(identity);

	        mainfunction();
	    }

	    private static void deleteIdentityMenu() throws Exception {
	        System.out.println("Delete Identity:");
	        Identity identity = new Identity(null, null, null);

	        searchIdentity(identity);
	        System.out.println("Choose Identity by UID to be deleted:");
	        int uid = Integer.parseInt(scan.nextLine());
	        identity.setUid(uid);

	        deleteIdentity(identity);

	        mainfunction();
	    }

	    /**
	     * @param identity
	     * @throws SQLException 
	     * @throws CreateIdentityException 
	     */
	    private static void createIdentity(Identity identity) throws SQLException, CreateIdentityException {
	       
	            IdentityDAO dao = new JDBCIdentityDAO();
	           
	                dao.create(identity);
	                System.out.println("Successfully created the new identity!");
	            
	                dao.close();
	          
	    }

	    /**
	     * @param criteria
	     * @throws SQLException 
	     * @throws SearchIdentityException 
	     * @throws Exception
	     */
	    private static void searchIdentity(Identity criteria) throws SQLException, SearchIdentityException {
	       
	            IdentityDAO dao = new JDBCIdentityDAO();
	            
	                List<Identity> results = dao.find(criteria);
	                for (Identity result: results) {
	                    System.out.println(result);
	                }
	             
	        
	                    dao.close();
	           
	        }
	    

	    /**
	     * @param identity
	     * @throws SQLException
	     * @throws UpdateIdentityException
	     */
	    private static void updateIdentity(Identity identity) throws SQLException, UpdateIdentityException {
	     
	            IdentityDAO dao = new JDBCIdentityDAO();
	           
	                dao.update(identity);
	                System.out.println("Successfully updated the  identity!");
	          
	                dao.close();
	            
	      	    }

	    /**
	     * @param identity
	     * @throws DeleteIdentityException
	     * @throws SQLException
	     */
	    private static void deleteIdentity(Identity identity) throws DeleteIdentityException, SQLException {
	    
	            IdentityDAO dao = new JDBCIdentityDAO();
	         
	                dao.delete(identity);
	                System.out.println("Successfully deleted the  identity!");
	            
	
	                dao.close();
	            
	        }
	    }




