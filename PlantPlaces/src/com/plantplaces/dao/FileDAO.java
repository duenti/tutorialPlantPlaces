package com.plantplaces.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Named;

import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * Re-Usable file implementation.
 * @author neli
 *
 */
@Named
public class FileDAO implements IFileDAO {
//Named permite que a classe seja injetada
	
	/* (non-Javadoc)
	 * @see com.plantplaces.dao.IFileDAO#save(java.io.InputStream, java.io.File)
	 */
	@Override
	public void save(InputStream inputStream, File file) throws IOException{
		//We are preparing an output stream so that we can write data to the specified file
		OutputStream output = new FileOutputStream(file);
		
		//Copy the input stream to the output location
		IOUtils.copy(inputStream, output);
	}
}
