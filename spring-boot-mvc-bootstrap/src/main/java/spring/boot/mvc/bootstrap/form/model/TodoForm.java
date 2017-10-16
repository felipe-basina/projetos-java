package spring.boot.mvc.bootstrap.form.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import spring.boot.mvc.bootstrap.model.Todo;

public class TodoForm {

	private Long id;
	
	@NotNull
	@Size(min = 5, message = "{Size.min.msg}")
	private String title;
	
	@NotNull
	@Size(min = 10, message = "{Size.min.msg}")	
	private String description;
	
	@NotNull
	private String creationDate;
	
	private Long emailId;
	
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Todo getEntityFromTodoForm() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Todo todo = new Todo(title, description, sdf.parse(creationDate));
		if (this.id != null) {
			todo.setId(this.id);
		}
		return todo;
	}

	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TodoForm [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoForm other = (TodoForm) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public TodoForm(Long id, String title, String description, String creationDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
	}

	public TodoForm() {
	}
	
}