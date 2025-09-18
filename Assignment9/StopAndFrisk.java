import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile ( String csvFile ) {

        // DO NOT remove these two lines
        StdIn.setFile(csvFile); // Opens the file
        StdIn.readLine();       // Reads and discards the header line

        // WRITE YOUR CODE HERE
        
        String l;   
        while ((l = StdIn.readLine()) != null) {
            String[] recordEntries = l.split(",");
            int year = Integer.parseInt(recordEntries[0]);
            String description = recordEntries[2];
            Boolean arrested = recordEntries[13].equals("Y");
            Boolean frisked = recordEntries[16].equals("Y");
            String gender = recordEntries[52];
            String race = recordEntries[66];
            String location = recordEntries[71];
            Boolean in = false;
            SFYear a = null;

            for (SFYear years: database) {
            int cur = years.getcurrentYear();
            if (cur == year) {
                a = years;
                in = true;
                break;
                }
            }

            if (in == false) {
                a = new SFYear(year);
                database.add(a);
            }

            a.addRecord(new SFRecord(description, arrested, frisked, gender, race, location));
                    
        }

    }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {

        // WRITE YOUR CODE HERE
        ArrayList<SFRecord> stop = new ArrayList<SFRecord>();
        
        for (int i = 0; i < database.size(); i++) {
            if(database.get(i).getcurrentYear() == year) {
                SFYear a = database.get(i);
                for (int j = 0;j < a.getRecordsForYear().size();j++) {
                    if(a.getRecordsForYear().get(j).getRace().equals(race)) {
                        stop.add(a.getRecordsForYear().get(j));
                    }
                }
            }

        }
        return stop;
    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        // WRITE YOUR CODE HERE
        double frisked = 0.0;
        double arrested = 0.0;
        double total = 0.0;

        for (int i = 0; i < database.size(); i++) {
            if(database.get(i).getcurrentYear() == year) {
                SFYear a = database.get(i);
                total = a.getRecordsForYear().size();
                for (int j = 0;j < total;j++) {
                    if(a.getRecordsForYear().get(j).getFrisked() == true) {
                        frisked++;
                    }
                    if(a.getRecordsForYear().get(j).getArrested() == true) {
                        arrested++;
                    }
                }
            }

        }
        double[] val = {(frisked/total)*100,(arrested/total)*100};

        return val; // update the return value
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {

        // WRITE YOUR CODE HERE
        int blackF = 0;
        int blackM = 0;
        int whiteF = 0;
        int whiteM = 0;
        int black = 0;
        int white = 0;

        for (int i = 0; i < database.size(); i++) {
            if(database.get(i).getcurrentYear() == year) {
                SFYear a = database.get(i);
                for (int j = 0;j < a.getRecordsForYear().size();j++) {
                    if(a.getRecordsForYear().get(j).getRace().equals("B")) {
                        black++; 
                        if(a.getRecordsForYear().get(j).getGender().equals("F")) {
                            blackF++;
                        }
                        else if (a.getRecordsForYear().get(j).getGender().equals("M")) {
                            blackM++;
                        }
                    }
                    else if(a.getRecordsForYear().get(j).getRace().equals("W")) {
                        white++;
                        if (a.getRecordsForYear().get(j).getGender().equals("F")) {
                            whiteF++;
                        }
                        else if (a.getRecordsForYear().get(j).getGender().equals("M")) {
                            whiteM++;
                        }
                    }
                } 
            }

        }

        double[][] val = {{(double)blackF/black * 0.5 * 100,(double)whiteF/white * 0.5 * 100,((double)whiteF/white * 0.5 * 100) + (double)blackF/black * 0.5 * 100},
                        {(double)blackM/black * 0.5 * 100,(double)whiteM/white * 0.5 * 100,((double)blackM/black * 0.5 * 100) + (double)whiteM/white * 0.5 * 100}};

        return val; // update the return value
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        
        // WRITE YOUR CODE HERE
        int crime1 = 0;
        int crime2 = 0;
        int total1 = 0;
        int total2 = 0;
        double ans = 0.0;

        if (year1 >= year2) {
            return 0.0;
        }

        for (SFYear years: database) {
            if(years.getcurrentYear() == year1) {
                SFYear a = years;
                for (SFYear yearss: database) {
                    if(yearss.getcurrentYear() == year2){
                        SFYear b = yearss;
                        total1 = a.getRecordsForYear().size();
                        for (int j = 0;j < total1;j++) {
                            if(a.getRecordsForYear().get(j).getDescription().indexOf(crimeDescription) > -1) {
                                crime1++;
                                }
                            }
                            total2 = b.getRecordsForYear().size();
                        for (int z = 0;z < total2;z++) {
                            if(b.getRecordsForYear().get(z).getDescription().indexOf(crimeDescription) > -1) {
                                crime2++;
                            }

                    }
                }
            }
        }
    }

        double one = (double)crime1/total1*100;
        double two = (double)crime2/total2*100;
        double per = (two - one);
            



	return per; // update the return value
    }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {

        // WRITE YOUR CODE HERE
        int Brooklyn = 0;
        int Manhattan = 0;
        int Bronx = 0;
        int Queens = 0;
        int Staten_Island = 0;

        String ans = "";
        

        for (int i = 0; i < database.size(); i++) {
            if(database.get(i).getcurrentYear() == year) {
                SFYear a = database.get(i);
                int total = a.getRecordsForYear().size();
                for (int j = 0;j < total;j++) {
                    if(a.getRecordsForYear().get(j).getLocation().equalsIgnoreCase("Brooklyn")) {
                        Brooklyn++;
                    }
                    if(a.getRecordsForYear().get(j).getLocation().equalsIgnoreCase("Manhattan")) {
                        Manhattan++;
                    }
                    if(a.getRecordsForYear().get(j).getLocation().equalsIgnoreCase("Bronx")) {
                        Bronx++;
                    }
                    if(a.getRecordsForYear().get(j).getLocation().equalsIgnoreCase("Queens")) {
                        Queens++;
                    }
                    if(a.getRecordsForYear().get(j).getLocation().equalsIgnoreCase("Staten Island")) {
                        Staten_Island++;
                    }
                    
                }
            }

        }
        
        if (Math.max(Math.max(Math.max(Math.max(Staten_Island,Queens),Bronx),Manhattan),Brooklyn) == Brooklyn) {
            ans = "Brooklyn";
        }
        else if (Math.max(Math.max(Math.max(Math.max(Staten_Island,Queens),Bronx),Manhattan),Brooklyn) == Manhattan) {
            ans = "Manhattan";
        }
        else if (Math.max(Math.max(Math.max(Math.max(Staten_Island,Queens),Bronx),Manhattan),Brooklyn) == Bronx) {
            ans = "Bronx";
        }
        else if (Math.max(Math.max(Math.max(Math.max(Staten_Island,Queens),Bronx),Manhattan),Brooklyn) == Queens) {
            ans = "Queens";
        }
        else if (Math.max(Math.max(Math.max(Math.max(Staten_Island,Queens),Bronx),Manhattan),Brooklyn) == Staten_Island) {
            ans = "Staten Island";
        }




        return ans; // update the return value
    }

}
