package com.acme.internal;


public class Event {
    String name;
    String date;
    String time;
    String place;
    String info;
    String invitedPeople;

    public Event(String name, String date, String time, String place, String info, String invitedPeople) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.place = place;
        this.info = info;
        this.invitedPeople = invitedPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInvitedPeople() {
        return invitedPeople;
    }

    public void setInvitedPeople(String invitedPeople) {
        this.invitedPeople = invitedPeople;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Event.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Event other = (Event) obj;
        if ((this.getName() == null) ? (other.getName() != null) : !this.getName().equals(other.getName())) {
            return false;
        }
        if ((this.getDate() == null) ? (other.getDate() != null) : !this.getDate().equals(other.getDate())) {
            return false;
        }
        if ((this.getTime() == null) ? (other.getTime() != null) : !this.getTime().equals(other.getTime())) {
            return false;
        }
        if ((this.getPlace() == null) ? (other.getPlace() != null) : !this.getPlace().equals(other.getPlace())) {
            return false;
        }
        if ((this.getInfo() == null) ? (other.getInfo() != null) : !this.getInfo().equals(other.getInfo())) {
            return false;
        }
        if ((this.getInvitedPeople() == null) ? (other.getInvitedPeople() != null) : !this.getInvitedPeople().equals(other.getInvitedPeople())) {
            return false;
        }
        return true;
    }
}
