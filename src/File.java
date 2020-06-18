import java.util.ArrayList;

public class File implements Cloneable {
	private String name;
	private File parrent;
	private boolean folder; // �������� ��������� ��� ���
	private ArrayList<File> children;
	private int startInMem = -1; // ������ � ������
	private int size = -1; // ������

	// ������ ����
	public File(String name, File parrent, Boolean folder) {
		this.name = name;
		this.parrent = parrent;
		this.folder = folder;
		if (folder) {
			children = new ArrayList<File>();
		}
	}

	// �������� ����
	public File(File forCopyFile, File parrent) {
		this.parrent = parrent;
		this.name = forCopyFile.getName();
		this.folder = forCopyFile.getFolder();
		if (folder) {
			children = forCopyFile.getChildren();
		}
	}

	private File() {

	}

	public File clone() throws CloneNotSupportedException {
		File newFile = new File();
		newFile.setSize(size);
		newFile.setName(new String(name));
		newFile.setFolder(folder);
		if (folder) {
			ArrayList<File> childs = new ArrayList<File>();
			for (File file : this.children) {
				childs.add(file.clone());
			}
			newFile.setChildren(childs);
		}
		return newFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getParrent() {
		return parrent;
	}

	public void setParrent(File parrent) {
		this.parrent = parrent;
	}

	public Boolean getFolder() {
		return folder;
	}

	public void setFolder(Boolean folder) {
		this.folder = folder;
	}

	public void setChildren(ArrayList<File> childs) {
		this.children = childs;
	}

	public String toString() {
		return name;
	}

	public ArrayList<File> getChildren() {
		return children;
	}

	public int getStartInMem() {
		return startInMem;
	}

	public void setStartInMem(int startInMem) {
		this.startInMem = startInMem;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
