package com.virtualpairprogrammers.roombooking.model.entities;

import com.virtualpairprogrammers.roombooking.model.Layout;
import com.virtualpairprogrammers.roombooking.model.entities.LayoutCapacity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "location cannot be blank")
    private String location;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<LayoutCapacity> layoutCapacities;

    public Room(String name, String location) {
        this.name = name;
        this.location = location;
        layoutCapacities = new ArrayList<>();
        for (Layout layout : Layout.values()) {
            layoutCapacities.add(new LayoutCapacity(layout, 0));
        }
    }

    public void setCapacity(LayoutCapacity layoutCapacity) {
        for (LayoutCapacity lc : layoutCapacities) {
            if (lc.getLayout() == layoutCapacity.getLayout()) {
                lc.setCapacity(layoutCapacity.getCapacity());
            }
        }
    }
}
