package org.taskfinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Rating;
import org.taskfinder.entities.User;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	List<Rating> findByWorker(User worker);
	Rating findOne(Long id);
	List<Rating> findByUser(User user);

}
