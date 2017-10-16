package br.com.rest.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Todo {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull(message = "error.title.notnull")
	@Size(min = 1, max = 15, message = "error.title.size")
	private String title;

	@NotNull(message = "error.description.notnull")
	@Size(min = 5, max = 30, message = "error.description.size")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Transient
	private String fmtCreationDate;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getFmtCreationDate() {
		if (this.creationDate != null) {
			fmtCreationDate = sdf.format(this.creationDate);
		}
		return fmtCreationDate;
	}

	public Todo() {
		super();
	}

	public Todo(String title, String description, Date creationDate) {
		super();
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		if (id != other.id)
			return false;
		return true;
	}
	
}
