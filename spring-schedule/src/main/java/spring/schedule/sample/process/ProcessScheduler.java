package spring.schedule.sample.process;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service(value = "prox")
public class ProcessScheduler {

    public void proc() {

	System.out
		.println("PROCESSANDO....\nMétodo executado a cada 5 segundos. Data/Hora atual :: "
			+ new Date());
	int cont = 0;
	while (++cont <= 4) {
	    try {
		System.out.println("cont [" + cont + "]");
		//Thread.sleep(1000l);
	    } catch (Exception ex) {

	    }
	}
    }
}