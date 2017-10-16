package spring.boot.mvc.bootstrap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.mvc.bootstrap.form.model.UserForm;
import spring.boot.mvc.bootstrap.model.User;
import spring.boot.mvc.bootstrap.repository.BaseRepository;
import spring.boot.mvc.bootstrap.repository.UserRepository;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private UserRepository usrRepo;
	
	@Autowired
	public UserServiceImpl(UserRepository usrRepo) {
		this.usrRepo = usrRepo;
	}

	@Override
	public List<UserForm> getAllForm() {
		List<UserForm> usuarios = new ArrayList<UserForm>();
		for (User usuario : usrRepo.findAll()) {
			UserForm form = new UserForm(usuario.getId(), 
					usuario.getName(), usuario.getLastName(), 
					usuario.getEmail());
			form.setCreationDate(usuario.getCreationDate());
			form.setUpdateDate(usuario.getUpdateDate());
			usuarios.add(form);
		}
		return usuarios;
	}

	@Override
	public User findByName(String name) {
		List<User> usuarios = usrRepo.findByName(name);
		if (usuarios != null
				&& usuarios.size() > 0) {
			return usuarios.get(0);
		}
		return null;
	}

	@Override
	public BaseRepository<User, Long> getRepository() {
		return usrRepo;
	}

	@Override
	public User save(User user) {
		if (user.getId() > 0) {
			return this.update(user);
		}
		return this.create(user);
	}
	
	@Override
	public User getById(Long id) {
		return this.find(id);
	}
	
	@Override
	public UserForm getUserFormFromEntity(User user) {
		UserForm newForm = new UserForm(user.getId(), user.getName(), user.getLastName(), user.getEmail());
		newForm.setCreationDate(user.getCreationDate());
		newForm.setUpdateDate(user.getUpdateDate());
		return newForm;
	}
	
	@Override
	public User getEntityFromUserForm(UserForm form) {
		User user = new User(form.getName(), form.getLastName(), form.getEmail());
		if (form.getId() != null) {
			user.setId(form.getId());
		}
		user.setCreationDate(new Date());
		user.setUpdateDate(new Date());
		return user;
	}
	
	@Override
	public void deleteUser(long id) {
		this.delete(id);
	}
	
	@Override
	public User findByEmail(String email) {
		return usrRepo.findByEmail(email);
	}
	
	@Override
	public boolean userExist(User user) {
		User userDb = this.findByEmail(user.getEmail());
		if (userDb != null) {
			return true;
		}
		return false;
	}
}