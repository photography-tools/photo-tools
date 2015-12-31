package org.imageviewer.app;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IAppUI {

	void setStatus(String text);

	void setWorkingImages(List<File> asList) throws IOException;

}