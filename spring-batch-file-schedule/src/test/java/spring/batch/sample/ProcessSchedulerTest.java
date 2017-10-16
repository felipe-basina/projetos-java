package spring.batch.sample;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProcessSchedulerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	SpringContainer.getInstance();
    }

    @Test
    public void testSchedule() {

	boolean fileCreated = false;

	long tempoEspera = 5000l; // 5 segundos

	while (!fileCreated) {

	    if (Notifier.getInstance().isEndOfProcess()) {
		System.out.println(" # Arquivo criado com sucesso!");
		fileCreated = true;
	    } else {
		try {
		    System.out.println(" > Arquivo não foi criado ainda....");
		    Thread.sleep(tempoEspera);
		} catch (Exception ex) {
		    System.err.println(" # Erro ao aguardar... ".concat(ex
			    .getMessage()));
		}
		continue;
	    }

	}
    }
}