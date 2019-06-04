package org.taskfinder.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity

public class Offer {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private float amount;

	private int accepted;

	/*
	 * @NotNull
	 * 
	 * @Temporal(TemporalType.TIMESTAMP)
	 * 
	 * @Column(name = "posted_at") public Date postedAt = new Date();
	 */

	@ManyToOne
	@JoinColumn(name = "USER_EMAIL")

	private User user;

	@ManyToOne
	@JoinColumn(name = "TASK_ID")

	private Task task;

	public Offer(int amount, User user, Task task) {

		this.amount = amount;
		this.user = user;
		this.task = task;
		this.accepted = 0;

	}

	public Offer(int amount) {
		this.amount = amount;

	}

	public Offer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

}
