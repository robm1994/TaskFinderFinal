package org.taskfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.repositories.OfferRepository;

@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepository;

	public void addOffer(Offer offer, User user, Task task) {

		offer.setUser(user);
		offer.setTask(task);
		offerRepository.save(offer);
	}

	public List<Offer> findUserOffer(User user) {

		return offerRepository.findByUser(user);
	}

	public List<Offer> findTaskOffer(Task task) {
		// TODO Auto-generated method stub
		return offerRepository.findByTask(task);
	}

	public Offer findOne(Long id) {
		// TODO Auto-generated method stub
		return offerRepository.findOne(id);
	}

	public String findEmailByOffer(Offer offer) {
		// TODO Auto-generated method stub
		return offer.getUser().getEmail();
	}

	public Offer findByAcceptedAndTask(Task task) {
		// TODO Auto-generated method stub
		return offerRepository.findByTaskAndAccepted(task, 1);
	}
	
	public List <Offer> findByAcceptedAndUser(User user) {
		return offerRepository.findByUserAndAccepted(user, 1);
	}

	public void save(Offer offer) {

		offerRepository.save(offer);
	}

	

	// TODO Auto-generated method stub

}
