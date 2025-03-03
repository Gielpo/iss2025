package conway;

import data.Stato;

/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
    //La struttura
    //private int rows=0;
    //private int cols=0;
    //private static int[][] grid;
    //private static int[][] nextGrid;
    
	private int rows=0;
    private int cols=0;
	
    private Grid grid;
    private Grid nextGrid;
 
    public Life( int rowsNum, int colsNum ) {
       
    	this.rows=rowsNum;
    	this.cols=colsNum;
    	
    	this.grid=new Grid(rowsNum, colsNum);
    	this.nextGrid=new Grid(rowsNum, colsNum);
        //createGrids();   //crea la struttura a griglia
    }

    public int getRowsNum(){
        return rows;
    }
    public int getColsNum(){
        return cols;
    }

//    protected void  createGrids() {
//    	Cell [][] griglia= this.griglia.getGrid();
//    	Cell [][] nextGrid= this.griglia.getNextGrid();
//		
//		for(int ii=0; ii<this.griglia.getRows();ii++){
//			
//			for(int kk=0;kk<this.griglia.getCols();kk++) {
//				
//				grid[ii][kk]= new Cell(Stato.morta);
//				nextGrid[ii][kk]= new Cell(Stato.morta);
//				
//			}
//		}
//    }

    protected void resetGrids() {
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.setCellState(j, i, Stato.morta);
                //setCellState(   i,   j, false );
                //outdev.setCellColor(  i,  j, grid[i][j] );
                nextGrid.setCellState(j, i, Stato.morta);
            }
        }
        //CommUtils.outyellow("Life | initGrids done");
    }


    protected int countNeighborsLive(int row, int col) {
        int count = 0;
        if (row-1 >= 0) {
            if (grid.getCellState(row-1, col) == Stato.viva) count++;
        }
        if (row-1 >= 0 && col-1 >= 0) {
            if (grid.getCellState(row-1, col-1) == Stato.viva) count++;
        }
        if (row-1 >= 0 && col+1 < cols) {
            if (grid.getCellState(row-1, col+1) == Stato.viva) count++;
        }
        if (col-1 >= 0) {
            if (grid.getCellState(row, col-1) == Stato.viva) count++;
        }
        if (col+1 < cols) {
            if (grid.getCellState(row, col+1) == Stato.viva) count++;
        }
        if (row+1 < rows) {
            if (grid.getCellState(row+1, col) == Stato.viva) count++;
        }
        if (row+1 < rows && col-1 >= 0) {
            if (grid.getCellState(row+1, col-1) == Stato.viva) count++;
        }
        if (row+1 < rows && col+1 < cols) {
            if (grid.getCellState(row+1, col+1) == Stato.viva) count++;
        }
        return count;
    }



    protected void computeNextGen( IOutDev outdev ) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int n = countNeighborsLive(i,j);
                applyRules(i, j, n);
                outdev.displayCell( ""+grid.getNumericCellState(i, j) );
            }
            outdev.displayCell("\n");  //Va tolta nel caso della GUI?
        }
        copyAndResetGrid( outdev );
        outdev.displayCell("\n");
    }

    protected void copyAndResetGrid( IOutDev outdev ) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.setCellState(i, j, nextGrid.getCellState(i, j));
                //outdev.displayCell( ""+grid[i][j] );
                nextGrid.setCellState(i, j, Stato.morta);
            }
        }
    }

    protected void applyRules(int row, int col, int numNeighbors) {
        //int numNeighbors = countNeighborsLive(row, col);
        //CELLA VIVA
        if (grid.getCellState(row, col)==Stato.viva) {
            if (numNeighbors < 2) { //muore per isolamento
                nextGrid.setCellState(row, col, Stato.morta);
            } else if (numNeighbors == 2 || numNeighbors == 3) { //sopravvive
                nextGrid.setCellState(row, col, Stato.viva);
            } else if (numNeighbors > 3) { //muore per sovrappopolazione
                nextGrid.setCellState(row, col, Stato.morta);
            }
        }
        //CELLA MORTA
        else if (grid.getCellState(row, col)==Stato.morta) {
            if (numNeighbors == 3) { //riproduzione
                nextGrid.setCellState(row, col, Stato.viva);
            }
        }
        //CommUtils.outgreen("Life applyRules " + nextGrid   );
    }

    public void switchCellState(int i, int j){
        if( grid.getCellState(i, j)==Stato.morta) grid.setCellState(i, j, Stato.viva);       
        else if( grid.getCellState(i, j)==Stato.viva) grid.setCellState(i, j, Stato.morta); 
    }

    public  Stato getCellState( int i, int j  ) {
        return   grid.getCellState(i, j);
    }
 


}
