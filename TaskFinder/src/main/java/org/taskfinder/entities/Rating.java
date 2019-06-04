package org.taskfinder.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Rating {
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Column(length = 1000)
	private String comment;

	private int stars;

	@ManyToOne
	@JoinColumn(name = "WORKER_EMAIL")
	private User worker;

	@ManyToOne
	@JoinColumn(name = "USER_EMAIL")
	private User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "task_id", nullable = false)
	private Task task;
	
	

	public Rating(String comment, int stars) {
		this.comment = comment;
		this.stars = stars;

	}

	public Rating(String comment, Task task, User user, User worker, int stars) {
		this.comment = comment;
		this.task = task;
		this.user = user;
		this.worker = worker;
		this.stars = stars;

	}

	public Rating() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getWorker() {
		return worker;
	}

	public void setWorker(User worker) {
		this.worker = worker;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

}
