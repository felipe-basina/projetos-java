package spring.boot.mvc.bootstrap.service;

import java.util.List;

import spring.boot.mvc.bootstrap.form.model.UserForm;
import spring.boot.mvc.bootstrap.model.User;

public interface UserService {
	
	public List<UserForm> getAllForm();
	
	public User findByName(String name);
	
	public User findByEmail(String email);
	
	public User save(User user);

	public void deleteUser(long id);
	
	public User getById(Long id);
	
	public UserForm getUserFormFromEntity(User user);
	
	public User getEntityFromUserForm(UserForm form);
	
	public boolean userExist(User user);
}
