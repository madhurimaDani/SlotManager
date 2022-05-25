package edu.sjsu.slotmanager.dao;


import edu.sjsu.slotmanager.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SlotDao extends JpaRepository<Slot, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into slot(date, start_time, end_time, doctor, is_booked) " +
            "values(:date, :start_time, :end_time, :doctor, :is_booked)", nativeQuery = true)
    int createSlot(String date, String start_time, String end_time, String doctor, String is_booked);

    @Query(value = "select slot_id from slot order by slot_id desc limit 1", nativeQuery = true)
    int getLatestSlotId();

    @Query(value = "SELECT * FROM slot WHERE slot_id = :id", nativeQuery = true)
    Slot getSlotById(int id);

    @Query(value = "SELECT * FROM slot WHERE is_booked = \"no\"", nativeQuery = true)
    List<Slot> getAvailableSlots();

    @Query(value = "SELECT * FROM slot WHERE date = :date AND start_time = :start_time " +
            "AND end_time = :end_time AND doctor = :doctor limit 1", nativeQuery = true)
    Slot validateSlot(String date, String start_time, String end_time, String doctor);

    @Transactional
    @Modifying
    @Query(value = "UPDATE slot SET date = :date, start_time = :start_time, end_time = :end_time, doctor = :doctor, " +
            "is_booked = :is_booked where slot_id = :slot_id", nativeQuery = true)
    int updateSlot(int slot_id, String date, String start_time,String end_time,String doctor,String is_booked);

    @Transactional
    @Modifying
    @Query(value = "UPDATE slot SET is_booked = \"yes\" where slot_id = :slot_id", nativeQuery = true)
    int bookSlot(int slot_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM slot WHERE slot_id = :id", nativeQuery = true)
    int deleteSlotById(int id);




}
