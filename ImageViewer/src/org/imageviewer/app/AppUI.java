package org.imageviewer.app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

import org.imageviewer.api.IAppServices;
import org.imageviewer.api.IAppUI;

public class AppUI implements IAppUI {

	private JFrame frame;
	private ImagePanel centerPanel;
	private JLabel statusLabel;
	private IAppServices services;

	public AppUI() {
		frame = new JFrame();
		centerPanel = new ImagePanel();
		statusLabel = new JLabel("Application started. Use File->Open to select a working folder.");
	}

	@Override
	public void start(IAppServices svc) {
		
		this.services = svc;

		addMenu(frame);
		addStatus(frame.getContentPane());

		frame.setState(Frame.MAXIMIZED_BOTH);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		centerPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		frame.setVisible(true);
	}

	private void addStatus(Container cpane) {
		JPanel statusPanel = new JPanel();

		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(cpane.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		cpane.add(statusPanel, BorderLayout.SOUTH);
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
		fc.setCurrentDirectory(new java.io.File(".")); // start at application
														// current directory
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showSaveDialog(this.frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			services.onOpen(fc.getSelectedFile());
		}
	}

	@Override
	public void setStatus(String status) {
		this.statusLabel.setText(status);
	}

	@Override
	public void setWorkingImages(List<File> images) throws IOException {
		centerPanel.populate(images);
		centerPanel.revalidate();
	}

}
