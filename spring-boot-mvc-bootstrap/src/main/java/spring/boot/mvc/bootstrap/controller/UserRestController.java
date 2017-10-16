package spring.boot.mvc.bootstrap.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.mvc.bootstrap.exception.EmailAlreadyInUseException;
import spring.boot.mvc.bootstrap.exception.UserNotFoundException;
import spring.boot.mvc.bootstrap.form.model.UserForm;
import spring.boot.mvc.bootstrap.model.User;
import spring.boot.mvc.bootstrap.service.UserService;

@RestController
public class UserRestController {

	private static Logger log = LoggerFactory.getLogger(UserRestController.class);
	
	private static final String PREFIX_LOG = "\n.....";
	
	private UserService userService;

	private MessageSource message;
	
	public UserRestController() {
	}

	@Autowired
	public UserRestController(UserService userService, MessageSource message) {
		this.userService = userService;
		this.message = message;
	}
	
	@RequestMapping(value = { "/usersave" }, method = { RequestMethod.GET })
	public void userForm() {
		log.debug(PREFIX_LOG.concat("[/usersave] -> userForm"));
	}
	
	@RequestMapping(value = { "/user/all" }, method = { RequestMethod.GET })
	@ResponseBody
	public List<UserForm> getUsers() {
		log.debug(PREFIX_LOG.concat("[/user/all] -> getUsers"));
		return userService.getAllForm();
	}
	
	@RequestMapping(value = { "/user/save" }, method = { RequestMethod.POST }, consumes="application/json")
	@ResponseBody
	public List<UserForm> saveUser(@RequestBody UserForm form) {
		log.debug(PREFIX_LOG.concat("[/save] -> saveUser"));
		log.debug(PREFIX_LOG.concat("[/form]:\n\t").concat(form.toString()));
		
		User user = userService.getEntityFromUserForm(form);
		
		// Se possui 'id', entao recupera o registro existente para atualizacao
		if (form.getId() != null
				&& form.getId() > 0) {
			User userDb = userService.getById(form.getId());
			user.setCreationDate(userDb.getCreationDate());
			user.setUpdateDate(new Date());
		} else {
			// Se nao for atualizacao, verifica se o email ja esta cadastrado 
			if (userService.userExist(user)) {
				throw new EmailAlreadyInUseException(message.getMessage("user.controller.email.already.in.use", 
						new Object[] { user.getEmail() }, Locale.getDefault()));
			}
		}

		user = userService.save(user);
		log.debug(PREFIX_LOG.concat("User:\n") + user);
		
		return userService.getAllForm();
	}
	
	@RequestMapping(value = { "/user/get/{id}" }, method = { RequestMethod.GET })
	@ResponseBody
	public UserForm userGet(@PathVariable(value="id") Long id) {
		log.debug(PREFIX_LOG.concat("[/user/get] -> userGet"));
		log.debug(PREFIX_LOG.concat("[/id]:\n\t").concat(String.valueOf(id)));
		
		User user = userService.getById(id);
		
		if (user == null) {
			throw new UserNotFoundException(message.getMessage("user.controller.user.not.found"
					, new Object[] { id }, Locale.getDefault()));
		}
		
		return userService.getUserFormFromEntity(user);
	}
	
	@RequestMapping(value = { "/user/delete/{id}" }, method = { RequestMethod.GET })
	@ResponseBody
	public List<UserForm> deleteUser(@PathVariable(value = "id") Long id) {
		log.debug(PREFIX_LOG.concat("[/user/delete] -> deleteUser"));
		log.debug(PREFIX_LOG.concat("[/id]:\n\t").concat(String.valueOf(id)));

		User user = userService.getById(id);
		
		if (user == null) {
			throw new UserNotFoundException(message.getMessage("user.controller.user.not.found"
					, new Object[] { id }, Locale.getDefault()));
		}

		userService.deleteUser(id);
		
		return userService.getAllForm();
	}
}