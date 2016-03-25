package org.paces.Stata.GitHub;

import org.eclipse.egit.github.core.client.GitHubClient;

import java.io.*;
import java.util.*;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Authenticate {

	private List<String> credentials = new ArrayList<>();

	private final GitHubClient client = new GitHubClient();

	public Authenticate(String oauthOrFile) {
		if (isFile(oauthOrFile)) setCredentials(new File(oauthOrFile));
		else this.setCredentials(oauthOrFile);
	}

	public Authenticate(String uid, String pwd) {
		this.setCredentials(uid, pwd);
	}

	private void setCredentials(File credFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(credFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				this.credentials.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setCredentials(String token) {
		this.credentials.add(token);
	}

	private void setCredentials(String uid, String pwd) {
		this.credentials.add(uid);
		this.credentials.add(pwd);
	}

	private Boolean isFile(String arg) {
		File x = new File(arg);
		return x.exists();
	}

	protected List<String> getCredentials() {
		return this.credentials;
	}

	private void authenticateClient() {
		if (this.credentials.size() == 1) this.client.setOAuth2Token(this.credentials.get(0));
		else this.client.setCredentials(this.credentials.get(0), this.credentials.get(1));
	}

	protected GitHubClient getClient() {
		this.authenticateClient();
		return this.client;
	}

}
