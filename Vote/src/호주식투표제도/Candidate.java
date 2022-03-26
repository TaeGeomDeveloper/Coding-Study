package 호주식투표제도;
/*
 * 				자바 과제
 * 										2022.03.04
 * 														윤태검
 * 										
 * 				Candidate.java
 * 
 * 				< 후보자 클래스 > 
 */

public class Candidate {
	private int CandidateID;		// 후보자 아이디
	private String candidateName;	// 후보자 이름
	private int candidateCount;		// 후보자 득표수
	

	public Candidate(int CandidateID, String candidateName){
		this.CandidateID = CandidateID;
		this.candidateName = candidateName;
	}
	
	public Candidate(int CandidateID, int candidateCount){
		this.CandidateID = CandidateID;
		this.candidateCount = candidateCount;
	}
	
	public Candidate(int CandidateID, String candidateName, int candidateCount){
		this.CandidateID = CandidateID;
		this.candidateCount = candidateCount;
		this.candidateName = candidateName;
	}
	
	public int getCandidateID() {
		return CandidateID;
	}
	
	public void changeCandidateID(int candidateID) {
		this.CandidateID = candidateID - 1;
	}

	public void setCandidateID(int candidateID) {
		CandidateID = candidateID;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getCandidateCount() {
		return candidateCount;
	}
	
	public void addCandidateCount(int candidateCount) {
		this.candidateCount = candidateCount;
	}	

	public void setCandidateCount(int candidateCount) {
		this.candidateCount = candidateCount + 1;
	}	
	
	public String toString() {
		return candidateName +" 후보가 총 "+ candidateCount+ "득표를 하여 선출되었습니다.";
	}
}
