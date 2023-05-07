package Utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperity {
	public FileInputStream stream;
	public Properties readPropertyFile(String filePath) throws IOException {
		// TODO Auto-generated method stub
		Properties property = new Properties();
		stream = new FileInputStream(System.getProperty("user.dir") + filePath);
		property.load(stream);
		return property;
	}

}
