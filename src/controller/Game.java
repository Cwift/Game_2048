package controller;

import view.GameFrame;
/**
 * 
 * @author Cwift
 * 目前存在的bug：当面板满的时候，有时候会刷新不出来，需要用鼠标点一下table的cell才可以.
 */
public class Game {

	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		frame.setVisible(true);
	}

}
