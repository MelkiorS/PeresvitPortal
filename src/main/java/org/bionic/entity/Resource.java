package org.bionic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
public class Resource {
	@Id
	@GeneratedValue
	@Getter @Setter private Long resourceId;
	@Getter @Setter private String title;
	@Getter @Setter private String description;
	@Getter @Setter private int type; // ENUM
	@Getter @Setter private String url;
	@ManyToOne
	@JoinColumn(name="groupId")
	@Getter @Setter private ResourceGroup group; //ManyToOne
	@ManyToOne
	@JoinColumn(name="ownerId")
	@Getter @Setter private User user; // If personal information (info concerning user)
}
