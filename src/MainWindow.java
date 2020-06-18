import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import java.awt.BorderLayout;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MainWindow {

	private JFrame frame;
	private JTextField txtName;
	private JTree tree;
	private JTextField textInfo;
	private JButton btnCut;
	private JButton buttonCreateFolder;
	private JButton buttonCopy;
	private JButton buttonPaste;
	private JButton btnCreateFile;
	private JButton btnDelete;
	MyJPanel panel;
	PhysicalMemory fizMemory;
	private fileManager fileManager;
	private JTextField textFieldSizeFile;
	private JLabel lblSizeFile;	
	private DefaultMutableTreeNode treeFile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		drawMemory();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.getContentPane().setLayout(null);
		
		//поле для имени файла или папки
		txtName = new JTextField();
		txtName.setEnabled(true);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setText("new");
		txtName.setBounds(263, 11, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Имя файла");
		lblName.setEnabled(true);
		lblName.setBounds(170, 14, 90, 14);
		frame.getContentPane().add(lblName);
		
		//поле для размера файла или папки
		textFieldSizeFile = new JTextField();
		textFieldSizeFile.setText("10");
		textFieldSizeFile.setBounds(263, 50, 86, 20);
		frame.getContentPane().add(textFieldSizeFile);
		textFieldSizeFile.setColumns(10);
		
		lblSizeFile = new JLabel("Размер файла:");
		lblSizeFile.setBounds(170, 40, 90, 31);
		frame.getContentPane().add(lblSizeFile);
		
		btnCreateFile = new JButton("Создать файл");
		btnCreateFile.setEnabled(true);
		btnCreateFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//создаём файл заданного размера
				fileManager.CreateNewFile(false,txtName.getText(),Integer.parseInt(textFieldSizeFile.getText()));
				// делаем выделенный файл корневым
				startUpdateTree(fileManager.getRootFile().getChildren());// как работает?
				textInfo.setText("Файл" + txtName.getText() + "создан");
				panel.repaint();
			}
		});
		btnCreateFile.setBounds(184, 80, 165, 23);
		frame.getContentPane().add(btnCreateFile);
		
		buttonCreateFolder = new JButton("Создать папку");
		buttonCreateFolder.setEnabled(true);
		buttonCreateFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileManager.CreateNewFile(true,txtName.getText(),Integer.parseInt(textFieldSizeFile.getText()));
				startUpdateTree(fileManager.getRootFile().getChildren());
				textInfo.setText("Папка" + txtName.getText() + "создана");
				panel.repaint();
			}
		});
		buttonCreateFolder.setBounds(184, 110, 165, 23);
		frame.getContentPane().add(buttonCreateFolder);	
		
		buttonCopy = new JButton("Копировать");
		buttonCopy.setEnabled(true);
		buttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textInfo.setText("Скопировано: "+ fileManager.Copy());
			}
		});
		buttonCopy.setBounds(184, 150, 165, 23);
		frame.getContentPane().add(buttonCopy);
		
		buttonPaste = new JButton("Вставить");
		buttonPaste.setEnabled(true);
		buttonPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					fileManager.paste();
					startUpdateTree(fileManager.getRootFile().getChildren());
					textInfo.setText("Вставка прошла успешно");
					panel.repaint();
			}
		});
		buttonPaste.setBounds(184, 180, 165, 23);
		frame.getContentPane().add(buttonPaste);	
		
		
		btnCut = new JButton("Вырезать");
		btnCut.setEnabled(true);
		btnCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					fileManager.setForCut(null);
					startUpdateTree(fileManager.getRootFile().getChildren());
				}
			});
		btnCut.setBounds(184, 210, 165, 23);
		frame.getContentPane().add(btnCut);
		
		btnDelete = new JButton("Удалить");
		btnDelete.setEnabled(true);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					fileManager.delete();
					startUpdateTree(fileManager.getRootFile().getChildren());
					panel.repaint();
			}
		});
		btnDelete.setBounds(184, 240, 165, 23);
		frame.getContentPane().add(btnDelete);			
		
		JLabel lblInfo = new JLabel("Информация:");
		lblInfo.setEnabled(true);
		lblInfo.setBounds(184, 280, 100, 14);
		frame.getContentPane().add(lblInfo);
		
		//для вывода информации
		textInfo = new JTextField();
		textInfo.setEnabled(false);
		textInfo.setBounds(184, 300, 169, 20);
		frame.getContentPane().add(textInfo);
		textInfo.setColumns(10);
	}

	protected void drawMemory()
	{
		fizMemory = new PhysicalMemory();
		fizMemory.setStartSelectedFile(0);
		panel = new MyJPanel(fizMemory);
		panel.setBounds(360, 11, 550, 518);
		frame.getContentPane().add(panel);
		fileManager= new fileManager(fizMemory);
		panel.repaint();
		startUpdateTree(fileManager.getRootFile().getChildren());
	}

	protected void startUpdateTree(ArrayList<File> childs) {
		treeFile = new DefaultMutableTreeNode(fileManager.getRootFile());
		updateTree(treeFile,childs);
		if(!Objects.isNull(tree)) {
			frame.getContentPane().remove(tree);
		}
		tree = new JTree(treeFile);
		tree.setEnabled(true);
		tree.setShowsRootHandles(true);
		tree.setBounds(0, 0, 169, 529);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				fileManager.setSelectedNodeTree((DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent());
				fizMemory.setStartSelectedFile(fileManager.getSelected().getStartInMem());
				panel.repaint();
				System.out.println(fileManager.getSelected());
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(tree);
		tree.updateUI();
		tree.setScrollsOnExpand(true);
	}

	private void updateTree(DefaultMutableTreeNode treeFile, ArrayList<File> childs) {
		for (File file : childs) {
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file);
			treeFile.add(newNode);
			if(file.getFolder()) {
				updateTree(newNode, file.getChildren());
			}
		}		
	}

}
