package spring.batch.sample;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "prox")
public class ProcessScheduler {

    private static Logger logger = Logger.getLogger(ProcessScheduler.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public void proc() {

	System.out
		.println("PROCESSANDO....\nMétodo executado a cada 15 segundos. Data/Hora atual :: "
			+ new Date());

	try {

	    String arquivo = "C:/Dir/eclipse/luna/lunadev/spring-batch-file-schedule/xml/outputs/report.xml";

	    File file = new File(arquivo);
	    if (file.exists()) {
		file.delete();
	    }

	    JobParameters jobParameters = new JobParametersBuilder().addLong(
		    "time", System.currentTimeMillis()).toJobParameters();

	    JobExecution execution = jobLauncher.run(job, jobParameters);
	    System.out.println("Exit Status : " + execution.getStatus());
	    Notifier.getInstance().setEndOfProcess(true);

	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
    }
}