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
import javax.swing.JSlider;
import javax.swing.JScrollPane;

import org.imageviewer.api.IAppServices;
import org.imageviewer.api.IAppUI;
import org.imageviewer.app.ImagePanel;

public class AppUI implements IAppUI{

	private JFrame frame;
	private JScrollPane scrollPane;
	private ImagePanel imagePanel;
	private JSlider slider;
	private JPanel statusPanel;
	private JLabel statusLabel;
	private IAppServices services;

	public AppUI(){
		frame=new JFrame();
		imagePanel=new ImagePanel();
		scrollPane=new JScrollPane(imagePanel,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
		slider=new JSlider(0,100,0);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
		statusPanel=new JPanel();
		statusLabel=new JLabel("Application started. Use File->Open to select a working folder.");
	}

	@Override
	public void start(IAppServices svc){
		
		this.services=svc;

		addMenu(frame);
		addStatus(frame.getContentPane());

		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		scrollPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() - 128));
		frame.add(scrollPane, BorderLayout.NORTH);		
		frame.add(slider, BorderLayout.CENTER);		
    }

	private void addStatus(Container cpane){
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(cpane.getWidth(), 16));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		cpane.add(statusPanel, BorderLayout.SOUTH);
	}

	private void addMenu(JFrame frame){
		JMenuBar menubar=new JMenuBar();
		ImageIcon icon=new ImageIcon("exit.png");

		JMenu file=new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem eMenuItem=new JMenuItem("Exit", icon);
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(event -> System.exit(0));

		JMenuItem openItem=new JMenuItem("Open Folder");
		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.addActionListener(event -> this.actionOpen(event));

		file.add(openItem);
		file.add(eMenuItem);
		menubar.add(file);

		frame.setJMenuBar(menubar);
	}

	private void actionOpen(ActionEvent event){
		JFileChooser fc=new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(".")); // start at application
														// current directory
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Select Folder");
//		fc.setApproveButtonText("Select");
//		System.out.println(fc.getApproveButtonText());
		fc.setApproveButtonToolTipText("Open images in this folder");
		int returnVal=fc.showSaveDialog(this.frame);
		if (returnVal == JFileChooser.APPROVE_OPTION){
			services.onOpen(fc.getSelectedFile());
		}
	}

	@Override
	public void setStatus(String status){
		this.statusLabel.setText(status);
	}

	@Override
	public void setWorkingImages(List<File> images) throws IOException{
		int size=slider.getValue()==100?5
			:slider.getValue()>=75?6
			:slider.getValue()>=50?7
			:slider.getValue()>=25?8
			:9;
		imagePanel.populate(images,size,slider);
		imagePanel.revalidate();
	}
}
