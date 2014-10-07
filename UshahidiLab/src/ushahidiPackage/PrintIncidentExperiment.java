package ushahidiPackage;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Vector;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class PrintIncidentExperiment
{
  public static void main(String[] args)
    throws Exception
  {
    // Create the output device
    PrintWriter pen = new PrintWriter(System.out, true);

    //      // A few basic incidents
    //      //UshahidiExtensions.printIncident(pen, UshahidiUtils.SAMPLE_INCIDENT);
    //      //UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());
    //      //UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());
    //
    //      // A newly created incident
    //      UshahidiLocation location = new UshahidiLocation(2, "Grinnell", 45.4, 32.4);
    //      UshahidiCategory[] categories = {new UshahidiCategory(1, "cat 1"), new UshahidiCategory(2, "cat 2")};
    //      UshahidiIncident myIncident = new UshahidiIncident(1, "Test", java.time.LocalDateTime.now(), 
    //                                                         location, "Description" , categories);
    //      UshahidiExtensions.printIncident(pen, myIncident);
    //
    //      // One from a list
    //      UshahidiClient client = UshahidiUtils.SAMPLE_CLIENT;
    //      UshahidiExtensions.printIncident(pen, client.nextIncident());

    // One that requires connecting to the server
    UshahidiClient webclient =
        new UshahidiWebClient("http://ushahidi1.grinnell.edu/sandbox/");
    //UshahidiClient testClient = UshahidiExtensions.makeTestClient();
    //UshahidiExtensions.printIncident(pen, webclient.nextIncident());
    LocalDateTime start = LocalDateTime.of(2014, 9, 1, 12, 0);
    LocalDateTime end = LocalDateTime.now();
    UshahidiIncident[] incidents =
        UshahidiExtensions.IncidentsBetweenDates2(start, end, webclient);
    Vector<UshahidiIncident> vec = new Vector<UshahidiIncident>();
    for (int i = 0; i < incidents.length; i++)
      {
        vec.add(incidents[i]);
      }
    //Vector<UshahidiIncident> local = UshahidiExtensions.incidentsWithinDistance(vec, 41.748, -92.720, 100);
    Vector<UshahidiIncident> local =
        UshahidiExtensions.filter(vec,
                                  (i) ->
                                    {
                                      return UshahidiExtensions.findDistance(41.748,
                                                                             i.getLocation()
                                                                              .getLatitude(),
                                                                             -92.720,
                                                                             i.getLocation()
                                                                              .getLongitude()) < 4;});
    //UshahidiIncident[] arr = UshahidiExtensions.IncidentsBetweenDates2(start, end, webclient);
    //UshahidiIncident[] arr = UshahidiExtensions.IncidentsBetweenDates2(start, end, webClient);
    for (int i = 0; i < local.size(); i++)
      {
        UshahidiExtensions.printIncident(pen, local.get(i));
      }
  } // main(String[])
}
