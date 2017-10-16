package spring.schedule.sample.process;

import org.junit.BeforeClass;
import org.junit.Test;

import spring.schedule.sample.context.SpringContainer;

public class ProcessSchedulerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	SpringContainer.getInstance();
    }

    @Test
    public void testSchedule() {
	while (true) {
	    continue;
	}
    }
}