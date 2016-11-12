package org.bionic.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
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
    @SerializedName("text")
    private String name;
    @SerializedName("start_date")
    private Date start;
    @SerializedName("end_date")
    private Date finish;
    private Date created;
    private String eventUrl;

    @Override
    public String toString() {
        String startDate = (new SimpleDateFormat("dd.MM.yyyy")).format(start);
        String finishDate = (new SimpleDateFormat("dd.MM.yyyy")).format(finish);
        return (startDate.equals(finishDate)) ? "" + startDate + " " + name : "" + startDate + " - " + finishDate + " "  + name;
    }
}