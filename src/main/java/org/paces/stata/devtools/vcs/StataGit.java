package org.paces.stata.devtools.vcs;

import com.stata.sfi.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.paces.stata.devtools.vcs.Exceptions.StataGitException;

import java.io.*;
import java.nio.file.*;
import java.util.StringJoiner;

/**
 * Stata Entry point for Git integration.  Includes methods used to call
 * typical Git commands and also provides methods for installing user written
 * programs from Git repositories.
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class StataGit {

	private static final FileRepositoryBuilder REPO_BUILDER = new FileRepositoryBuilder();

	public static void main(String[] args) {
		File x = new File("/Users/billy/Desktop/Programs/Testing/test.txt");
		FileOutputStream fos = null;
		String contents = "Sample Contents";
		String[] arg = {"/Users/billy/Desktop/Programs/Testing/", "test.txt"};
		String[] arg2 = {"/Users/billy/Desktop/Programs/Testing/", "test.txt"};
		String[] arg3 = {"/Users/billy/Desktop/Programs/Testing/", "This is " +
			"just a test"};
		String[] arg4 = {"/Users/billy/Desktop/Programs/Testing/",
			"/Users/billy/Desktop/Programs/Testing/commitMessage.txt"} ;
		String mod = "Modification to original test file";

		try {
			fos = new FileOutputStream(x, true);
			init(arg);
			fos.write(contents.getBytes());
			add(arg2);
			commit(arg3);
			fos.write(mod.getBytes());
			add(arg2);
			commit(arg4);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

	/**
	 * Method used to initialize a new Git repository in the specified location
	 * @param args A single element string array containing the directory
	 *                where the Git repository will be initialized
	 * @return A success/failure code required by the Stata Java API
	 */
	public static int init(String[] args) {
		try (Git g = Git.init().setDirectory(new File(args[0])).call()) {
			return 0;
		} catch (GitAPIException e) {
			SFIToolkit.stackTraceToString(e);
			return 198;
		}
	}

	/**
	 * Method used to add files to an existing repository
	 * @param files The directory where the repository is located and the
	 *                 files or file pattern to stage for commit
	 * @return A success/failure code required by the Stata Java API
	 */
	public static int add(String[] files) {
		try (GitArgChecker st = new GitArgChecker(files)) {
			if (files.length == 1) st.git.add().addFilepattern(".*").call();
			else for(String i : st.args) st.git.add().addFilepattern(i).call();
			return 0;
		} catch (StataGitException | GitAPIException e) {
			SFIToolkit.stackTraceToString(e);
			return 198;
		}
	}

	/**
	 * Method used for committing changes to the repository allows the first
	 * string passed to be either an actual string or a file containing
	 * commit messages.  If the string is a file name it will be used for the
	 * commit message, otherwise it will treat the passed string as the message.
	 * @param args An array of strings specifying the location of the
	 *                repository (args[0]) and either a file path to a text
	 *                file containing a commit message or the commit message
	 *                itself (args[1])
	 * @return A success/failure code required by the Stata Java API
	 */
	public static int commit(String[] args) {
		StringJoiner message = new StringJoiner(" ");
		try (GitArgChecker st = new GitArgChecker(args)) {
			if (new File(st.args.get(0)).exists()) {
				Files.lines(Paths.get(st.args.get(0))).forEach(message::add);
			}
			else message.add(st.args.get(0));
			st.git.commit().setMessage(message.toString()).call();
			return 0;
		} catch (StataGitException | GitAPIException | IOException e) {
			SFIToolkit.stackTraceToString(e);
			return 198;
		}
	}




}
