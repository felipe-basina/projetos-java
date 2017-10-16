package br.com.sample.aop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Descrição: Spring AOP x @Transactional
 * http://blog.triadworks.com.br/controle-transacional-declarativo-com-spring-aop-ou-transactional
 *
 */
@SpringBootApplication
public class SpringBootAopApplication {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(SpringBootAopApplication.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StudentDAO studentJDBCTemplate;

	@Bean
	protected CommandLineRunner sampleData() {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				LOGGER.debug("Creating tables");

				try {
					jdbcTemplate.execute(" DROP TABLE IF EXISTS Marks ");
					jdbcTemplate.execute(" DROP TABLE IF EXISTS Student ");

					jdbcTemplate.execute(" CREATE TABLE Student( "
							+ " ID INT NOT NULL AUTO_INCREMENT, "
							+ " NAME VARCHAR(20) NOT NULL, "
							+ " AGE INT NOT NULL, " + " PRIMARY KEY (ID) "
							+ "); ");
					jdbcTemplate
							.execute(" CREATE TABLE Marks( "
									+ " SID INT NOT NULL, "
									+ " MARKS  INT NOT NULL, "
									+ " YEAR   INT NOT NULL "
									+ " ,CONSTRAINT StudentIDFK FOREIGN KEY (SID) REFERENCES Student (ID) "
									+ "); ");
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}

				LOGGER.debug("------Records creation--------");
				studentJDBCTemplate.create("Zara", 11, 99, 2010);
				studentJDBCTemplate.create("Nuha", 20, 97, 2010);
				studentJDBCTemplate.create("Ayan", 25, 100, 2011);

				LOGGER.debug("------Listing all the records--------");
				List<StudentMarks> studentMarks = studentJDBCTemplate
						.listStudents();
				for (StudentMarks record : studentMarks) {
					LOGGER.debug(record);
				}
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAopApplication.class, args);
	}
}

class StudentMarks {
	private Integer age;
	private String name;
	private Integer id;
	private Integer marks;
	private Integer year;
	private Integer sid;

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getYear() {
		return year;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getSid() {
		return sid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudentMarks [age=");
		builder.append(age);
		builder.append(", name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", marks=");
		builder.append(marks);
		builder.append(", year=");
		builder.append(year);
		builder.append(", sid=");
		builder.append(sid);
		builder.append("]");
		return builder.toString();
	}
}

class StudentMarksMapper implements RowMapper<StudentMarks> {
	public StudentMarks mapRow(ResultSet rs, int rowNum) throws SQLException {

		StudentMarks studentMarks = new StudentMarks();

		studentMarks.setId(rs.getInt("id"));
		studentMarks.setName(rs.getString("name"));
		studentMarks.setAge(rs.getInt("age"));
		studentMarks.setSid(rs.getInt("sid"));
		studentMarks.setMarks(rs.getInt("marks"));
		studentMarks.setYear(rs.getInt("year"));

		return studentMarks;
	}
}

interface StudentDAO {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Student and Marks
	 * tables.
	 */
	public void create(String name, Integer age, Integer marks, Integer year);

	/**
	 * This is the method to be used to list down all the records from the
	 * Student and Marks tables.
	 */
	public List<StudentMarks> listStudents();
}

@Component
class StudentJDBCTemplate implements StudentDAO {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(StudentJDBCTemplate.class);

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * Adicionando a anotação @Transactional a operação, quando não executada
	 * com sucesso, realiza o roll - back automático
	 * 
	 * Sem a anotação, não ocorre o roll back!
	 */
	@Transactional
	public void create(String name, Integer age, Integer marks, Integer year) {

		try {
			String SQL1 = "insert into Student (name, age) values (?, ?)";
			jdbcTemplateObject.update(SQL1, name, age);

			// Get the latest student id to be used in Marks table
			String SQL2 = "select max(id) from Student";
			int sid = jdbcTemplateObject.queryForObject(SQL2, Integer.class);

			String SQL3 = "insert into Marks(sid, marks, year) "
					+ "values (?, ?, ?)";
			jdbcTemplateObject.update(SQL3, sid, marks, year);

			LOGGER.debug(" --------- Created Name = " + name + ", Age = " + age);
			// to simulate the exception.
			// throw new RuntimeException("simulate Error condition");
		} catch (DataAccessException e) {
			System.out.println("Error in creating record, rolling back");
			throw e;
		}
	}

	public List<StudentMarks> listStudents() {
		String SQL = "select s.*, m.* from Student s INNER JOIN Marks m ON m.sid = s.id where s.id = m.sid";

		List<StudentMarks> studentMarks = jdbcTemplateObject.query(SQL,
				new StudentMarksMapper());
		return studentMarks;
	}
}

@Aspect
@Component
class ServiceMonitor {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(ServiceMonitor.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss:ms");
	
	@AfterReturning("within(br.com.sample.aop..*)")
	public void logServiceAccess(JoinPoint joinPoint) {
		LOGGER.debug("#### Completed: " + joinPoint + " in " + sdf.format(new Date()));
	}

}