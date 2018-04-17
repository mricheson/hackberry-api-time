package com.richesoncabinets.hackberry.time.model.tsheets;

public abstract class Result<T> {
	private T results;
	private boolean more;
	private SupplementalData supplemental_data;
	
	public T getResults() {
		return results;
	}
	public void setResults(T results) {
		this.results = results;
	}
	public boolean isMore() {
		return more;
	}
	public void setMore(boolean more) {
		this.more = more;
	}
	public SupplementalData getSupplemental_data() {
		return supplemental_data;
	}
	public void setSupplemental_data(SupplementalData supplemental_data) {
		this.supplemental_data = supplemental_data;
	}
	
	protected abstract void merge(T otherResults);
	
	public void merge(Result<T> otherResult)
	{
		 merge(otherResult.getResults());
		 more = this.more || otherResult.more;
		 supplemental_data.merge(otherResult.supplemental_data);
	}
}
