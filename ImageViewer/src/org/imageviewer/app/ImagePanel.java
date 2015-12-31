package org.imageviewer.app;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.imageviewer.util.ImageResize;

/*
 * @author akhilkarun
 *
 */

public class ImagePanel extends JPanel {
	// uncomment this sample value only for testing
	//private static final String IMAGE = "/Users/akhilkarun/Desktop/download.jpeg";
	
	static GraphicsDevice gd = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	public ImagePanel(String image1, String image2, String image3, String image4) throws IOException {
		
		GridLayout gridLayout = new GridLayout(2, 2);
		BufferedImage img1 = ImageIO.read(new File(image1));
		BufferedImage img2 = ImageIO.read(new File(image2));
		BufferedImage img3 = ImageIO.read(new File(image3));
		BufferedImage img4 = ImageIO.read(new File(image4));
		
		ImageIcon icon1 = new ImageIcon(ImageResize.resizeImageWithHint(img1,
				getImageHeight(), getImageWidth()));
		ImageIcon icon2 = new ImageIcon(ImageResize.resizeImageWithHint(img2,
				getImageHeight(), getImageWidth()));
		ImageIcon icon3 = new ImageIcon(ImageResize.resizeImageWithHint(img3,
				getImageHeight(), getImageWidth()));
		ImageIcon icon4 = new ImageIcon(ImageResize.resizeImageWithHint(img4,
				getImageHeight(), getImageWidth()));
		
		
		JLabel label1 = new JLabel(icon1);
		JLabel label2 = new JLabel(icon2);
		JLabel label3 = new JLabel(icon3);
		JLabel label4 = new JLabel(icon4);

		add(label1);
		add(label2);
		add(label3);
		JFrame frame = new JFrame();
		frame.setLayout(gridLayout);
		frame.setState(Frame.MAXIMIZED_BOTH);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(label4);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private static int getImageWidth() {
		return getScreenWidth() / 2;
	}

	private static int getScreenWidth() {
		return gd.getDisplayMode().getWidth();
	}

	private static int getImageHeight() {
		return getScreenHeight() / 2;
	}

	private static int getScreenHeight() {
		return gd.getDisplayMode().getHeight();
	}
//uncomment this class for testing 
//	public static void main(String avg[]) throws IOException {
//
//		//DisplayImage abc = new DisplayImage(IMAGE,IMAGE,IMAGE,IMAGE);
//	}
}