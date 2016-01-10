package org.imageviewer.app;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.imageviewer.util.ImageResize;
import org.imageviewer.util.WrapLayout;

/*
 * original @author akhilkarun
 * heavily modified*
 *
 */

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	
	int height;
	int width;
	boolean click=true;
	Component[] components;
	ImagePanel container;
	ImagePanel image;
	
	public void populate(List<File> images,int sizeMod,JSlider slider) throws IOException{
		//sizeMod=(int)Math.round(sizeMod*0.5);
		images.stream().forEach(file ->{
			image=this;
			BufferedImage img=readFile(file);
			height=image.getHeight() / sizeMod;
			width=image.getWidth() / sizeMod;
			width=(int) Math.min(width, height *(img.getWidth() * 1.0 / img.getHeight()));
			
			ImageIcon icon=new ImageIcon(ImageResize.resizeImageWithHint(img,height,width));
			JLabel label=new JLabel(icon);
			
			label.addMouseListener(new MouseAdapter(){
		      public void mouseClicked(MouseEvent e){
		    	if(e.getClickCount()==2&&!e.isConsumed()&&click){
		    		e.consume();
		    		label.setText(label.getName());
		    		height=container.getHeight();
		    		width=container.getWidth();
		    		width=(int) Math.min(width, height *(img.getWidth() * 1.0 / img.getHeight()));
		    		icon.setImage(ImageResize.resizeImageWithHint(img,height,width));
		    		click=!click;
		    		for(int i=0;i<components.length;i++){
		    	        if(components[i]==label){
		    	        	components[i].setVisible(true);
		    	        }
		    	        else{
		    	        	components[i].setVisible(false);
		    	        }
		    	    }
		    		
		      	}
		    	if(e.getClickCount()==2&&!e.isConsumed()&&!click){
		    		e.consume();	    				    	  
		    		label.setText("");
		    		int size=slider.getValue()==100?5
	    				:slider.getValue()>=75?6
						:slider.getValue()>=50?7
						:slider.getValue()>=25?8
						:9;
		    		height=image.getHeight() / size;
		    		width=image.getWidth() / size;
		    		width=(int) Math.min(width, height *(img.getWidth() * 1.0 / img.getHeight()));
		    		icon.setImage(ImageResize.resizeImageWithHint(img,height,width));
		    		click=!click;
		    		for(int i=0;i<components.length;i++){components[i].setVisible(true);}
		      	}
		      }
		    });
			slider.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent event) {
			    	  int size=slider.getValue()==100?5
						:slider.getValue()>=75?6
						:slider.getValue()>=50?7
						:slider.getValue()>=25?8
						:9;
					  height=image.getHeight() / size;
					  width=image.getWidth() / size;
					  width=(int) Math.min(width, height *(img.getWidth() * 1.0 / img.getHeight()));
					  icon.setImage(ImageResize.resizeImageWithHint(img,height,width));
					  container.revalidate();
		    	  }
		    });

			this.add(label);
			components=this.getComponents();
		});

		this.setLayout(new WrapLayout());
		container=this;
	}

	private BufferedImage readFile(File file){
		try{
			return ImageIO.read(file);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}
