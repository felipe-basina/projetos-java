package spring.batch.sample;

public class Notifier {

    private static Notifier instance;

    public static Notifier getInstance() {
	if (instance == null) {
	    instance = new Notifier();
	}
	return instance;
    }

    private Notifier() {
    }

    private boolean endOfProcess = false;

    public boolean isEndOfProcess() {
	return endOfProcess;
    }

    public void setEndOfProcess(boolean endOfProcess) {
	this.endOfProcess = endOfProcess;
    }
}
