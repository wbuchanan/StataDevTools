package org.paces.Stata.GitHub;

import com.stata.sfi.*;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;

import java.util.*;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class GitHub {

	public static int makeGist(String[] args) {
		Authenticate auth;
		if (!args[0].isEmpty() && !args[1].isEmpty()) {
			auth = new Authenticate(args[0], args[1]);
		} else {
			auth = new Authenticate(args[0]);
		}
		GistService service = new GistService(auth.getClient());
		// File list should be a single comma delimited string in args[2]
		List<String> files = Arrays.asList(args[2].split(","));
		MakeGist gist = new MakeGist(service, files, args[3]);
		Gist publishedGist = gist.getPublished();
		Macro.setLocal("gist", publishedGist.getHtmlUrl());
		return 0;
	}

}
