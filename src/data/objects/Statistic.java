package data.objects;

import java.util.Date;

public class Statistic {

	private int id;
	private int userid;
	private int exerciseareaid;
	private int percent;
	private Date date;

	public Statistic(int userid, int exerciseareaid, int percent,
		Date date) {
		this.userid = userid;
		this.exerciseareaid = exerciseareaid;
		this.percent = percent;
		this.date = date;
	}

	/*
	public Statistic() {

	}
	*/

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the exerciseareaid
	 */
	public int getExerciseareaid() {
		return exerciseareaid;
	}

	/**
	 * @param exerciseareaid the exerciseareaid to set
	 */
	public void setExerciseareaid(int exerciseareaid) {
		this.exerciseareaid = exerciseareaid;
	}

	/**
	 * @return the percent
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
