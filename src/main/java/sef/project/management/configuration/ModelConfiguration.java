package sef.project.management.configuration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import sef.project.management.dto.UserDTO;
import sef.project.management.entity.User;
import sef.project.management.service.UserService;

@Component
public class ModelConfiguration {

	@Autowired
	private UserService userService;
	
	// Create a singleton project management object for all API calls
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ProjectManagement getProjectManagement() {
		
		// Create model mapper to convert entity to DTO
		ModelMapper modelMapper = new ModelMapper();
		
		// Model for the application
    	ProjectManagement projectManagement = new ProjectManagement();
    	
    	// Get all users and add them to model
		List<User> users = userService.getAllUsers();
		for(User u: users) {
			// Map user entity to user DTO
			UserDTO user = modelMapper.map(u, UserDTO.class);
			projectManagement.getUsers().add(user);
		}
		
        return projectManagement;
    }

}
