package BasicCommonClasses;


public class ForgetPassword {

	String question;
	String answer;
	
	public ForgetPassword(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public ForgetPassword() {
		this.question = new String();
		this.answer = new String();
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
