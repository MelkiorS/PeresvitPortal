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
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Date start;
    @Getter
    @Setter
    private Date finish;
    @Getter
    @Setter
    private Date created;
    @Getter
    @Setter
    private String eventUrl;

}