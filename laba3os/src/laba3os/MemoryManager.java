package laba3os;

import java.util.ArrayList;

public class MemoryManager {		
	PhysicalMemory physicalMemory;
	SubstitutionAlgorythm clock;
	
	public MemoryManager() {
		physicalMemory = new PhysicalMemory();		
	}
	
	// метод, вызываемый при обращении к странице
	public void implementPage(int address, ArrayList<Record> Table) {			
			if(Table.get(address).isInPhysicalMemory() == true) {
				System.out.println("—траница є" + address + "отображена на физическую страницу " + Table.get(address).getNumberOfPhysicalPage());				
				}
			else {
				//загружаем страницу в пам€ть								
				int physicalPage = physicalMemory.getFreePage();
				//если есть место, то на свободную страниц
				if(physicalPage!= -1) {
					physicalMemory.takePhysicalPage(physicalPage);
					Table.get(address).sendToPhysicalMemory();
					Table.get(address).setNumberOfPhysicalPage(physicalPage);
					System.out.println("¬иртуальна€ страница є" + address + " отображена на физическую страницу є " + physicalPage);
				}
				else{
					System.out.println("¬се физические страницы зан€ты, запускаем алгоритм замещени€ страниц");
					clock = new SubstitutionAlgorythm(Table, physicalMemory);
					clock.start();// если нет места, то используем алгоритм замещени€ страниц
					implementPage(address,Table);//после выполнени€, ещЄ раз пытаемс€ разместить страницу в физической пам€ти
				}		
			}	
			// устанавливаем признак обращени€ к странице
			Table.get(address).r(true);
				
	}
		
}	