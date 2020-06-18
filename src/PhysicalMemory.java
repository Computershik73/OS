import java.util.ArrayList;

public class PhysicalMemory {
	private int sizeDisc = 1020;
	private int sizeSector = 4;
	private int sizePaintSectors;
	private int[] place;
	private int startSelectedFile;
	private ArrayList<Record> table= new ArrayList<Record>();
	
	public PhysicalMemory() {		
		this.sizePaintSectors = (int) Math.sqrt(Double.parseDouble(sizeDisc/sizeSector+""));
		place = new int[sizeDisc/sizeSector];	
	}	
	
	public ArrayList<Integer> getFileClusters(File file)
	{
		ArrayList<Integer> list= new ArrayList<Integer>();
		int start = -1;
		for(int i = 0; i < table.size(); i ++)
		{
			if(table.get(i).getFile() == file)
			{
				start = table.get(i).getStartFile();
				break;
			}
		}
		while(start != -1)
		{
			list.add(start);
			start = place[start];
		}
		return list;
	}

	public int allocateMemoryForFile(File file) {
		table.add(new Record(file,file.getStartInMem()));
		int size = file.getSize();
		int countSectors=size/sizeSector;
		int startNewFile = -1;
		int prevSector = 0;
		if(size%sizeSector>0)
			countSectors++;
		for (int i = 0; i < place.length; i++) {
			if(place[i]==0 && startNewFile == -1) {
				place[i] = -1;
				startNewFile = i;
				prevSector = i;	
				countSectors--;
				file.setStartInMem(startNewFile);
			} else if (place[i]==0) {
				place[prevSector]=i;
				prevSector = i;
				place[i]=-1;
				countSectors--;
			}
			if (countSectors==0)
				break;
		}
		return startNewFile;	
	}
	
	public void clearMemory(File file) {
		
		for (Record cellTable : table) {
			if(file == cellTable.getFile()) {
				table.remove(cellTable);
				break;
			}
		}
		int target = file.getStartInMem();
		if(place[target]!=-1) {
			clearMemory(place[target]);
		}
		place[target] = 0;
		startSelectedFile = -1;
	}

	private void clearMemory(int i) {
		if(place[i]!=-1) {
			clearMemory(place[i]);
		}
		place[i] = 0;
	}

	public int getStartSelectedFile() {
		return startSelectedFile;
	}

	public void setStartSelectedFile(int startSelectedFile) {
		this.startSelectedFile = startSelectedFile;
	}


	public int getSizeDisc() {
		return sizeDisc;
	}	

	public int getSizeSector() {
		return sizeSector;
	}

	public int getSizePaintSectors() {
		return sizePaintSectors;
	}


	public void setSizePaintSectors(int sizePaintSectors) {
		this.sizePaintSectors = sizePaintSectors;
	}


	public int[] getPlace() {
		return place;
	}


	public void setPlace(int[] place) {
		this.place = place;
	}


	public ArrayList<Record> getTables() {
		return table;
	}


	public void setTables(ArrayList<Record> tables) {
		this.table = tables;
	}
	
	public int getCell(int i) {
		return place[i];
	}

}
