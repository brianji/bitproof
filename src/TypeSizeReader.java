

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Gets the top types and sizes of the data
 */
public class TypeSizeReader {

	private Map<String, Integer> types;
	private Map<String, Integer> sizes;
	
	public TypeSizeReader(String filename) {
		this.types = new HashMap<String, Integer>();
		this.sizes = new HashMap<String, Integer>();

		BufferedReader brType;
		try {
			brType = new BufferedReader(new FileReader("files/"+filename + ".type"));
			BufferedReader brSize = new BufferedReader(new FileReader("files/"+filename + ".size"));
			
			populate(types, brType);
			populate(sizes, brSize);
			
			brType.close();
			brSize.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<String, Integer> getTypes() {
		return types;
	}

	public void setTypes(Map<String, Integer> types) {
		this.types = types;
	}

	public Map<String, Integer> getSizes() {
		return sizes;
	}

	public void setSizes(Map<String, Integer> sizes) {
		this.sizes = sizes;
	}

	private void populate(Map<String, Integer> map, BufferedReader br) throws IOException {
		int count = 1;
		String line;
		while((line = br.readLine()) != null) {
			map.put(line, count);
			count++;
		}
	}
}
