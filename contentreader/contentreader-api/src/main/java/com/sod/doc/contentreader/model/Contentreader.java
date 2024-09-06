package com.sod.doc.contentreader.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;
@Entity
@Table(name = "contentreader")
public class Contentreader extends AbstractJpaStateEntity implements Serializable {
    @Serial
	private static final long serialVersionUID = 1L;
	public String originalUrl;
	public String shortestUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String shortUrl;
	public Date timePeriod;
	public String fullName;
	public Long accessCount =0L;

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortestUrl() {
		return shortestUrl;
	}

	public void setShortestUrl(String shortestUrl) {
		this.shortestUrl = shortestUrl;
	}

	public Date getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(Date timePeriod) {
		this.timePeriod = timePeriod;
	}

	public long getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(long accessCount) {
		this.accessCount = accessCount;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
