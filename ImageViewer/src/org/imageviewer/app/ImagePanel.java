package org.imageviewer.app;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.imageviewer.util.ImageResize;

/*
 * @author akhilkarun
 *
 */

public class ImagePanel extends JPanel {
	
	private static GraphicsDevice gd = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	public void populate(List<File> images) throws IOException {
		
		GridLayout gridLayout = new GridLayout(2, 2);
		
		Stream<BufferedImage> imgs = images.stream().map(file -> readFile(file));

		Stream<ImageIcon> icons = imgs.map(img -> new ImageIcon(ImageResize.resizeImageWithHint(img,
				getImageHeight(), getImageWidth())));
		
		Stream<JLabel> labels = icons.map(icon -> new JLabel(icon));
		
		labels.forEach(label -> this.add(label));
		this.setLayout(gridLayout);

	}

	private static BufferedImage readFile(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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

}