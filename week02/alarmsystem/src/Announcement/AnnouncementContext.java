package Announcement;

import Sensors.Sensor;

/**
 * Created by dannymadell on 22/01/2017.
 */
public class AnnouncementContext {

    private Announcement announcement = null;

    public AnnouncementContext(Announcement announcement) {this.announcement = announcement; }

    public void doAnnouncement() {
        System.out.println("Announced alarm: " + announcement.getClass());

    }
}
