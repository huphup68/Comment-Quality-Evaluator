package comments;

/** responsible for storing...*/
public class Comment {
	private String comment;
	private boolean filtered;
	/** @param comment A string representation of the comment */
	public Comment(String comment) {
		this(comment, false);
	}
	/** 
	 * @param comment A string representation of the comment
	 * @param filtered A value representing if the comment is filtered or not
	 */
	public Comment(String comment, boolean filtered) {
		this.comment = comment;
		this.filtered = filtered;
	}
	/** Removes parts of the comment syntax so it complies better with natural language */
	public void filter() {
		// TODO: Implement method for filtering comments
		filtered = true;
	}
	/** @return The string representation of the comment */
	public String getComment() {
		return comment;
	}
	/** @return A value representing if the comment is filtered or not */
	public boolean getFiltered() {
		return filtered;
	}
}
