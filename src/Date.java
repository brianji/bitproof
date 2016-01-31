
public class Date {

	int day;
	int month;
	int year;

	public Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Date(String x){
		year = Integer.parseInt(x.substring(0,x.indexOf("-")));
		x = x.substring(x.indexOf("-")+1,x.length());
		month = Integer.parseInt(x.substring(0,x.indexOf("-")));
		x = x.substring(x.indexOf("-")+1,x.length());
		day = Integer.parseInt(x);
	}

	public int getDay(){
		return day;
	}

	public int getMonth(){
		return month;
	}

	public int getYear(){
		return year;
	}

	public int compareTo(Date x){
		if(this.year != x.year){
			return x.year - this.year;
		}else if(this.month != x.month){
			return x.month - this.month;
		}else{
			return x.day - this.day;
		}
	}
	
	public String toString(){
		return month + "/"+day+"/"+year;
	}

}
