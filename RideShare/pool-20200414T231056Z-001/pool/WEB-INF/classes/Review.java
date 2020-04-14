import java.io.IOException;
import java.io.*;


public class Review implements Serializable{
	private String Reviewer;
	private String userName;
	private String reviewDate;
	private String reviewText;
	private String retailercity;
	private String age;
	private String gender;
	private String reviewRating;
	
	public Review (String Reviewer,String userName,String reviewRating,String reviewDate,String reviewText,String retailercity,String age,String gender){
		this.Reviewer=Reviewer;
		this.userName=userName;
	 	this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
	 	this.reviewText=reviewText;
		this.retailercity= retailercity;
		this.age=age;
		this.gender= gender;
	}
	public String getReviewer() {
		return Reviewer;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setReviewer(String Reviewer) {
		this.Reviewer = Reviewer;
	}
	

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public String getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
 
			public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}


	public String getUserAge() {
			return age;
	}

	public void setUserAge(String age) {
		this.age = age;
	}

	public String getUserGender() {
			return gender;
	}

	public void setUserGender(String gender) {
		this.gender = gender;
	}


}
