package org.imageviewer.app;

import java.io.File;

public class AppServices {
	
	private IAppUI ui;

	public AppServices(IAppUI ui) {
		this.ui = ui;
	}

	public void onOpen(File dir) {
		File files[] = dir.listFiles(file -> file.getName().matches("(?i).*\\.(jpg|jpeg|png|gif|tif|tiff)$"));
		ui.setStatus(String.format("Found %d images.", files.length));
	}

}
