package com.zensar.SamuraiZenAnalyticaIntegration.model;

public class UserRatingDto {

	private String response;
	int feedback; // 1- positive Feedback 0- Negative Feedback
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "UserRatingDto [responseId=" + id + ", response=" + response + ", feedback=" + feedback + "]";
	}

}
