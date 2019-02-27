package cali.eventkalender.model;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Nation implements Parcelable {


    private UUID mId;
    private String mName;
    private String mAddress;
    private String mAboutNation;
    private Double mLatitude;
    private Double mLongitude;
    private List<Event> mEvents;

    public Nation(String name, String aboutNation, String address, Double latitude, Double longitude) {
        this(UUID.randomUUID(), name, aboutNation, address, latitude, longitude);
    }

    public Nation(UUID id, String name, String aboutNation, String address, Double latitude, Double longitude) {
        mId = id;
        mName = name;
        mAboutNation = aboutNation;
        mAddress = address;
        mLatitude = latitude;
        mLongitude = longitude;
        mEvents = new ArrayList<>();
    }

    public String getAboutNation() {
        return mAboutNation;
    }

    public void setAboutNation(String aboutNation) {
        mAboutNation = aboutNation;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }


    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Event> getEvents() {
        return mEvents;
    }

    public Event getEvent(UUID id){
        for(Event event : mEvents){
            if(event.getId() == id){
                return event;
            }
        }
        return null;
    }

    public void setEvents(List<Event> events) {
        mEvents = events;
    }

    public void addEvent(Event event) {
        if (event != null) {
            Nation n = event.getNation();
            if (n != null && n != this) {
                n.deleteEvent(event);
            }
            if (!hasEvent(event)) {
                this.mEvents.add(event);
                if (event.getNation() != this) {
                    event.setNation(this);
                }
            }
        }
    }

    public void deleteEvent(UUID id) {

    for(Event e : mEvents){
        if(e.getId().equals(id)){
            deleteEvent(e);
        }
    }

     // Android innan API 24, ingen lambda
     /*   Optional<Event> e = this.mEvents.stream().filter(x -> x.getId() == id).findFirst();
        if (e.isPresent()) {
            deleteEvent(e.get());
        } */
    }

    public void deleteEvent(Event event) {
        if (event != null) {
            this.mEvents.remove(event);
            event.setNation(null);
        }
    }

    public boolean hasEvent(Event event) {
        return this.mEvents.contains(event);
    }


    public Nation(Parcel in) {
        mId = (UUID) in.readValue(UUID.class.getClassLoader());// mId = new UUID(in.readLong(), in.readLong()); // ParcelUuid, någon skillnad?
        mName = in.readString();
        mAddress = in.readString();
        mAboutNation = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
        mEvents = in.createTypedArrayList(Event.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mId); //  dest.writeSerializable(mId);
        dest.writeString(mName);
        dest.writeString(mAddress);
        dest.writeString(mAboutNation);
        dest.writeDouble(mLatitude);
        dest.writeDouble(mLongitude);
        dest.writeTypedList(mEvents);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Nation createFromParcel(Parcel in) {
            return new Nation(in);
        }

        public Nation[] newArray(int size) {
            return new Nation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
