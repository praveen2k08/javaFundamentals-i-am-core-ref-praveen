package fr.epita.iamcorerefprojectpraveen.services;
/**
 * 
 */
import java.util.List;

import fr.epita.iamcorerefprojectpraveen.datamodel.Identity;
import fr.epita.iamcorerefprojectpraveen.exceptions.CreateIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.DeleteIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.SearchIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.UpdateIdentityException;

public interface IdentityDAO {
	 /**
     * Stores the Identity to Database.
     * @param insert identity.
     */
    /**
     * @param identity
     * @throws CreateIdentityException
     */
    void create(Identity identity) throws CreateIdentityException;

    /**
     * Search or read Identity from Database with given criteria.
     * @param search for Identities.

     */
    List<Identity> find(Identity criteria) throws SearchIdentityException;

    /**
     * Update Identity in Database.
     */
    /**
     * @param identity
     * @throws UpdateIdentityException
     */
    void update(Identity identity) throws UpdateIdentityException;

    
    /**
     * Delete identity from database
     * 
     * @param identity
     * @throws DAODeleteException
     */
    /**
     * @param identity
     * @throws DeleteIdentityException
     */
    void delete(Identity identity) throws DeleteIdentityException;

    /**
     * Close the access to the Database.
     */
    void close();

}
