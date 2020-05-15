package laba3os;

import java.util.ArrayList;
import java.util.Random;

public class Main {		
	
	public static void main(String[] args) {		
		Random rnd = new Random();
		int pages[] = new int[32];   
		ArrayList<Record> Table = new ArrayList<>(); 
		for(int i = 0; i < 32; i++) {
			pages[i] = rnd.nextInt(32);
			Table.add(new Record(i));
		}		
		
		MemoryManager manager = new MemoryManager();	
		
        for(int i = 0; i < pages.length; i++){ 
        	manager.implementPage(pages[i], Table);
        	System.out.println("Обращение к адресу " + pages[i]);
	    	  
        }	

	}
}
