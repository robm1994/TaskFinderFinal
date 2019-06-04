package org.taskfinder.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.services.TaskService;
import org.taskfinder.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
      @Autowired
      private UserService userService;
      @Autowired
      private TaskService taskService;
       
      @Before
      public void initDb() {
    	  {
    		  User newUser = new User("testUser@mail.com", "testUser", "123456", "", "");
    		  userService.createUser(newUser); 
    	  }
    	  {
    		  User newUser = new User("testAdmin@mail.com", "testAdmin", "123456", "", "");
    		  userService.createUser(newUser); 
    	  }
    	  
    	  
      }
	 
      @Test
      public void testUser() {
    	  User user = userService.findOne("testUser@mail.com");
          assertNotNull(user);
    	  User admin = userService.findOne("testAdmin@mail.com");
    	  assertEquals(admin.getEmail(),"testAdmin@mail.com");
      }
      
      
      
     
	
}
