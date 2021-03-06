package ridgewell.pickupsports2.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cameronridgewell on 1/16/15.
 */
public class Event implements Parcelable{
    private String name;
    private Sport sport;
    private Date time;
    private Location location;
    private int cost;
    private String notes;
    private boolean isPublic;
    private List<User> attendees;
    private int maxAttendance;
    private List<User> waitlist; //queue
    private User creator;

    //largest number of users allowed on the waitlist
    public final int WAITLISTMAX = 10;

    public Event(String name_, Sport sport_, Date time_, Location location_, int cost_,
                 String notes_, boolean isPublic_, int maxAttendance_, User creator_) {
        this.name = name_;
        this.sport = sport_;
        this.time = time_;
        this.location = location_;
        this.cost = cost_;
        this.notes = notes_;
        this.isPublic = isPublic_;
        this.maxAttendance = maxAttendance_;
        this.attendees = new ArrayList<User>();
        this.waitlist = new ArrayList<User>();
        this.creator = creator_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDaysUntil() {
        Date now = new Date();
        now.setHours(12);
        now.setMinutes(0);
        now.setSeconds(0);
        int days =  (int) ((time.getTime() - now.getTime()) / (1000 * 60 * 60 * 24));

        if (days == 0) {
            return "(today)";
        } else if (days == 1) {
            return "(tomorrow)";
        } else if (days == -1) {
            return "(yesterday)";
        } else if (days < 0) {
            return "(" + (-1 * days) + " days ago)";
        } else {
            return "(" + days + " days)";
        }
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public int getAttendeeCount() {
        return attendees.size();
    }

    public void addAttendee(User user) {
        this.attendees.add(user);
    }

    public void removeAttendee(User user) {
        this.attendees.remove(user);
    }

    public int getMaxAttendance() {
        return maxAttendance;
    }

    public void setMaxAttendance(int maxAttendance) {
        this.maxAttendance = maxAttendance;
    }

    public List<User> getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(List<User> waitlist) {
        this.waitlist = waitlist;
    }

    public User waitlistTop() {
        return waitlist.get(0);
    }

    //no checking for empty waitlist
    public User waitlistDequeue() {
        return waitlist.remove(0);
    }

    public boolean waitlistEnqueue(User user) {
        if (this.waitlistSize() < WAITLISTMAX) {
            waitlist.add(user);
            return true;
        }
        return false;
    }

    public boolean waitlistIsEmpty() {
        return waitlist.size() == 0;
    }

    public int waitlistSize() {
        return waitlist.size();
    }

    public User getCreator() {
        return creator;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeParcelable(sport,0);
        out.writeLong(time.getTime());
        out.writeParcelable(location, 0);
        out.writeInt(cost);
        out.writeString(notes);
        out.writeInt(isPublic ? 1 : 0);
        out.writeTypedList(attendees);
        out.writeInt(maxAttendance);
        out.writeTypedList(waitlist);
        out.writeParcelable(creator, 0);
    }

    public static final Parcelable.Creator<Event> CREATOR
            = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    private Event(Parcel in) {
        name = in.readString();
        sport = in.readParcelable(Sport.class.getClassLoader());
        time = new Date(in.readLong());
        location = in.readParcelable(Location.class.getClassLoader());
        cost = in.readInt();
        notes = in.readString();
        isPublic = in.readInt() == 1;
        attendees = in.createTypedArrayList(User.CREATOR);
        maxAttendance = in.readInt();
        waitlist = in.createTypedArrayList(User.CREATOR);
        creator = in.readParcelable(User.class.getClassLoader());
    }
}
