package gzip;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Ignore;
import org.junit.Test;

public class TestMain {


	@Test
	public void gzip() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("E:\\cache\\test\\ffff.txt"));
		BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("E:\\cache\\test\\ffff.gz")));
		System.out.println("write file");
		int c ;
		while((c=in.read())!=-1) {
			out.write(c);
		}
		in.close();
		out.close();
		
	}
	
	@Test
	public void unGzip() throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("E:\\cache\\test\\ffff.gz"))));
		System.out.println("readfile");
		String s ;
		while((s = reader.readLine()) != null) {
			System.out.println(s);
		}
		reader.close();
	}
}