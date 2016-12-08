package org.bionic.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Event entity by MMaximov 03.11.2016
 */
@Entity
@Data
public class Event {

    @Id
    @GeneratedValue
    private long id;
    @SerializedName("text")
    private String name;
    @SerializedName("start_date")
    private Date start;
    @SerializedName("end_date")
    private Date finish;
    private Date created;
    private String eventUrl;

    private String description;
    private String place;
    @Column(columnDefinition = "boolean not null default 0")
    private boolean connectAll;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_events", joinColumns = {
            @JoinColumn(name = "event_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id",
                    nullable = false, updatable = false) })
    private Set<User> users = new HashSet<User>();

    @Override
    public String toString() {
        String startDate = (new SimpleDateFormat("dd.MM.yyyy")).format(start);
        String finishDate = (new SimpleDateFormat("dd.MM.yyyy")).format(finish);
        return (startDate.equals(finishDate)) ? "" + startDate + " " + name : "" + startDate + " - " + finishDate + " "  + name;
    }

    public boolean isAssigned(User u){
        return users.contains(u);
    }
}