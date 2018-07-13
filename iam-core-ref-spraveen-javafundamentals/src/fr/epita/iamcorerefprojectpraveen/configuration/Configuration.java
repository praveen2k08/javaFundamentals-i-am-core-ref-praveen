package fr.epita.iamcorerefprojectpraveen.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

import fr.epita.iamcorerefprojectpraveen.launcher.Launcherclass;
import fr.epita.iamcorerefprojectpraveen.services.ConfKeyfile;

/**
 * class created below is for the configuration Project
 * 
 * @author Praveen Kumar
 *
 */
public class Configuration {
	
	private Configuration()
	{
		
	}
	/**
     * JDBC Connection string.
     */
    public static final String jdbcurl = "jdbc:derby://localhost:1527/testconnection";

    /**
     * JDBC Username to get connection.
     */
    public static final String jdbcusernam = "admin";

    /**
     * JDBC Password to get connection
     */
    public static final String jdbcpa = "root1234";

    /**
     * Authenticator Username.
     */
    public static final String authenticateuname = "admin";

    /**
     * Authenticator Password.
     */
    public static final String authenticatep = "root1234";
    
    public static final String BACKEND_MODE = "backend.mode";
	public static final String FALLBACK_BACKEND_MODE = "fallback.backend.mode";
	public static final String DB_BACKEND = "db";
	public static final String FILE_BACKEND = "file";
	static Logger logger = Logger.getLogger(Launcherclass.class.getName());
	
    private static Properties properties;

	static {
		init();
	}
    private static void init() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File(System.getProperty("conf.file.path"))));
		} catch (final Exception e) {
			logger.info(e.getMessage());
		
		}


	}
    
    
    
    
    public static Integer getIntProperty(ConfKeyfile key) {
		final String valueAsString = getProperty(key);
		return Integer.valueOf(valueAsString);
	}
    
    public static String getProperty(ConfKeyfile key) {
		return properties.getProperty(key.getKey());
	}

	

}
