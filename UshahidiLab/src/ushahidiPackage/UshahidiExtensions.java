package ushahidiPackage;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Predicate;

import edu.grinnell.glimmer.ushahidi.UshahidiCategory;
import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiLocation;
import edu.grinnell.glimmer.ushahidi.UshahidiTestingClient;

public class UshahidiExtensions
{
  /**
   * printIncident - Takes a single Ushahidi Incident and prints it in the 
   * specified format to the console.
   * @param pen
   * @param ui
   */
  public static void printIncident(PrintWriter pen, UshahidiIncident ui)
  {
    pen.println("Incident #: " + ui.getTitle());
    pen.println(ui.getDescription());
    pen.println("Location: " + ui.getLocation());
    pen.println("Status: (" + ui.getMode() + ", " + ui.getActive() + ", "
                + ui.getVerified() + ")");
  }//printIncident(PrintWriter, UshahidiIncident)

  
  /**
   * IncidentsBetweenDates - Takes two dates and a UshahidiClient and prints all
   * UshahidiIncidents on the client that falls between the two dates
   * @param pen
   * @param start
   * @param end
   * @param client
   * @throws Exception
   * Preconditions - For proper operation, start date should come chronologically
   * before the end date
   * 
   * Postconditions - Function will print all UshahidiIncidents after the start date
   * and before the end date
   */
  public static void IncidentsBetweenDates(PrintWriter pen,
                                           LocalDateTime start,
                                           LocalDateTime end,
                                           UshahidiClient client)
    throws Exception
  {
    while (client.hasMoreIncidents())
      {
        UshahidiIncident current = client.nextIncident();//current ushahidi Incident on client
        LocalDateTime date = current.getDate();
        if (date.isAfter(start) && date.isBefore(end))
          {
            printIncident(pen, current);
          }//if
      }//while
  }//IncidentsBetweenDates(PrintWriter, LocalDateTime, LocalDateTime, UshahidiClient)

  /**
   * IncidentsBetweenDates2 - Takes two dates and a UshahidiClient and returns an array of all
   * UshahidiIncidents on the client that fall between the two dates
   * @param start
   * @param end
   * @param client
   * @throws Exception
   * Preconditions - For proper operation, start date should come chronologically
   * before the end date
   * 
   * Postconditions - Function will return array of all UshahidiIncidents after the start date
   * and before the end date
   */
  public static UshahidiIncident[]
    IncidentsBetweenDates2(LocalDateTime start, LocalDateTime end,
                           UshahidiClient client)
      throws Exception
  {
    ArrayList<UshahidiIncident> results = new ArrayList<UshahidiIncident>(); //create an arrayList to store Incidents
    UshahidiIncident[] incidents = client.getIncidents(); //gets incidents from client
    for (UshahidiIncident i : incidents) //goes through each incident
      {
        LocalDateTime date = i.getDate();
        if (date.isAfter(start) && date.isBefore(end))
          {
            results.add(i);//adds to arrayList if its between the dates
          }//if
      }//for
    UshahidiIncident[] returnArray = new UshahidiIncident[results.size()];//makes an array of UshahidiIncidents
    for (int j = 0; j < results.size(); j++)
      {
        returnArray[j] = results.get(j);//unloads arrayList into returnArray
      }//for
    return returnArray;
  }//IncidentsBetweenDates2(LocalDateTime, LocalDateTime, UshahidiClient)
  
  /**
   * incidentsWithinDistance - takes a vector of UshahidiIncidents and returns a new vector
   * containing the incidents with a distance from the given location
   * @param vec
   * @param lat
   * @param lon
   * @param distance
   * @return
   * Preconditions - Latitude and Longitude must be in degree format.
   * Postconditions - Will return a vector of all Incidents within a distance of the
   * given location.
   */
  public static Vector<UshahidiIncident>
    incidentsWithinDistance(Vector<UshahidiIncident> vec, double lat,
                            double lon, double distance)
  {
    Vector<UshahidiIncident> returnVec = new Vector<UshahidiIncident>(); //makes new vector
    for (int i = 0; i < vec.size(); i++) //iterates through input list
      {
        UshahidiLocation currentLocation = vec.get(i).getLocation();
        if (currentLocation != null)
          {
            double currentLat = currentLocation.getLatitude();
            double currentLon = currentLocation.getLongitude();
            if(findDistance(lat, currentLat, lon, currentLon) <= distance){
              returnVec.add(vec.get(i)); //adds current Incident to vector if it is within the given distance
            }//if
          }//if
      }//for
    return returnVec;
  }//incidentsWithinDistance(Vector<UshahidiIncident>, double, double, double)
  
  /**
   * makeTestClient - Creates 12 generic fake UshahidiIncidents for testing purposes
   * and returns a testingClient containing them.
   * @return
   */

  public static UshahidiTestingClient makeTestClient()
  {
    UshahidiTestingClient testClient = new UshahidiTestingClient();
    UshahidiCategory[] categories =
        { new UshahidiCategory(1, "cat 1"), new UshahidiCategory(2, "cat 2") };
    String[] places =
        { "Grinnell", "Chicago", "New York", "Los Angeles", "Des Moines",
         "Highland Park", "Pyongyang", "Tokyo", "Dining Hall", "Noyce",
         "Haines Hall", "Smith Hall" };
    for (int i = 0; i < 12; i++)
      {
        LocalDateTime time = LocalDateTime.now().minusDays(7 * i); //separates each date by 7 days
        UshahidiLocation loc = new UshahidiLocation(i, places[i]);
        UshahidiIncident incident =
            new UshahidiIncident(i, "Test" + i, time, loc, "Description" + i,
                                 categories); //creates new incident
        testClient.addIncident(incident);//adds it to the client
      }//for
    return testClient;
  }//makeTestClient()
  
  /**
   * filter - A generic filter that takes in a vector of UshahidiIncidents and returns a vector of 
   * UshahidiIncidents that pass a given predicate test
   * @param vec
   * @param pred
   * @return
   * Precondition - Properly formatted predicate must have a test method that returns a boolean
   * Postcondition - Will return a vector of Incidents that pass the predicate test.
   */
  public static Vector<UshahidiIncident> filter(Vector<UshahidiIncident> vec, Predicate<? super UshahidiIncident> pred){
    Vector<UshahidiIncident> returnVec = new Vector<UshahidiIncident>();
    for(int i = 0; i < vec.size(); i++){ //iterates through vec
      UshahidiIncident current = vec.get(i);
      if(pred.test(current)){
        returnVec.add(current); //adds to return vector if the incident passes the test
      }//if
    }//for
    return returnVec;
  }//filter(Vector<UshahidiIncident>, Predicate<? super UshahidiIncident>)

  
  /**
   * findDistance - Uses procedure from the citation below to calculate the distance (in km)
   * between a given set of latitude and longitude values.
   * Citation for procedure: http://andrew.hedges.name/experiments/haversine/
   * @param lat1
   * @param lat2
   * @param lon1
   * @param lon2
   * @return
   */
  public static double findDistance(double lat1, double lat2, double lon1,
                                    double lon2)
  {
    double R = 6371.0; //average Radius of the Earth in kilometers
    double dlat = lat2 - lat1;
    double dlon = lon2 - lon1;
    double a =
        Math.sin(dlat / 2)*Math.sin(dlat/2) + Math.cos(lat1) * Math.cos(lat2)
            * Math.sin(dlon / 2)*Math.sin(dlon/2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R*c;
  }//findDistance(double, double, double, double)
}//class UshahidiExtensions
