import java.util.ArrayList;

public class SubstitutionAlgorythm {
	// алгоритм второй шанс	
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
					System.out.println("Страница № " + Table.get(i).getPageNumber() + "выгружена на диск,"
						+ " так как признак обращения сброшен");
					physicalMemory.sendPageToDisk(number);						
				//делаем запись в таблице, что страница не отображена на физическую память
				Table.get(i).removeFromPhysicalMemory();
				Table.get(i).setNumberOfPhysicalPage(-1);
				break;
			}			
			else {
				//если признак обращения установлен, то он сбрасывается, а страница перемещается в конец очереди
				Table.get(i).r(false);
				Table.add(Table.get(i));
				Table.remove(i);
				System.out.println("Страница № " + Table.get(i).getPageNumber() + "перемещена в конец очереди, признак обращения сброшен");
			}
			
				
		}
	}
}

