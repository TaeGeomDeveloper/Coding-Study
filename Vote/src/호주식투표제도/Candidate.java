package ȣ�ֽ���ǥ����;
/*
 * 				�ڹ� ����
 * 										2022.03.04
 * 														���°�
 * 										
 * 				Candidate.java
 * 
 * 				< �ĺ��� Ŭ���� > 
 */

public class Candidate {
	private int CandidateID;		// �ĺ��� ���̵�
	private String candidateName;	// �ĺ��� �̸�
	private int candidateCount;		// �ĺ��� ��ǥ��
	

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
		return candidateName +" �ĺ��� �� "+ candidateCount+ "��ǥ�� �Ͽ� ����Ǿ����ϴ�.";
	}
}
