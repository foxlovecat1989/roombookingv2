package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.RoomRepository;
import com.virtualpairprogrammers.roombooking.model.entities.Room;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/rooms")
@AllArgsConstructor
public class RestRoomController {

    private final RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") Long id) {
        return roomRepository.findById(id).get();
    }

    @PostMapping()
    public Room newRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping()
    public Room updateRoom(@RequestBody Room updateRoom) {
        Room originalRoom = roomRepository.findById(updateRoom.getId()).get();
        originalRoom.setName(updateRoom.getName());
        originalRoom.setLocation(updateRoom.getLocation());
        originalRoom.setLayoutCapacities(updateRoom.getLayoutCapacities());

        return roomRepository.save(originalRoom);
    }
}
