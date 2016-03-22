package org.paces.Stata.GitHub;

import java.io.*;
import java.util.*;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Authenticate {

	private Map<String, String> credentials = new HashMap<>();

	public Authenticate(String oauthOrFile) {
		if (isFile(oauthOrFile)) setCredentials(new File(oauthOrFile));
		else setCredentials(oauthOrFile);
	}

	public Authenticate(String uid, String pwd) {
		setCredentials(uid, pwd);
	}

	public void setCredentials(File credFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(credFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(" = ");
				if (tokens.length == 2) this.credentials.put(tokens[0], tokens[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCredentials(String token) {
		this.credentials.put("oauth", token);
	}

	public void setCredentials(String uid, String pwd) {
		this.credentials.put("username", uid);
		this.credentials.put("password", pwd);
	}

	public Boolean isFile(String arg) {
		File x = new File(arg);
		return x.exists();
	}

	protected Map<String, String> getCredentials() {
		return this.credentials;
	}

}
