package fr.epita.iamcorerefprojectpraveen.services;
/**
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.epita.iamcorerefprojectpraveen.configuration.Configuration;
import fr.epita.iamcorerefprojectpraveen.datamodel.Identity;
import fr.epita.iamcorerefprojectpraveen.exceptions.CreateIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.DeleteIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.SearchIdentityException;
import fr.epita.iamcorerefprojectpraveen.exceptions.UpdateIdentityException;
import fr.epita.iamcorerefprojectpraveen.launcher.Launcherclass;

/**
 * @author Praveen Kumar
 *
 */
/**
 * @author Praveen Kumar
 *
 */
public class JDBCIdentityDAO implements IdentityDAO {
	private Connection conn;
	static Logger logger = Logger.getLogger(Launcherclass.class.getName());
    public JDBCIdentityDAO() throws SQLException {
        this.conn = DriverManager.getConnection(
                Configuration.jdbcurl,
                Configuration.jdbcusernam,
                Configuration.jdbcpa
        );
    }

    /**
     * Stores the Identity to the Identity table.
     */
    /* (non-Javadoc)
     * @see fr.epita.iamcorerefprojectpraveen.services.IdentityDAO#create(fr.epita.iamcorerefprojectpraveen.datamodel.Identity)
     */
    public void create(Identity identity) throws CreateIdentityException {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    Configuration.getProperty(ConfKeyfile.IDENTITY_INSERT));
            statement.setString(1, identity.getDisplayName());
            statement.setString(2, identity.getEmail());
           
            statement.execute();
            
        } catch (SQLException sqle) {
            CreateIdentityException e = new CreateIdentityException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
       
    }

    /**
     * Search the Identity from the Identity table.
     */
    /* (non-Javadoc)
     * @see fr.epita.iamcorerefprojectpraveen.services.IdentityDAO#find(fr.epita.iamcorerefprojectpraveen.datamodel.Identity)
     */
    /* (non-Javadoc)
     * @see fr.epita.iamcorerefprojectpraveen.services.IdentityDAO#find(fr.epita.iamcorerefprojectpraveen.datamodel.Identity)
     */
    
    public List<Identity> find(Identity criteria) throws SearchIdentityException {
        List<Identity> results = new ArrayList<>();
        String sql = "SELECT * FROM TEST.IDENTITIES";
        boolean view = false;

        if (criteria.getUid() != null) {
            sql += " WHERE IDENTITY_UID=" + criteria.getUid();
            view = true;
        }

        if (criteria.getDisplayName() != null) {
            if (view) {
                sql += " AND IDENTITY_DISPLAYNAME='" + criteria.getDisplayName() + "'";
            } else {
                sql += " WHERE IDENTITY_DISPLAYNAME='" + criteria.getDisplayName() + "'";
            }
            view = true;
        }

        if (criteria.getEmail() != null) {
            if (view) {
                sql += " AND IDENTITY_EMAIL='" + criteria.getEmail() + "'";
            } else {
                sql += " WHERE IDENTITY_EMAIL='" + criteria.getEmail() + "'";
            }
        }

        try {
            PreparedStatement statement = this.conn.prepareStatement(sql);
            ResultSet statementResult = statement.executeQuery();
            while (statementResult.next()) {
                String displayname = statementResult.getString("IDENTITY_DISPLAYNAME");
                String email = statementResult.getString("IDENTITY_EMAIL");
                int uid = statementResult.getInt("IDENTITY_UID");
                results.add(new Identity(displayname, email, uid));
            }
        } catch (SQLException sqle) {
            SearchIdentityException e = new SearchIdentityException();
            e.initCause(sqle);
            e.setFaultObject(criteria);
            throw e;
        }

        return results;
    }

    /**
     * Update Identity to the Identity table.
     */
    /* (non-Javadoc)
     * @see fr.epita.iamcorerefprojectpraveen.services.IdentityDAO#update(fr.epita.iamcorerefprojectpraveen.datamodel.Identity)
     */
    public void update(Identity identity) throws UpdateIdentityException {
        try {
        	  PreparedStatement statement = conn.prepareStatement(
                      Configuration.getProperty(ConfKeyfile.IDENTITY_UPDATE));
            
            statement.setString(1, identity.getDisplayName());
            statement.setString(2, identity.getEmail());
            statement.setInt(3, identity.getUid());
            statement.execute();
        } catch (SQLException sqle) {
            UpdateIdentityException e = new UpdateIdentityException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
        
     
         }

    /**
     * Delete Identity from the Identity table.
     */
    public void delete(Identity identity) throws DeleteIdentityException {
        try {
        	
            PreparedStatement statement = this.conn.prepareStatement(Configuration.getProperty(ConfKeyfile.IDENTITY_DELETE));           
            statement.setInt(1, identity.getUid());
            statement.execute();
        } catch (SQLException sqle) {
            DeleteIdentityException e = new DeleteIdentityException();
            e.initCause(sqle);
            e.setFaultObject(identity);
            throw e;
        }
      
    }

    /**
     * Close the JDBC connection.
     */
    /* (non-Javadoc)
     * @see fr.epita.iamcorerefprojectpraveen.services.IdentityDAO#close()
     */
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
        	logger.info(e.getMessage());
        }
    }
}

