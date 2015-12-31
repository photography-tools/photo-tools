package org.imageviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
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
	private JPanel centerPanel;
	private JLabel statusLabel;

	public AppMain() {
		frame = new JFrame();
		centerPanel = new JPanel();
		statusLabel = new JLabel("started");
	}

	public static void main(String[] args) {
		AppMain am = new AppMain();
		am.start();
	}

	private void start() {
		addMenu(frame);
		addStatus(frame.getContentPane());

		frame.setState(Frame.MAXIMIZED_BOTH);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		centerPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		frame.setVisible(true);
}

	private void addStatus(Container cpane2) {
		JPanel statusPanel = new JPanel();

		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(cpane2.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
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
		this.statusLabel.setText(String.format("Found %d images.", files.length));
	}

}
