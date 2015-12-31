package org.imageviewer.app;

public class AppMain {

	public static void main(String[] args) {
		AppUI ui = new AppUI();
		AppServices svc = new AppServices(ui);
		ui.start(svc);
	}
	
}
