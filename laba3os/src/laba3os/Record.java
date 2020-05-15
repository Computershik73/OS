package laba3os;

public class Record {
	 private int pageNumber; 
	 private boolean isInPhysicalMemory = false;
	 private boolean isModified = false;
	 private boolean r = false;
	 private int numberOfPhysicalPage = -1;
	  
	 public Record(int pageNumber) {
		 this.pageNumber = pageNumber;		 
	 }
	 public int getPageNumber() {
		 return pageNumber;
	 }
	 public boolean isModified() {
		 return isModified;
	 }
	 public void r(boolean r) {
		 this.isInPhysicalMemory = r;
	 }
	 public boolean r() {
		 return r;
	 }
	 public void removeFromPhysicalMemory() {
		 isInPhysicalMemory = false;
	 }
	 public void sendToPhysicalMemory() {
		 isInPhysicalMemory = true;
	 }
	 public boolean isInPhysicalMemory() {
		 return isInPhysicalMemory;
	 }
	public void setNumberOfPhysicalPage(int number) {
		numberOfPhysicalPage = number;
	}
	public int getNumberOfPhysicalPage() {
		return numberOfPhysicalPage;
	}

}