package edu.sjsu.slotmanager.service;

import edu.sjsu.slotmanager.dao.SlotDao;
import edu.sjsu.slotmanager.entity.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SlotService {
    @Autowired
    private SlotDao slotDao;

    @Transactional
    public Slot createSlot(Slot slot){

        String date = (slot.getDate() == null)? "NA": slot.getDate();
        String start_time = (slot.getStart_time() == null)? "NA": slot.getStart_time(); //YYYY-MM-DD format
        String end_time = (slot.getEnd_time() == null)? "NA": slot.getEnd_time(); //YYYY-MM-DD format
        String doctor = (slot.getDoctor() == null)? "NA": slot.getDoctor();
        String is_booked = "no";
        slot.setIs_booked(is_booked);

        int rowsUpdated = slotDao.createSlot(date, start_time, end_time, doctor, is_booked);
        int id = slotDao.getLatestSlotId();

        slot.setSlot_id(id);

        return slot;
    }


    public Slot validateSlot(Slot slot){
        System.out.println("here");
        String date = slot.getDate();
        String start_time = slot.getStart_time(); //YYYY-MM-DD format
        String end_time = slot.getEnd_time(); //YYYY-MM-DD format
        String doctor = slot.getDoctor();
        return slotDao.validateSlot(date, start_time, end_time, doctor);
    }

    public Slot bookSlot(Slot slot){

        int slot_id = slot.getSlot_id();

        Slot base = slotDao.getSlotById(slot_id);

        String is_booked = "yes";
        slot.setIs_booked(is_booked);

        String date = base.getDate();
        slot.setDate(date);
        String start_time = base.getStart_time(); //YYYY-MM-DD format
        slot.setStart_time(start_time);
        String end_time = base.getEnd_time(); //YYYY-MM-DD format
        slot.setEnd_time(end_time);
        String doctor = base.getDoctor();
        slot.setDoctor(doctor);


        int updatedRows = slotDao.bookSlot(slot_id);

        if(updatedRows < 0){
            return null;
        }else{
            return slot;
        }
    }

    public Slot updateSlot(Slot slot){

        int slot_id = slot.getSlot_id();

        Slot base = slotDao.getSlotById(slot_id);

        String date = (slot.getDate() == null)? base.getDate(): slot.getDate();
        slot.setDate(date);
        String start_time = (slot.getStart_time() == null)? base.getStart_time(): slot.getStart_time(); //YYYY-MM-DD format
        slot.setStart_time(start_time);
        String end_time = (slot.getEnd_time() == null)? base.getEnd_time(): slot.getEnd_time(); //YYYY-MM-DD format
        slot.setEnd_time(end_time);
        String doctor = (slot.getDoctor() == null)? base.getDoctor(): slot.getDoctor();
        slot.setDoctor(doctor);
        String is_booked = (slot.getIs_booked() == null)? base.getIs_booked(): slot.getIs_booked();
        slot.setIs_booked(is_booked);


        int updatedRows = slotDao.updateSlot(slot_id, date, start_time,end_time,doctor,is_booked);

        if(updatedRows < 0){
            return null;
        }else{
            return slot;
        }
    }


    public Slot getSlotById(int id) {
        return slotDao.getSlotById(id);
    }

    public List<Slot> getAvailableSlots() {
        return slotDao.getAvailableSlots();
    }

    public int deleteSlotById(int id) {
        return slotDao.deleteSlotById(id);
    }

}
