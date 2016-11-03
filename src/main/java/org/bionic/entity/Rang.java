package org.bionic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "rang")
public class Rang {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rangId;	
	private String rangName;
}