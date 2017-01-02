package ua.peresvit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "combatArt") 
@Data
public class CombatArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long combatArtId;
    private String combatArtName;
}
