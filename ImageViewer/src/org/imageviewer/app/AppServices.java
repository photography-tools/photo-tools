package org.imageviewer.app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AppServices {
	
	private IAppUI ui;

	public AppServices(IAppUI ui) {
		this.ui = ui;
	}

	public void onOpen(File dir) {
		File files[] = dir.listFiles(file -> file.getName().matches("(?i).*\\.(jpg|jpeg|png|gif|tif|tiff)$"));

		ui.setStatus(String.format("Found %d images.", files.length));
		
		try {
			ui.setWorkingImages(Arrays.asList(files));
		} catch (IOException e) {
			ui.setStatus("ERROR: " + e.toString());
		}
	}

}
