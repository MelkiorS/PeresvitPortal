package org.bionic.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Event entity by MMaximov 03.11.2016
 */
@Entity
@Data
public class Event {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date start;
    private Date finish;
    private Date created;
    private String eventUrl;

}