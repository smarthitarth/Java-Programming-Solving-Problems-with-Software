/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser){
            if (largestSoFar == null){
                largestSoFar = currentRow;
            }else{
               double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
               double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
               if (currentTemp > largestTemp){
                   largestSoFar = currentRow;
               }
            }
        }
        return largestSoFar;
            //If largestSoFar is nothing

            //Otherwise

                //Check if currentRow’s temperature > largestSoFar’s

                    //If so update largestSoFar to currentRow

        //The largestSoFar is the answer

    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }
}
