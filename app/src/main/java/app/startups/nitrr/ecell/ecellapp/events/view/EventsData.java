package app.startups.nitrr.ecell.ecellapp.events.view;

/**
 * Created by Iket on 7/27/2016.
 */
public class EventsData {

    private String name,description,date,time,venue;

   // private int img;



    public EventsData(String name,  String date, String time, String venue, String description) {
        this.name =name;
       // this.img=img;
        this.date=date;
        this.venue=venue;
        this.description=description;
        this.time=time;


    }
    public EventsData() {
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEventName() {
        return name;
    }

   // public int getImg() {
   //     return img;
   // }

    public void setEventName(String name) {
        this.name = name;
    }

   // public void setImg(int img) {
   //     this.img = img;
   // }

}
