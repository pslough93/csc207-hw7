package ushahidiPackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Vector;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class UshahidiUserInterface
{
  /**
   * UshahidiUserInterface main - Our UI to guide users through the filtering of
   * UshahidiIncidents from a client. 
   * @param args
   * @throws Exception
   */
  public static void main(String args[])
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Welcome to the Ushahidi User Interface");
    pen.flush();
    pen.print("Please enter a Ushahidi Client: ");
    pen.flush(); //prints greetings, flushes to force console printing
    BufferedReader eyes = new BufferedReader(new InputStreamReader(System.in));
    String url = eyes.readLine();
    UshahidiClient client = new UshahidiWebClient(url);
    UshahidiIncident[] incidents = client.getIncidents();
    Vector<UshahidiIncident> incidentVec = new Vector<UshahidiIncident>(); 
    for (int i = 0; i < incidents.length; i++) //iterates through client incidents and adds them to the vector
      {
        incidentVec.add(incidents[i]);
      }
    pen.println("Your options to filter by are: ");
    pen.println("d - Distance from a location");
    pen.println("t - Within a certain Timeframe");
    pen.println("l - Location Name");
    pen.println("a - Whether Incident is active");
    pen.println("q - Quit filtering"); //prints command instructions
    pen.flush();
    Vector<UshahidiIncident> vec = new Vector<UshahidiIncident>(); //vec is empty and will contain filtered incidents
    while (true) //loops until user termination
      {
        pen.print("Enter Command: ");
        pen.flush();
        String command = eyes.readLine();
        switch (command) //switch statement for commands
          {
            case "d": 
              pen.print("Enter location latitude: ");
              pen.flush();
              String lat = eyes.readLine();
              double lat1 = Double.parseDouble(lat);
              pen.print("Enter location longitude: ");
              pen.flush();
              String lon = eyes.readLine();
              double lon1 = Double.parseDouble(lon);
              pen.print("Enter a distance from location (in km): ");
              pen.flush();
              String d = eyes.readLine();
              double dist = Double.parseDouble(d); //prompts user for and receives necessary variables, converting to double
              vec =
                  UshahidiExtensions.filter(incidentVec,
                                            (i) ->
                                              {
                                                return UshahidiExtensions.findDistance(lat1,
                                                                                       i.getLocation()
                                                                                        .getLatitude(),
                                                                                       lon1,
                                                                                       i.getLocation()
                                                                                        .getLongitude()) <= dist;
                                              }); //fills vec with filtered Incidents with anonymous filter
              break;
            case "t":
              pen.print("Enter number of days ago that the filter should start at: ");
              pen.flush();
              String dayString = eyes.readLine();
              int days = Integer.parseInt(dayString);
              LocalDateTime end = LocalDateTime.now();
              LocalDateTime start = end.minusDays(days); //gets necessary date info from user
              vec =
                  UshahidiExtensions.filter(incidentVec,
                                            (i) ->
                                              {
                                                return i.getDate()
                                                        .isAfter(start)
                                                       && i.getDate()
                                                           .isBefore(end);
                                              });//fills vec with filtered incidents with anonymous filter

              break;
            case "l":
              pen.print("Enter location name: ");
              pen.flush();
              String location = eyes.readLine(); //gets location name for comparison
              vec =
                  UshahidiExtensions.filter(incidentVec,
                                            (i) ->
                                              {
                                                return i.getLocation()
                                                        .getName()
                                                        .equals(location);
                                              });//fills vec with filtered incidents with anonymous filter
              break;
            case "a":
              pen.print("Enter boolean for active status: ");
              pen.flush();
              String act = eyes.readLine(); //gets active status boolean
              boolean active = Boolean.parseBoolean(act);
              vec =
                  UshahidiExtensions.filter(incidentVec, (i) ->
                                              {
                                                return i.getActive() == active;
                                              });//fills vec with filtered incidents with anonymous filter
              break;
            case "q":
              pen.println("Terminating procedure");
              pen.flush(); //notifies user of termination
              break;
            default:
              pen.println("Unrecognized Command. Please enter again.");
              pen.flush(); //notifies user of invalid command
          }//switch
        if (!command.equals("q")) //if command isn't q
          {
            for (int i = 0; i < vec.size(); i++)
              {
                UshahidiExtensions.printIncident(pen, vec.get(i)); //prints filtered vector
              }//for
          }//if
        else{
          break;
        }//else
      }//while
  }//main
}//class UshahidiUserInterface
