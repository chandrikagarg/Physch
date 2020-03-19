package com.physch.game.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
//We make the Game Mode the entity
//diff between enum and entity
//enum - code easy to read
//enum enforce code change whenever new thing is added
//entity -> insert a row in to a table
//

@Entity
@Table(name="gamemodes")
public class GameMode extends Auditable {
    @NotBlank
    @Getter
    @Setter
    @Column(unique = true) // all game mode has the unique name
    private String name;

    @Getter
    @Setter
    @URL
    private String picture;

    @Getter
    @Setter
    private String description;


    public GameMode() {
    }

    public GameMode(@NotBlank String name, @URL String picture, String description) {
        this.name = name;
        this.picture = picture;
        this.description = description;
    }
}
