
package fr.epita.iamcorerefprojectpraveen.services;


/**
 * @author Praveen Kumar
 *
 */
/**
 * @author Praveen Kumar
 *
 */
public enum ConfKeyfile {
	/**
	 * this is the key to choose the backend mode
	 */
	BACKEND_MODE("backend.mode"),
	/**
	 * this is the key to choose the fall back backend mode
	 */

	FALLBACK_BACKEND_MODE("backend.mode"),

	/**
	 *
	 */
	DB_URL("db.url"),

	/**
	 *
	 */
	DB_USER("db.user"),

	/**
	 *
	 */
	DB_PASSWORD("db.pwd"),

	/**
	 *
	 */
	DB_BACKEND("db"),

	/**
	 *
	 */
	/**
	 * 
	 */
	IDENTITY_SEARCH_QUERY(
			"identity.search"),
	/**
	 *
	 */
	IDENTITY_INSERT("identity.insert"),
	IDENTITY_DELETE("identity.DELETE"),
	IDENTITY_UPDATE("identity.UPDATE"),
	


	;

	private String key;

	/**
	 *
	 */
	/**
	 * @param key
	 */
	private ConfKeyfile(String key) {
		this.key = key;
	}

	/**
	 * @return
	 */
	public String getKey() {
		return key;
	}

}
