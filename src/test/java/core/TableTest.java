package core;


import org.junit.Test;

import junit.framework.TestCase;

public class TableTest extends TestCase {
    final String filePath = "src/test/resources/";
	
/*	@Test
	public void testMeldsCheck() {
		Table aTable = new Table();
		aTable.meldsCheck();
	}
*/	
	//@Test
/*	public void testIsSet() {
		Table table1 = new Table();
		assertEquals(false, table1.fileInputTestsIsMeld(filePath+"/isSetTest1.txt"));
		Table table2 = new Table();
		assertEquals(true, table2.fileInputTestsIsMeld(filePath+"/isSetTest2.txt"));
		Table table3 = new Table();
		assertEquals(false, table3.fileInputTestsIsMeld(filePath+"/isSetTest3.txt"));
	}
	*/
	/*@Test 
	public void testIsRun() {
		Table table1 = new Table();
		assertEquals(false, table1.fileInputTestsIsSet(filePath+"/isRunTest1.txt"));
		Table table2 = new Table();
		assertEquals(true, table2.fileInputTestsIsSet(filePath+"/isRunTest2.txt"));
		Table table3 = new Table();
		assertEquals(false, table3.fileInputTestsIsSet(filePath+"/isRunTest3.txt"));
		
	}*/
	
	@Test
	public void testSplitMeldAddCard() {
		Table table1 = new Table();
		assertEquals(true, table1.fileInputTestsSplitMeldAddCard(filePath+"/splitMeldAddCardTest1.txt"));
		Table table2 = new Table();
		assertEquals(false, table2.fileInputTestsSplitMeldAddCard(filePath+"/splitMeldAddCardTest2.txt"));
		Table table3 = new Table();
		assertEquals(false, table3.fileInputTestsSplitMeldAddCard(filePath+"/splitMeldAddCardTest3.txt"));
	}
	
	@Test
	public void testAddCard2Meld() {
		Table table1 = new Table();
		assertEquals(true, table1.fileInputTestsAddCard2Meld(filePath+"/AddCard2MeldTest1.txt"));
		Table table2 = new Table();
		assertEquals(true, table2.fileInputTestsAddCard2Meld(filePath+"/AddCard2MeldTest2.txt"));
		Table table3 = new Table();
		assertEquals(false, table3.fileInputTestsAddCard2Meld(filePath+"/AddCard2MeldTest3.txt"));
		Table table4 = new Table();
		assertEquals(false, table4.fileInputTestsAddCard2Meld(filePath+"/AddCard2MeldTest4.txt"));
		Table table5 = new Table();
		assertEquals(false, table5.fileInputTestsAddCard2Meld(filePath+"/AddCard2MeldTest5.txt"));
		Table table6 = new Table();
		assertEquals(true, table5.fileInputTestsAddCard2Meld(filePath+"/AddCard2MeldTest6.txt"));
	}
}
