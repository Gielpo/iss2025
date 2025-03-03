package conway;

import data.Stato;

public class Grid {
	
	private int rows;
	private int cols;
	
	private Cell [][] griglia;
	
	public Grid(int rows, int cols) {
		
		this.cols=cols;
		this.rows=rows;
		
		createGrids();
		
	}
	
	private void createGrids() {
		
		griglia=new Cell[this.cols][this.rows];
		
		for(int ii=0;ii<rows;ii++) {
			
			for(int kk=0; kk<cols;kk++) {
				
				griglia[ii][kk]=new Cell(Stato.morta);
			}
			
		}
		
		
	}
	
	public void setCellState(int row, int col, Stato stato) {
		
		this.griglia[row][col].setCellState(stato);
		
	}
	
	public Stato getCellState(int row, int col) {
		
		return this.griglia[row][col].getCellState();
		
	} 
	
	public int getNumericCellState(int row, int col) {
		
		if(this.griglia[row][col].getCellState()==Stato.viva) {
			
			return 1;
			
		}else {
			
			return 0;
		}
	}

	public Cell[][] getGriglia() {
		return griglia;
	}

	public void setGriglia(Cell[][] grid) {
		this.griglia = grid;
	}


	public int getRows() {
		return rows;
	}

//	public void setRows(int rows) {
//		this.rows = rows;
//	}

	public int getCols() {
		return cols;
	}

//	public void setCols(int cols) {
//		this.cols = cols;
//	}
	
	
}
