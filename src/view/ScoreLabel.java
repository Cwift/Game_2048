package view;

import javax.swing.JLabel;

public class ScoreLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2002923302303017240L;
	
	private int score = 0;
	private String title;
	
	public ScoreLabel() {
		super();
		title = "";
	}
	
	public ScoreLabel(String title) {
		super(title);
		this.title = title;
		this.setText(title + score);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
		this.setText(title + score);
	}

}
