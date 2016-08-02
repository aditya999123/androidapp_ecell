package app.startups.nitrr.ecell.ecellapp.events_json.view;

/**
 * Created by Iket on 7/27/2016.
 */
public class EventsData {

    public String eventName,description,date,time,venue;
   // private int img;



    public EventsData(String eventName, int img, String date, String time, String venue, String description) {
        this.eventName = eventName;
       // this.img=img;
        this.date=date;
        this.venue=venue;
        this.description=description;
        this.time=time;


    }
    public EventsData() {
    }



}
