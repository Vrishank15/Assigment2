package com.people.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "people")
@EntityListeners(AuditingEntityListener.class)
public class People {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(name = "email_address", nullable = false)
    private String email;
    
    @Column(name = "country", nullable = false)
    private String country;
    
    @Column(name = "avatar", nullable = false)
    private String avatar;

    
	public People(String name, String dob, String email, String country, String avatar) {
		super();
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.country = country;
		this.avatar = avatar;
	}

	public People() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
    
    
    
}
