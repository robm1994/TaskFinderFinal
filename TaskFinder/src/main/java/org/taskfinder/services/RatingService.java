package org.taskfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Rating;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public void addRating(Rating rating, User user, Task task, User worker) {

		rating.setUser(user);
		rating.setTask(task);
		rating.setWorker(worker);
		ratingRepository.save(rating);
	}

	public void save(Rating rating) {

		ratingRepository.save(rating);
	}
	
//	public List<Rating> findUserRating(Rating rating) {
//
//		return ratingRepository.findByUser(rating);
//	}

	public List<Rating> findWorkerRating(User worker) {
		return ratingRepository.findByWorker(worker);
	}
	
	public List<Rating> findUserRating(User user){
		
		return ratingRepository.findByUser(user);
	}
	
	public Rating findOne(Long id) {
		// TODO Auto-generated method stub
		return ratingRepository.findOne(id);
	}
	
}
