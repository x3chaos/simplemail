package org.l3eta;

public class StringUtils {

	/**
	 * Slices an array at the given index.
	 * 
	 * @author l3eta
	 * @param o
	 *            The original {@code String[]} to slice
	 * @param index
	 *            The index at which to slice
	 * @return The new, processed {@code String[]}
	 */
	public static String[] slice(String[] o, int index) {
		String[] result = new String[o.length - index];
		for (int i = index; i < o.length; i++) {
			result[i - index] = o[i];
		}
		return result;
	}

	/**
	 * Completes a {@code String[]}, used primarily to separate a message from a
	 * set of arguments.
	 * 
	 * @author l3eta
	 * @param o
	 *            The {@code String[]} to complete
	 * @return The completed {@code String}
	 */
	public static String complete(String[] o) {
		String result = "";
		for (int i = 0; i < o.length; i++) {
			result += o[i] + " ";
		}
		return result.trim();
	}

	/**
	 * A shortcut for having to nest a {@link slice()} inside a
	 * {@link complete()}
	 * 
	 * @author l3eta
	 * @param o
	 *            The {@code String[]} to complete
	 * @param index
	 *            The index from which to complete the {@code String}
	 * @return The completed {@code String}, sliced at the given index.
	 */
	public static String complete(String[] o, int index) {
		return complete(slice(o, index));
	}

}
