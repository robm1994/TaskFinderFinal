package org.taskfinder.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taskfinder.entities.Offer;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}

	public List<Task> findUserTask(User user) {

		return taskRepository.findByUser(user);
	}

	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return taskRepository.findAll();
	}

	public List<Task> findByName(String name) {

		List<Task> tasks = new ArrayList<Task>();
		List<Task> descriptions = new ArrayList<Task>();
		List<Task> categorys = new ArrayList<Task>();
		List<Task> locations = new ArrayList<Task>();

		descriptions.addAll(taskRepository.findByDescriptionLike("%" + name + "%"));
		categorys.addAll(taskRepository.findByCategoryLike("%" + name + "%"));
		locations.addAll(taskRepository.findByLocationLike("%" + name + "%"));
		
		//adds all descriptions to tasks
		//removes any matches between descriptions and categorys from category list
		for (int i = 0; i < descriptions.size(); i++) {
			Task description = descriptions.get(i);
			
			if (categorys.contains(description)) {
				tasks.add(description);
				categorys.remove(description);
			}
			else {
				tasks.add(description);
			}
		}
		
		//adds remaining categorys to task list
		for (int i = 0; i < categorys.size(); i++) {
			Task category = categorys.get(i);
			tasks.add(category);
		}
		
		//adds locations that are not already in the task list to the task list
		for (int i = 0; i < locations.size(); i++) {
			Task location = locations.get(i);
			
			if (!tasks.contains(location)) {
				tasks.add(location);
			}
			
		}

		return tasks;

	}

	public Task findById(Long id) {
		// TODO Auto-generated method stub
		return taskRepository.findById(id);
	}

	public List<Task> findByCompleted() {

		return taskRepository.findByCompleted(1);
	}
	
	

	public void save(Task task) {

		taskRepository.save(task);
	}

	public List<Task> findByUncompleted() {
		// TODO Auto-generated method stub
		return taskRepository.findByCompleted(0);
	}

}
