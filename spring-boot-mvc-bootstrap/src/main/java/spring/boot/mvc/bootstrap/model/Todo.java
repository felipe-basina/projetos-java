package spring.boot.mvc.bootstrap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "todo")
@Table(name = "TODO", schema = "springbootstrap")
/*@NamedQueries({
	@NamedQuery(name = "getTodoWithUser", query = "SELECT t FROM todo t JOIN FETCH t.usuario ORDER BY t.usuario.creationDate DESC"),
	@NamedQuery(name = "getTodoWithUserByTodoId", query = "SELECT t FROM todo t JOIN FETCH t.usuario WHERE t.id = :todoId")
})*/
public class Todo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2162243927430380030L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENTIFIER")
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SCHEDULED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_IDENTIFIER")
	private User usuario;

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Todo [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", creationDate=");
		builder.append(creationDate);
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
		Todo other = (Todo) obj;
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

	public Todo(Long id, String title, String description, Date creationDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
	}
	
	public Todo(String title, String description, Date creationDate) {
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
	}

	public Todo() {
	}
}