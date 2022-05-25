package edu.sjsu.slotmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int slot_id;
    String date;
    String start_time;
    String end_time;
    String doctor;
    String is_booked;

    public Slot(){

    }

    public Slot(int slot_id, String date, String start_time, String end_time, String doctor, String isBooked) {
        this.slot_id = slot_id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.doctor = doctor;
        this.is_booked = isBooked;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getIs_booked() {
        return is_booked;
    }

    public void setIs_booked(String isBooked) {
        this.is_booked = isBooked;
    }
}
