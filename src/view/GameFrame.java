package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;

import consts.Const;
import model.Cell;
import model.CellModel;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = -6399886456682347905L;
	
	private JPanel toolPabel;
	private ScoreLabel scoreLabel;
	private ScoreLabel maxScoreLabel;
	private JButton restartButton;
	
	private JScrollPane tablePanel;
	private JTable table;
	private CellModel model;
	
	private boolean isMoved;
	private int score;
	private int maxScore;
	
	public GameFrame() {
		this.setTitle("2048——Cwift");
		this.setBounds(100, 100, Const.frameW, Const.frameH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		for (KeyListener lis : this.getKeyListeners()) {
			this.removeKeyListener(lis);
		}
		this.setFocusable(true);
		initToolPanel();
		initTablePanel();
		
		this.table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) { 
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: up();break;
				case KeyEvent.VK_LEFT: left();break;
				case KeyEvent.VK_DOWN: down();break;
				case KeyEvent.VK_RIGHT: right();break;
				default:
				}
				if (isMoved) {
					addCell();
				}
			}
		});
		
		initGame();
	}
	
	private void initToolPanel() {
		toolPabel = new JPanel();
		toolPabel.setSize(Const.frameW, Const.toolH);
		this.getContentPane().add(toolPabel, BorderLayout.NORTH);
		scoreLabel = new ScoreLabel("当前得分: ");
		toolPabel.add(scoreLabel);
		maxScoreLabel = new ScoreLabel("最高得分: ");
		toolPabel.add(maxScoreLabel);
		restartButton = new JButton("开始游戏");
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				restartButton.setText("重新开始");
				restart();
			}
		});
		toolPabel.add(restartButton);
	}

	private void initTablePanel() {
		tablePanel = new JScrollPane();
		this.getContentPane().add(tablePanel, BorderLayout.CENTER);
		tablePanel.setVisible(true);
		table = new JTable() {

			private static final long serialVersionUID = 115666827245565879L;

			@Override
			public void updateUI() {
				super.updateUI();
				setUI(new BasicTableUI() {
					@Override
					protected void installKeyboardActions() { 
						
					}
				});
			}
		};
		table.setSize(Const.tableW, Const.tableH);
		model = new CellModel();
		table.setModel(model);
		table.setRowHeight(Const.cellH);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(false);
		table.setFont(new Font("微软雅黑", Font.BOLD, 40));
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		table.setCellSelectionEnabled(false);
		tablePanel.add(table);
		table.setVisible(true);
		
	}
	
	private void initGame() {
		addCell();
		addCell();
		table.revalidate();
		table.repaint();
		table.clearSelection();
		table.grabFocus();
		
		score = 0;
		scoreLabel.setScore(0);
		isMoved = false;
	}
	
	private void addCell() {
		int n = model.getEmptyCellCount();
		int location = (int)(Math.random() * n) + 1;
		Cell cell;
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (model.getValueAt(i, j).equals("")) {
					count++;
					if (count == location) {
						if(n >= 8 && Math.random() <= 0.25) {
							cell = new Cell(4);
						}else {
							cell = new Cell(2);
						}
						model.setValueAt(cell, i, j);
						break;
					}
				}
			}
		}
		n--;
		if (n == 0) {
			if (isEnd()) {
				JOptionPane.showMessageDialog(null, "游戏结束了");
			}
		}
		isMoved = false;
		table.revalidate();
		table.repaint();
	}
	
	private boolean isEnd() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i != 0 && model.getCellAt(i, j).VALUE == model.getCellAt(i - 1, j).VALUE) {
					return false;
				}
				if (i != 3 && model.getCellAt(i, j).VALUE == model.getCellAt(i + 1, j).VALUE) {
					return false;
				}
				if (j != 0 && model.getCellAt(i, j).VALUE == model.getCellAt(i, j - 1).VALUE) {
					return false;
				}
				if (j != 3 && model.getCellAt(i, j).VALUE == model.getCellAt(i, j + 1).VALUE) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void up() {
		for (int col = 0; col < 4; col++) {
			for (int row = 0; row < 4; row++) {
				Cell cell = model.getCellAt(row, col);
				if (cell != null) {
					while(row > 0) {
						if (model.getCellAt(row - 1, col) != null) {
							if (model.getCellAt(row - 1, col).VALUE == cell.VALUE) {
								model.setValueAt(null, row, col);
								int value = cell.VALUE * 2;
								addScore(value);
								model.setValueAt(new Cell(value), row - 1, col);
								isMoved = true;
							}
							break;
						}else {
							cell = model.getCellAt(row, col);
							model.setValueAt(null, row, col);
							model.setValueAt(cell, row - 1, col);
							row--;
							isMoved = true;
						}
					}
				}
				
			}
		}
	}

	private void left() {
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				Cell cell = model.getCellAt(row, col);
				if (cell != null) {
					while(col > 0) {
						if (model.getCellAt(row , col - 1) != null) {
							if (model.getCellAt(row, col - 1).VALUE == cell.VALUE) {
								model.setValueAt(null, row, col);
								int value = cell.VALUE * 2;
								addScore(value);
								model.setValueAt(new Cell(value), row, col - 1);
								isMoved = true;
							}
							break;
						}else {
							cell = model.getCellAt(row, col);
							model.setValueAt(null, row, col);
							model.setValueAt(cell, row, col - 1);
							col--;
							isMoved = true;
						}
					}
				}
				
			}
		}
	}

	private void down() {
		for (int col = 0; col < 4; col++) {
			for (int row = 3; row >= 0; row--) {
				Cell cell = model.getCellAt(row, col);
				if (cell != null) {
					while(row < 3) {
						if (model.getCellAt(row + 1, col) != null) {
							if (model.getCellAt(row + 1, col).VALUE == cell.VALUE) {
								model.setValueAt(null, row, col);
								int value = cell.VALUE * 2;
								addScore(value);
								model.setValueAt(new Cell(value), row + 1, col);
								isMoved = true;
							}
							break;
						}else {
							cell = model.getCellAt(row, col);
							model.setValueAt(null, row, col);
							model.setValueAt(cell, row + 1, col);
							row++;
							isMoved = true;
						}
					}
				}
				
			}
		}
	}
	
	private void right() {
		for (int row = 0; row < 4; row++) {
			for (int col = 3; col >= 0; col--) {
				Cell cell = model.getCellAt(row, col);
				if (cell != null) {
					while(col < 3) {
						if (model.getCellAt(row , col + 1) != null) {
							if (model.getCellAt(row, col + 1).VALUE == cell.VALUE) {
								model.setValueAt(null, row, col);
								int value = cell.VALUE * 2;
								addScore(value);
								model.setValueAt(new Cell(value), row, col + 1);
								isMoved = true;
							}
							break;
						}else {
							cell = model.getCellAt(row, col);
							model.setValueAt(null, row, col);
							model.setValueAt(cell, row, col + 1);
							col++;
							isMoved = true;
						}
					}
				}
				
			}
		}
	}

	private void restart() {
		model.clear();
		initGame();
	}
	
	private void addScore(int score) {
		this.score += score;
		scoreLabel.setScore(this.score);
		if (this.score > maxScore) {
			maxScore = this.score;
			maxScoreLabel.setScore(maxScore);
		}
	}

	
}
