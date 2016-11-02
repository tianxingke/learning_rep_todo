package learning.demo.login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Utlis {

	public static void writeLog(String info, File file) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file,true));
			osw.write(info);
			osw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
