package model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CellModel implements TableModel {

	private Cell[][] cells;
	
	public CellModel() {
		cells = new Cell[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cells[i][j] = null;
			}
		}
	}
	
	public int getEmptyCellCount() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (cells[i][j] == null) {
					count++;
				}
			}
		}
		return count;
	}
	
	public Cell getCellAt(int row, int col) {
		return cells[row][col];
	}
	
	@Override
	public int getRowCount() {
		return 4;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cell cell = cells[rowIndex][columnIndex];
		return cell == null ? "" : String.valueOf(cell.VALUE);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		cells[rowIndex][columnIndex] = (Cell)aValue;
	}

	@Override
	public void addTableModelListener(TableModelListener l) { }

	@Override
	public void removeTableModelListener(TableModelListener l) { }

	public void clear() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cells[i][j] = null;
			}
		}
	}
}
