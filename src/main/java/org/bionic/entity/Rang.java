package org.bionic.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity
@Data
@Table(name = "rang")
public class Rang {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rangId;	
	private String rangName;
	@OneToMany(mappedBy = "rang")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.DELETE)
	private Collection<User> userCollection;
}