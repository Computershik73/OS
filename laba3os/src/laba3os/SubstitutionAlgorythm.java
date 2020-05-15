package laba3os;

import java.util.ArrayList;

public class SubstitutionAlgorythm {
	// алгоритм часы	
	ArrayList<Record> Table;
	PhysicalMemory physicalMemory;
	
	public SubstitutionAlgorythm(ArrayList<Record> Table, PhysicalMemory physicalMemory) {
		this.Table = Table;
		this.physicalMemory = physicalMemory;
	}
	// проходим по таблице
	public void start() {
		for(int i = 0; i< Table.size(); i++) {
			int number = Table.get(i).getNumberOfPhysicalPage();
			//если признак обращения сброшен, то страница выгружается
			if(Table.get(i).r() == false && Table.get(i).isInPhysicalMemory()) {				
				// если страница не была изменена, то просто удаляем
				if(Table.get(i).isModified() == false) {
					System.out.println("Страница №" + Table.get(i).getPageNumber() + 
							" удалена из памяти, поскольку признак обращения сброшен и страница не была модифицирована");
					physicalMemory.removePage(number);
				}
				else {
					//если страница была изменена, то выгружаем на диск
					System.out.println("Страница №" + Table.get(i).getPageNumber() + " выгружена на диск,"
						+ " поскольку признак обращения сброшен");
					physicalMemory.sendPageToDisk(number);
				}		
				//делаем запись в таблице, что страница не отображена на физическую память
				Table.get(i).removeFromPhysicalMemory();
				Table.get(i).setNumberOfPhysicalPage(-1);
				break;
			}
			else {
				//если признак обращения установлен, то он сбрасывается
				Table.get(i).r(false);
				System.out.println("Страница №" + Table.get(i).getPageNumber() + " признак обращения сброшен");
			}
				
		}
	}
}