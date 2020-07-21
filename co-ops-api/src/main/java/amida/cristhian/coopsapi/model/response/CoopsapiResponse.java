package amida.cristhian.coopsapi.model.response;

import java.util.ArrayList;

public class CoopsapiResponse {

	private ArrayList<DailyValue> dailyAvgMonth;
	private String MaxValueMonth;
	private String MinValueMonth;
	private String AverageValueMonth;
	private String MaxValuePrevMonth;
	private String MinValuePrevMonth;
	private String AverageValuePrevMonth;
	
	
	public ArrayList<DailyValue> getDailyAvgMonth() {
		return dailyAvgMonth;
	}

	public void setDailyAvgMonth(ArrayList<DailyValue> dailyAvgMonth) {
		this.dailyAvgMonth = dailyAvgMonth;
	}

	public String getMaxValueMonth() {
		return MaxValueMonth;
	}

	public void setMaxValueMonth(String maxValueMonth) {
		MaxValueMonth = maxValueMonth;
	}

	public String getMinValueMonth() {
		return MinValueMonth;
	}

	public void setMinValueMonth(String minValueMonth) {
		MinValueMonth = minValueMonth;
	}

	public String getAverageValueMonth() {
		return AverageValueMonth;
	}

	public void setAverageValueMonth(String averageValueMonth) {
		AverageValueMonth = averageValueMonth;
	}

	public String getMaxValuePrevMonth() {
		return MaxValuePrevMonth;
	}

	public void setMaxValuePrevMonth(String maxValuePrevMonth) {
		MaxValuePrevMonth = maxValuePrevMonth;
	}

	public String getMinValuePrevMonth() {
		return MinValuePrevMonth;
	}

	public void setMinValuePrevMonth(String minValuePrevMonth) {
		MinValuePrevMonth = minValuePrevMonth;
	}

	public String getAverageValuePrevMonth() {
		return AverageValuePrevMonth;
	}

	public void setAverageValuePrevMonth(String averageValuePrevMonth) {
		AverageValuePrevMonth = averageValuePrevMonth;
	}

}
