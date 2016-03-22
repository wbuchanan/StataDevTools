package org.paces.Stata.GitHub;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.service.GistService;

import java.io.*;
import java.util.*;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class MakeGist {

	private Map<String, GistFile> gistFiles = new HashMap<>();
	private Gist gist = new Gist();

	public MakeGist() {
	}

	public MakeGist(GistService service, List<String> fileList) {
		this();
		this.contentFromFiles(fileList);
		this.setContent();
	}

	public MakeGist(GistService service, List<String> fileList, String
		description) {
		this(service, fileList);
		gist.setDescription(description);
	}


	public void contentFromFiles(List<String> fileNames) {
		for(Integer i = 0; i < fileNames.size(); i++) {
			try (BufferedReader br = new BufferedReader(new FileReader(fileNames.get(i)))) {
				String line;
				StringJoiner sj = new StringJoiner("\n");
				GistFile x = new GistFile();
				while ((line = br.readLine()) != null) {
					sj.add(line);
				}
				x.setContent(sj.toString());
				this.gistFiles.put(fileNames.get(i), x);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setContent() {
		this.gist.setFiles(this.gistFiles);
	}


	public Gist makeGist(GistService service, Gist gistContent) {
		Gist retval;
		try {
			retval = service.createGist(gistContent);
		} catch (IOException e) {
			e.printStackTrace();
			retval = new Gist();
		}
		return retval;
	}

}
