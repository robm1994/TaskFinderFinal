package org.taskfinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;

public interface OfferRepository extends JpaRepository<Offer, Long> {

	List<Offer> findByUser(User user);
	
	List<Offer> findByUserAndAccepted(User user, int acc);

	List<Offer> findByTask(Task task);

	Offer findOne(Long id);

	Offer findByTaskAndAccepted(Task task, int acc);
	



}
