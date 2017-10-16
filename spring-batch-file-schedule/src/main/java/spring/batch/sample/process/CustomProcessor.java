package spring.batch.sample.process;

import org.springframework.batch.item.ItemProcessor;

import spring.batch.sample.model.Report;

public class CustomProcessor implements ItemProcessor<Report, Report> {

    @Override
    public Report process(Report item) throws Exception {

	System.out.println("Processing..." + item);
	return item;
    }

}