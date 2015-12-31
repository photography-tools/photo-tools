package org.imageviewer.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.imageviewer.app.AppServices;

public interface IAppUI {

	void setStatus(String text);

	void setWorkingImages(List<File> asList) throws IOException;

	void start(IAppServices svc);

}
