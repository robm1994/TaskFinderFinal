package org.taskfinder.entities;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Task {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String date;
	@NotEmpty
	private String location;
	@NotEmpty
	private String category;
	@NotEmpty
	@Column(length = 1000)
	private String description;

	private int completed;
	private String time;
	
	@NotEmpty
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne
	@JoinColumn(name = "USER_EMAIL")
	private User user;

	/*
	 * @OneToMany(mappedBy = "task", cascade = CascadeType.ALL) private List<Offer>
	 * offers;
	 */

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "task")
	private Rating rating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public Task(String date, String location, String category, String description, int completed, User user, String time, String address) {
		this.date = date;
		this.location = location;
		this.category = category;
		this.description = description;
		this.user = user;
		this.completed = 0;
		this.time = time;
		this.address = address;
	}

	public Task(String date, String location, String category, String description, int completed, String time, String address) {
		this.date = date;
		this.location = location;
		this.category = category;
		this.description = description;
		this.completed = 0;
		this.time = time;
		this.address = address;
	}

	public Task() {
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
