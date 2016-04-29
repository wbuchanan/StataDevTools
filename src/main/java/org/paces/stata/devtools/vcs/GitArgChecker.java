package org.paces.stata.devtools.vcs;

import org.eclipse.jgit.api.Git;
import org.paces.stata.devtools.vcs.Exceptions.StataGitException;

import java.io.*;
import java.util.*;

/**
 * This is a helper method used to initialize the Git object and remove the
 * file path from the remaining arguments in the string array.  It also
 * converts the remaining arguments from a String array to a List of strings
 * to use the convenience for loops.
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class GitArgChecker implements AutoCloseable {

	/**
	 * Member containing the exception method that is thrown by the class
	 * constructor.
	 */
	private static final StataGitException exception = new StataGitException
	("Insufficient number of arguments passed.  Must provide directory and " +
	"file");

	/**
	 * The Git class object.  Publicly accessible to avoid using getters to
	 * access the object.
	 */
	public Git git;

	/**
	 * A List of the String arguments passed from Stata.
	 */
	public List<String> args;

	/**
	 * Class constructor for the helper class
	 * @param args An array of Strings.  The first element of the array will
	 *                always contain a reference to the location of the Git
	 *                repository.  All other elements are stored in the args
	 *                member to avoid having to remove the file path from the
	 *                array when passing between different methods.
	 * @throws StataGitException An exception is thrown if the array passed
	 * to this class is of length 0 (e.g., no file paths passed).
	 */
	public GitArgChecker(String[] args) throws StataGitException {
		if (args.length < 1) throw(exception);
		try (Git x = Git.open(new File(args[0]))) {
			this.git = x;
			setArgs(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Private method used to consistently convert the remaining String
	 * elements.  The Arrays.copyOfRange() method fails to copy the array as
	 * desired if the from and to elements defining the range are the same
	 * value.  If a single argument after the file path is passed this caused
	 * the method to fail to remove the filepath stored in the zero element.
	 * @param argVals String array passed from the class constructor
	 */
	private void setArgs(String[] argVals) {
		List<String> theargs = new ArrayList<>();
		for(int i = 1; i < argVals.length; i++) theargs.add(argVals[i]);
		this.args = theargs;
	}


	/**
	 * Closes this resource, relinquishing any underlying resources. This method
	 * is invoked automatically on objects managed by the {@code
	 * try}-with-resources statement.
	 * <p>
	 * <p>While this interface method is declared to throw {@code Exception},
	 * implementers are <em>strongly</em> encouraged to declare concrete
	 * implementations of the {@code close} method to throw more specific
	 * exceptions, or to throw no exception at all if the close operation cannot
	 * fail.
	 * <p>
	 * <p> Cases where the close operation may fail require careful attention by
	 * implementers. It is strongly advised to relinquish the underlying
	 * resources and to internally <em>mark</em> the resource as closed, prior
	 * to throwing the exception. The {@code close} method is unlikely to be
	 * invoked more than once and so this ensures that the resources are
	 * released in a timely manner. Furthermore it reduces problems that could
	 * arise when the resource wraps, or is wrapped, by another resource.
	 * <p>
	 * <p><em>Implementers of this interface are also strongly advised to not
	 * have the {@code close} method throw {@link InterruptedException}.</em>
	 * <p>
	 * This exception interacts with a thread's interrupted status, and runtime
	 * misbehavior is likely to occur if an {@code InterruptedException} is
	 * {@linkplain Throwable#addSuppressed suppressed}.
	 * <p>
	 * More generally, if it would cause problems for an exception to be
	 * suppressed, the {@code AutoCloseable.close} method should not throw it.
	 * <p>
	 * <p>Note that unlike the {@link Closeable#close close} method of {@link
	 * Closeable}, this {@code close} method is <em>not</em> required to be
	 * idempotent.  In other words, calling this {@code close} method more than
	 * once may have some visible side effect, unlike {@code Closeable.close}
	 * which is required to have no effect if called more than once.
	 * <p>
	 * However, implementers of this interface are strongly encouraged to make
	 * their {@code close} methods idempotent.
	 *
	 */
	@Override
	public void close() {

	}
}
