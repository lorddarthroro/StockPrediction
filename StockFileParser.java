import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StockReader {
  public static void main(String[] args) throws FileNotFoundException {
	File original = new File("C:/Users/ahmad/Desktop/fiveYearStockData.txt");
	Scanner scnr = new Scanner(original);
	ArrayList<String[]> d2013 = new ArrayList<String[]>();
	ArrayList<String[]> d2018 = new ArrayList<String[]>();
	ArrayList<String[]> d2017 = new ArrayList<String[]>();
	ArrayList<String[]> d2016 = new ArrayList<String[]>();
	ArrayList<String> companies = new ArrayList<String>();
	while(scnr.hasNextLine()) {
		String str = scnr.nextLine();
		String[] s = str.split(",");
		if(s[0].equals("2013-02-08")) {
			d2013.add(s);
			companies.add(s[s.length-1]);
		}
		else if(s[0].equals("2018-02-01")) {
			if(companies.contains(s[s.length-1])) {
				d2018.add(s);
			}
		}
		else if(s[0].equals("2017-05-04")) {
			if(companies.contains(s[s.length-1])) {
				d2017.add(s);
			}
		}
		else if(s[0].equals("2016-02-08")) {
			if(companies.contains(s[s.length-1])) {
				d2016.add(s);
			}
		}

	}
	ArrayList<String> closeDelta = new ArrayList<String>();
	ArrayList<String> fiveYearDelta = new ArrayList<String>();
	ArrayList<String> vol2017 = new ArrayList<String>();
	ArrayList<String> profit = new ArrayList<String>();
	ArrayList<String> BuyOrNot = new ArrayList<String>();
	//0 = date, 1 = open, 2 = high, 3 = low, 4 = close, 5 = volume, 6 = Name
	for(int i = 0; i < d2018.size(); i++) {
		String[] s16 = d2016.get(i);
		String[] s17 = d2017.get(i);
		String[] s18 = d2018.get(i);
		String[] s13 = d2013.get(i);
		Double d16 = Double.parseDouble(s16[4]);
		Double d17 = Double.parseDouble(s17[4]);
		Double d18 = Double.parseDouble(s18[4]);
		Double d13 = Double.parseDouble(s13[4]);
		closeDelta.add(String.valueOf(d17-d16));
		vol2017.add(s18[5]);
		profit.add(String.valueOf(d18-d17));
		fiveYearDelta.add(String.valueOf(d17-d13));
		if(d18-d17 > 0.0) {
			BuyOrNot.add("1");
		}
		else {
			BuyOrNot.add("0");
		}
		
	}

//	CATEGORIES FOR CSV: close delta from 2015 to 2016, volume 2016, profit?
	//ADD: 
    try (PrintWriter writer = new PrintWriter("CleanStockData.csv")) {

      StringBuilder sb = new StringBuilder();
      sb.append("yearDelta");
      sb.append(',');
      sb.append("fourYearDelta");
      sb.append(',');
      sb.append("Volume");
      sb.append(',');
      sb.append("nextYearProfit");
      sb.append(',');
      sb.append("BuyOrNot");
      sb.append('\n');
      for(int i = 0; i < closeDelta.size(); i++) {
    	  sb.append(closeDelta.get(i));
    	  sb.append(',');
    	  sb.append(fiveYearDelta.get(i));
    	  sb.append(',');
    	  sb.append(vol2017.get(i));
    	  sb.append(',');
    	  sb.append(profit.get(i));
    	  sb.append(',');
    	  sb.append(BuyOrNot.get(i));
    	  sb.append('\n');
      }
      


      writer.write(sb.toString());

      System.out.println("done!");

    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

  }
}
