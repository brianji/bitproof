import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Gets the bounds for the data
 */
public class BoundReader {
	double minLat = Double.MIN_VALUE;
	double maxLat = Double.MAX_VALUE;
	double minLong = Double.MIN_VALUE;
	double maxLong = Double.MAX_VALUE;

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

	public Double getMinLong() {
		return minLong;
	}

	public void setMinLong(Double minLong) {
		this.minLong = minLong;
	}

	public Double getMaxLong() {
		return maxLong;
	}

	public void setMaxLong(Double maxLong) {
		this.maxLong = maxLong;
	}

	public BoundReader(String filename) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("files/"+filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line;
		try {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] data = line.split(", ");
				Double latitude = null;
				Double longitude = null;
				if(!data[1].equals(""))
				{	latitude = Double.parseDouble(data[1]);
				}
				if(!data[2].equals(""))
				{				
					longitude = Double.parseDouble(data[2]);
				}
				if(latitude != null && longitude != null){
					if (minLat > latitude) {
						minLat = latitude;
					}
					if (maxLat < latitude) {
						maxLat = latitude;
					}
					if (minLong > longitude) {
						minLong = longitude;
					}
					if (maxLong < longitude) {
						maxLong = longitude;
					}
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
