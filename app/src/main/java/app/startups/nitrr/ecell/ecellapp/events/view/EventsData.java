package app.startups.nitrr.ecell.ecellapp.events.view;

/**
 * Created by Iket on 7/27/2016.
 */
public class EventsData {

    private String name,description,date,time,venue,image;


    public EventsData(String name,  String date, String time, String venue, String description,String image) {
        this.name =name;
        this.image=image;
        this.date=date;
        this.venue=venue;
        this.description=description;

    }
    public EventsData() {
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }


    public String getVenue() {
        return venue;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEventName() {
        return name;
    }



    public void setEventName(String name) {
        this.name = name;
    }

}
