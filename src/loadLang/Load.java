package loadLang;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Load {

	
	public static String load(String path)
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("res/" + path + ".sav"));
		} catch (FileNotFoundException e) {
			System.out.println("no file");
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "null";
	}

}
