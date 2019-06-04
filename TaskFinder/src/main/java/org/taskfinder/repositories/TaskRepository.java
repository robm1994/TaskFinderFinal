package org.taskfinder.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findByUser(User user);

	List<Task> findByDescriptionLike(String description);

	Task findById(Long id);

	List<Task> findByCompleted(int completed);

	List<Task> findByCategory(String category);

	List<Task> findAll();

	List<Task> findByLocation(String location);

	List<Task> findByCategoryLike(String category);

	List<Task> findByLocationLike(String location);
	
	

}
