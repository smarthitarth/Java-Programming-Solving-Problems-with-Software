
/**
 * Write a description of Countries here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Countries {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String country = "";
        System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "diamond", "gold");
        parser = fr.getCSVParser();
        System.out.println("Number of countries to export gold: " + numberofExporteres(parser, "gold"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
            String data = record.get("Country");
            if (data.contains(country)){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                String info = country + ": " + exports + ": " + value;
                return info;
            } 
        }
        return "Not Found";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2){
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportitem1)){
                   if (export.contains(exportitem2)){
                       String country = record.get("Country");
                       System.out.println(country);
                    }
            }
        }
    }
    
    public int numberofExporteres(CSVParser parser, String exportitem){
        int count = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportitem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters (CSVParser parser, String value){
        for (CSVRecord record : parser){
            String val = record.get("Value (dollars)");
            String country = record.get("Country");
            if (val.length() > value.length()){
                System.out.println( country + ": " + val);
            }
        }
    }
}
