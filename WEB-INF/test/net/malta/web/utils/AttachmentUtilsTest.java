
package net.malta.web.utils;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.apache.struts.upload.FormFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class AttachmentUtilsTest {

	private String absoluteJPGFilePath = "C:\\tmp\\index.jpg";

	private String absoluteNOTEXISTFilePath = "C:\\tmp\\aaa.jpg";

	private File file;

	@Mocked
	private FormFile formFile;

    @Before
    public void setUp() {
    	// ローカルに適当なファイルを配置して実施
    }

    @Test
    public void testIsImage1() {
    	assertTrue(AttachmentUtils.isImage("sample/path/test.TIF"));
    }

    @Test
    public void testIsImage2() {
    	assertFalse(AttachmentUtils.isImage("sample/path/test.txt"));
    }

    @Test
    public void testGetImageExts() {
    	assertNotNull(AttachmentUtils.getImageExts());
    }

    @Test
    public void GetSuffix1() {
    	AttachmentUtils.getSuffix(null);
    }

    @Test
    public void GetSuffix2() {
    	AttachmentUtils.getSuffix("sample/path/test.txt");
    }

    @Test
    public void GetSuffix3() {
    	AttachmentUtils.getSuffix("test");
    }

    @Test
    public void GetPreffix1() {
    	AttachmentUtils.getPreffix(null);
    }

    @Test
    public void GetPreffix2() {
    	AttachmentUtils.getPreffix("sample/path/test.txt");
    }

    @Test
    public void GetPreffix3() {
    	AttachmentUtils.getPreffix("test");
    }

    @Test
    public void testSetImageExts() {
    	AttachmentUtils.setImageExts(new String[] {".jpg",".gif",".png",".eps",".ai",".svg",".mpg",".psd",".tiff",".tif",".bmp",".TIF"});
    }

    @Test
    public void testCreateDbFileFromFile1() {

    	file = new File(absoluteJPGFilePath);

    	AttachmentUtils.createDbFileFromFile(file);
    }

    @Test
    public void testCreateDbFileFromFile2() {

    	file = new File(absoluteNOTEXISTFilePath);

    	AttachmentUtils.createDbFileFromFile(file);
    }

    @Test
    public void testCreateDbFileFromFile3() {

    	new MockUp<BufferedOutputStream> () {
    		@Mock public void write(int b) throws IOException {
    			throw new IOException();
    		}
		};

    	file = new File(absoluteJPGFilePath);

    	AttachmentUtils.createDbFileFromFile(file);
    }

    @Test
    public void testCreateByteArrayFromInputStream1() {

    	new MockUp<FormFile> () {
    		@Mock public InputStream getInputStream() throws FileNotFoundException, IOException {
    			return new FileInputStream(file);
    		}
		};

    	file = new File(absoluteJPGFilePath);

    	AttachmentUtils.createDbFileFromFormFile(formFile);
    }

    @Test
    public void testCreateByteArrayFromInputStream2() {

    	new MockUp<FormFile> () {
    		@Mock public InputStream getInputStream() throws FileNotFoundException, IOException {
    			throw new FileNotFoundException();
    		}
		};

    	file = new File(absoluteNOTEXISTFilePath);

    	AttachmentUtils.createDbFileFromFormFile(formFile);
    }

    @Test
    public void testCreateByteArrayFromInputStream3() {

    	new MockUp<FormFile> () {
    		@Mock public InputStream getInputStream() throws FileNotFoundException, IOException {
    			return new FileInputStream(file);
    		}
		};

		new MockUp<BufferedOutputStream> () {
    		@Mock public void write(int b) throws IOException {
    			throw new IOException();
    		}
		};

    	file = new File(absoluteJPGFilePath);

    	AttachmentUtils.createDbFileFromFormFile(formFile);
    }

    @After
    public void checkExpectations() {
    }
}
