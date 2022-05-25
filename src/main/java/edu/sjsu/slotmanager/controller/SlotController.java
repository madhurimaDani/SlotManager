package edu.sjsu.slotmanager.controller;

import edu.sjsu.slotmanager.entity.Slot;
import edu.sjsu.slotmanager.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slot")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @PostMapping(path= "/new", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity createSlot(@RequestBody Slot slot)
    {
        try{
            if(slot.getDate() == null){
                return new ResponseEntity("Slot Date Missing", HttpStatus.BAD_REQUEST);
            }
            if(slot.getStart_time() == null){
                return new ResponseEntity("Slot Start Time Missing", HttpStatus.BAD_REQUEST);
            }
            if(slot.getEnd_time() == null){
                return new ResponseEntity("Slot End Time Missing", HttpStatus.BAD_REQUEST);
            }
            if(slot.getDoctor() == null){
                return new ResponseEntity("Slot's Doctor Missing", HttpStatus.BAD_REQUEST);
            }
            if(slotService.validateSlot(slot) != null){
                return new ResponseEntity("Given Slot Details Already Exist", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println("Exception : " + e);
        }

        Slot response = slotService.createSlot(slot);

        if(response.getSlot_id() == 0){
            return new ResponseEntity("Slot Creation Failed", HttpStatus.EXPECTATION_FAILED);
        }else {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }


    @PostMapping(path= "/update", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity updateSlot(@RequestBody Slot slot)
    {
        try{
            if(slot.getSlot_id() < 1) {
                return new ResponseEntity("Please provide slot id to update slot", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println("Exception : " + e);
        }

        Slot response = slotService.updateSlot(slot);

        if(response != null) {
            return new ResponseEntity(response, HttpStatus.OK);
        }else {
            return new ResponseEntity("Slot Update Failed", HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PostMapping(path= "/book", consumes = "application/json", produces = "application/json")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity bookSlot(@RequestBody Slot slot)
    {
        try{
            if(slot.getSlot_id() < 1) {
                return new ResponseEntity("Please provide slot id to book slot", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println("Exception : " + e);
        }

        Slot response = slotService.bookSlot(slot);

        if(response != null) {
            return new ResponseEntity(response, HttpStatus.OK);
        }else {
            return new ResponseEntity("Slot Booking Failed", HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/available")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity getAvailableSlots(){
        return new ResponseEntity(slotService.getAvailableSlots(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity getSlotById(@PathVariable int id){
        return new ResponseEntity(slotService.getSlotById(id), HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity deleteSlotById(@PathVariable int id){
        if(slotService.deleteSlotById(id) != 0) {
            return new ResponseEntity("Slot Deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity("Slot Deletion Failed", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
