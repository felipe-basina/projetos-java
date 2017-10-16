package spring.batch.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 * 
 */
public class App {

    // TODO: Executar essa rotina através de um processo Spring (scheduler)

    // TODO: Verificar como executar operações batch sem regitrar em base de
    // dados (not stored job-meta in database)

    public static void main(String[] args) {

	String[] springConfig = { "classpath:spring/batch/config/spring-files.xml" };

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		springConfig);

	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	Job job = (Job) context.getBean("helloWorldJob");

	try {

	    JobExecution execution = jobLauncher.run(job, new JobParameters());
	    System.out.println("Exit Status : " + execution.getStatus());

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {

	    if (context != null) {
		context.close();
	    }

	}

	System.out.println("Done");

    }

}
