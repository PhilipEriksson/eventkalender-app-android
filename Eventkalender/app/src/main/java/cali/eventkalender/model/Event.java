package cali.eventkalender.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static junit.framework.Assert.assertTrue;

public class Event implements Comparable<Event>, Parcelable {

    private UUID mId;
    private String mTitle;
    private String mEventType;
    private Date mDate;
    private String mSummary;
    private Nation mNation;
   // private HashMap<UUID, Nation> parents = new HashMap<UUID, Nation>();
    //private int mNationId;

/**    public Event() {
        this(UUID.randomUUID(), summary, eventType, nation);
    }
*/

    public Event(String title, String summary, String eventType, Nation nation) {
        mId = UUID.randomUUID();
        mTitle = title;
        mEventType = eventType;
        mDate = new Date();
        mSummary = summary;
        setNation(nation);
    }

  // public UUID getKey() { return mNation.getId(); }

    public String getEventType() {
        return mEventType;
    }

    public void setEventType(String eventType) {
        mEventType = eventType;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public Nation getNation() {
        return mNation;
    }

    public void setNation(Nation nation) {
        if (nation != this.mNation) {
            this.mNation = nation;
        }
        if (nation != null && !nation.hasEvent(this)) {
            nation.addEvent(this);
        }
    }

    @Override
    public int compareTo(Event event) {
       // return getTitle().compareTo(event.getTitle());
        return getDate().compareTo(event.getDate());
    }

    @Override
    public String toString(){
        return getTitle();
    }

    public Event(Parcel in) {
        mId = (UUID) in.readValue(UUID.class.getClassLoader()); //mId = new UUID(in.readLong(), in.readLong()); // mId = (UUID) in.readValue(UUID.class.getClassLoader());
        mTitle = in.readString();
        mEventType = in.readString();
        mDate = new Date(in.readLong());
        mSummary = in.readString();
   //     mNation = (Nation) in.readValue(Nation.class.getClassLoader());
      //    assertTrue( (mNation = parents.remove( getKey() )) != null );
   //     setNation(mNation);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mId);//dest.writeSerializable(mId);  //     dest.writeValue(mId);
        dest.writeString(mTitle);
        dest.writeString(mEventType);
        dest.writeLong(mDate.getTime());
        dest.writeString(mSummary);
     //   dest.writeValue(mNation);

    //    parents.put( this.getKey(), mNation );


    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
