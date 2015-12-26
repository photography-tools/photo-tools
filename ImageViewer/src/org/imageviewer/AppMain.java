package org.imageviewer;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AppMain {

	private JFrame frame;

	public AppMain() {
		frame = new JFrame();
	}

	public static void main(String[] args) {
		AppMain am = new AppMain();
		am.start();
	}

	private void start() {
		GridLayout gridLayout = new GridLayout(1, 1);
		addMenu(frame);

		frame.setLayout(gridLayout);
		frame.setState(Frame.MAXIMIZED_BOTH);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		// frame.add(label1);
		// frame.add(label2);
		// frame.add(label3);
		// frame.add(label4);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addMenu(JFrame frame) {
		JMenuBar menubar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit.png");

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem eMenuItem = new JMenuItem("Exit", icon);
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(event -> System.exit(0));
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(event -> this.fileOpen(event));

		file.add(openItem);
		file.add(eMenuItem);
		menubar.add(file);

		frame.setJMenuBar(menubar);
	}

	private void fileOpen(ActionEvent event) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(this.frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    File yourFolder = fc.getSelectedFile();
			System.out.println(yourFolder);
		}
	}

}
