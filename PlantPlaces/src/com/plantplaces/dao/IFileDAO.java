package com.plantplaces.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IFileDAO {

	/**
	 * Save data from an input stream to a output location
	 * @param inputStream
	 * @param file
	 * @throws IOException
	 */
	void save(InputStream inputStream, File file) throws IOException;

}