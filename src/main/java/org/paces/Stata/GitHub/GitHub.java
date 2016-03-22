package org.paces.Stata.GitHub;

import org.eclipse.egit.github.core.service.GistService;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class GitHub {

	public static final GistService service = new GistService();






	protected void setGistCredentials() {

		service.getClient().setCredentials("user", "passw0rd");

	}

}
