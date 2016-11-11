package org.bionic.entity;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Default;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
 
    private String firstName;
	private String lastName;
	private String middleName;

    private String password;
	private String email;
	private String avatarURL;
	// level of user
	@ManyToOne
	@Cascade(CascadeType.DELETE)
	@JoinColumn(name="rangId")
	private Rang rang;
    // Account verification status
	@Column(columnDefinition = "boolean default true")
    private boolean enabled = true;
  /*  @ManyToMany(
    		cascade = {CascadeType.ALL},
			targetEntity = ResourceGroup.class
	)
    @JoinTable(
    		name="userResourceGroup",
            joinColumns={@JoinColumn(name="userId")},
            inverseJoinColumns={@JoinColumn(name="resourceGroupId")}
	)
    private Set<ResourceGroup> resourceGroups = new HashSet<ResourceGroup>();
     */
    private boolean enabled;

	public User() {
		super();
		enabled = true;
	}

}
