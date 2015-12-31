package org.imageviewer.app;

import java.io.File;

import org.imageviewer.api.IAppUI;

public class Open_C_Temp_Test {

	public static void main(String[] args) {
		IAppUI ui = new AppUI();
		AppServices svc = new AppServices(ui);
		ui.start(svc);
		svc.onOpen(new File("c:/temp"));
	}
}
