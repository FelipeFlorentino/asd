package br.com.delogic.asd.housekeeping;

import java.io.File;
import java.util.List;

import br.com.delogic.jfunk.Find;
import br.com.delogic.jfunk.When;

public class TimeHousekeepingJob extends HousekeepingJobBase {

	protected Double thresholdHours;

	public TimeHousekeepingJob(String directory, Double thresholdHours) {
		super(directory);
		this.thresholdHours = thresholdHours;
	}

	@Override
	public List<File> filterFilesToBeDeleted(List<File> files) {

		final long thresholdCut = (long) (System.currentTimeMillis() - (thresholdHours * 60 * 60 * 1000));

		return Find.all(files, new When<File>() {
			@Override
			public boolean found(File e) {
				return e.isFile() && e.lastModified() < thresholdCut;
			}
		});
	}

}