package laba3os;

import java.util.ArrayList;

public class MemoryManager {		
	PhysicalMemory physicalMemory;
	SubstitutionAlgorythm clock;
	
	public MemoryManager() {
		physicalMemory = new PhysicalMemory();		
	}
	
	// �����, ���������� ��� ��������� � ��������
	public void implementPage(int address, ArrayList<Record> Table) {			
			if(Table.get(address).isInPhysicalMemory() == true) {
				System.out.println("�������� �" + address + "���������� �� ���������� �������� " + Table.get(address).getNumberOfPhysicalPage());				
				}
			else {
				//��������� �������� � ������								
				int physicalPage = physicalMemory.getFreePage();
				//���� ���� �����, �� �� ��������� �������
				if(physicalPage!= -1) {
					physicalMemory.takePhysicalPage(physicalPage);
					Table.get(address).sendToPhysicalMemory();
					Table.get(address).setNumberOfPhysicalPage(physicalPage);
					System.out.println("����������� �������� �" + address + " ���������� �� ���������� �������� � " + physicalPage);
				}
				else{
					System.out.println("��� ���������� �������� ������, ��������� �������� ��������� �������");
					clock = new SubstitutionAlgorythm(Table, physicalMemory);
					clock.start();// ���� ��� �����, �� ���������� �������� ��������� �������
					implementPage(address,Table);//����� ����������, ��� ��� �������� ���������� �������� � ���������� ������
				}		
			}	
			// ������������� ������� ��������� � ��������
			Table.get(address).r(true);
				
	}
		
}	