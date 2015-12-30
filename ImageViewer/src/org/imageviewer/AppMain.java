package org.imageviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class AppMain {

	private JFrame frame;
	private Container cpane;

	public AppMain() {
		frame = new JFrame();
		cpane = frame.getContentPane();
	}

	public static void main(String[] args) {
		AppMain am = new AppMain();
		am.start();
	}

	private void start() {
		addMenu(frame);
		addStatus(cpane);

		frame.setState(Frame.MAXIMIZED_BOTH);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		// frame.add(label1);
		// frame.add(label2);
		// frame.add(label3);
		// frame.add(label4);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		mainPanel.setBackground(Color.GREEN);
		cpane.add(mainPanel, BorderLayout.CENTER);

		frame.setVisible(true);
}

	private void addStatus(Container cpane2) {
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(cpane2.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		statusPanel.setBackground(Color.WHITE);
		cpane2.add(statusPanel, BorderLayout.SOUTH);
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
		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.addActionListener(event -> this.actionOpen(event));

		file.add(openItem);
		file.add(eMenuItem);
		menubar.add(file);

		frame.setJMenuBar(menubar);
	}

	private void actionOpen(ActionEvent event) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(".")); // start at application current directory
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(this.frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		    this.onOpen(fc.getSelectedFile());
		}
	}

	private void onOpen(File dir) {
		File files[] = dir.listFiles(file -> file.getName().matches("(?i).*\\.(jpg|jpeg|png|gif|tif|tiff)$"));
		
	}

}
