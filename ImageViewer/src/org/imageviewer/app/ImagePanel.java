package org.imageviewer.app;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.imageviewer.util.ImageResize;

/*
 * @author akhilkarun
 *
 */

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	
	public void populate(List<File> images) throws IOException {
		
		GridLayout gridLayout = new GridLayout(2, 2);
		
		images.stream().forEach(file -> {
			BufferedImage img = readFile(file);
			int height = this.getHeight() / 2;
			int width = this.getWidth() / 2;
			
			// crunch horizontally to maintain aspect ratio
			width = (int) Math.min(width, height * (img.getWidth() * 1.0 / img.getHeight()));
			
			ImageIcon icon = new ImageIcon(ImageResize.resizeImageWithHint(img,height, width));
			JLabel label = new JLabel(icon);
			this.add(label);
		});

		this.setLayout(gridLayout);

	}

	private BufferedImage readFile(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}