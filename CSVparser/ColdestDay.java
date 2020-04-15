
/**
 * Write a description of ColdestDay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class ColdestDay {
    public CSVRecord coldestHourinFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser){
             coldestSoFar = coldestOfTwo(coldestSoFar, currentRow);          
        }
        return coldestSoFar;
    }
    
    public void testColdesrHourFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourinFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF")+ " at "+ coldest.get("DateUTC"));
    }
    
    public File fileWithColdestTemperature(){
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourinFile(fr.getCSVParser());
            if (coldestSoFar == null){
                coldestSoFar = currentRow;
                coldestFile = f;
            }else{
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if (currentTemp == -9999){
                    continue;
                }
                if (currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                    coldestFile = f;
                }    
            }          
        }
        return coldestFile;
    }    
    
    public CSVRecord coldestOfTwo(CSVRecord coldestSoFar, CSVRecord currentRow){
        if (coldestSoFar == null){
                coldestSoFar = currentRow;
            }else{
                
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                if (currentTemp == -9999){
                    //continue;
                }
                if (currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                }    
             }   
             return coldestSoFar;
    }      
    public void testFileWithColdestTemperature(){
        File coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest file is: " + coldestFileName.getName());
        FileResource fr = new FileResource(coldestFileName);  
        CSVRecord coldest = coldestHourinFile(fr.getCSVParser());
        System.out.println("All the temp of coldest day: ");
        for(CSVRecord current : fr.getCSVParser()){
            System.out.println(current.get("DateUTC") +"  "+ current.get("TemperatureF"));
        }
        System.out.println("Coldest temperature on coldest day was " + coldest.get("TemperatureF")+ " at "+ coldest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }else{
                double currentHumi = 0;
                double lowestHumi = 0;
                if (currentRow.get("Humidity").equals("N/A")){
                   currentHumi = 10000;
                   lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
                }else{
                lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
                currentHumi = Double.parseDouble(currentRow.get("Humidity"));}
                
                if (currentHumi < lowestHumi){
                    lowestSoFar = currentRow;
                }    
             }             
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + lowest.get("Humidity")+ " at "+ lowest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        //File coldestFile = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }else{
                double currentHumi = 0;
                double lowestHumi = 0;
                if (currentRow.get("Humidity").equals("N/A")){
                    currentHumi = 10000;
                    lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
                }else{
                lowestHumi = Double.parseDouble(lowestSoFar.get("Humidity"));
                currentHumi = Double.parseDouble(currentRow.get("Humidity"));}
                if (currentHumi < lowestHumi){
                    lowestSoFar = currentRow;
                }    
             }     
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity")+ " at "+ lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sum = 0, count = 0;
        for (CSVRecord current : parser){
            double temp = Double.parseDouble(current.get("TemperatureF"));
            sum += temp;
            count ++;
        }
        return sum/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Avg Temperature is " + avg);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int hum){
        double sum = 0, count = 0;
        for (CSVRecord current : parser){
            double temp = Double.parseDouble(current.get("TemperatureF"));
            if (Integer.parseInt(current.get("Humidity"))>=hum){
                sum += temp;
                count ++;
            }
        }
        return sum/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        System.out.println("Avg Temperature is when high humidity is " + avg);
    }
}
