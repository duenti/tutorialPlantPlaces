package com.plantplaces.dto;

import java.util.Date;

import javax.inject.Named;

@Named
public class Photo {
	private int id;
	private int speciemen_id;
	private String uri;
	private Date dateTaken;
	private String contributor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSpeciemen_id() {
		return speciemen_id;
	}
	public void setSpeciemen_id(int speciemen_id) {
		this.speciemen_id = speciemen_id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Date getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}
	public String getContributor() {
		return contributor;
	}
	public void setContributor(String contributor) {
		this.contributor = contributor;
	}
}
