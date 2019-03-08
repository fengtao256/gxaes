package com.gxaes.es.entity;

public class TestDetails {
	private Integer testId ;
	private Integer queId ;
	private String actualAnswer ;
	private Integer correct ;
	
		public Integer getTestId() {
			return testId;
		}
		public void setTestId(Integer testId) {
			this.testId = testId;
		}
		public Integer getQueId() {
			return queId;
		}
		public void setQueId(Integer queId) {
			this.queId = queId;
		}
		public String getActualAnswer() {
			return actualAnswer;
		}
		public void setActualAnswer(String actualAnswer) {
			this.actualAnswer = actualAnswer;
		}
		public Integer getCorrect() {
			return correct;
		}
		public void setCorrect(Integer correct) {
			this.correct = correct;
		}
}
