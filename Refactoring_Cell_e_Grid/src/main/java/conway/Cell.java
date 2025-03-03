package conway;

import data.Stato;

public class Cell {
	
	private Stato stato;
	
	
	public Cell(Stato stato) {
		
		this.stato=stato;
		
	}
	
	
	public void switchCellState() {
		
		if(this.stato==Stato.viva) {
			
			this.stato=Stato.morta;
		}
		else if(this.stato==Stato.morta){
			
			this.stato=Stato.viva;
			
		}
	}
	
	public Stato getCellState() {
		
		return this.stato;
	}

	public void setCellState(Stato s) {
		
		this.stato=s;
		
	}
	
	
	
}
