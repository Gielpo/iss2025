package conway;

import org.junit.BeforeClass;
import org.junit.Test;

import conway.devices.ConwayInputMock;

public class Conway25Test {
	
	private static ConwayInputMock cim;
	private static Life life;
	private static LifeController cc;
	
	
	@BeforeClass
	public void setup() {

		life           = new Life( 3,3 );
	    cc   = new LifeController(life);   	
		cim = new ConwayInputMock(cc,life);
	}
	
	@Test
	public void test1() {
		
		
		
	}
	

}
