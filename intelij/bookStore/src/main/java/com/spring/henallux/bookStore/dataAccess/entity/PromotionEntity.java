package com.spring.henallux.bookStore.dataAccess.entity;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="promotion")
@NamedQuery(
		name = "findCurrentPromotions",
		query = "select currentPromotion from PromotionEntity currentPromotion where currentPromotion.startDate <= :date and currentPromotion.endDate >= :date")
public class PromotionEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="promotion_id")
	private Integer promotion_id;
	
	@Column(name="startdate")
	private Date startDate;
	
	@Column(name="enddate")
	private Date endDate;
	
	@Column(name="percentage")
	private Float percentage;
	
	@Column(name="summary")
	private String summary;
	
	@JoinColumn(name="book_id", referencedColumnName="isbn")
	@ManyToOne
	private BookEntity book_id;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Float getPercentage() {
		return percentage;
	}
	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
	public BookEntity getBook_id() {
		return book_id;
	}
	public void setBook_id(BookEntity book_id) {
		this.book_id = book_id;
	}
	public Integer getPromotion_id()
	{
		return promotion_id;
	}
	public void setPromotion_id(Integer promotion_id)
	{
		this.promotion_id = promotion_id;
	}
	public String getSummary()
	{
		return summary;
	}
	public void setSummary(String summary)
	{
		this.summary=summary;
	}
	
}
