package 호주식투표제도;
/*
 * 				자바 과제
 * 										2022.03.04
 * 														윤태검
 * 										
 * 				CandidateArrayList.java
 * 
 * 				< 후보자 ArrayList > 
 */

import java.util.ArrayList;

public class CandidateArrayList {
	
	public static int VCount = 1000;				// 투보자의 숫자
	
	private ArrayList<Candidate> candidateList;		// ArrayList 선언		
	
	public CandidateArrayList() {
		candidateList = new ArrayList<Candidate>();	// ArrayList 생성
	}
	
	public void addCandidate(Candidate CD) {
		candidateList.add(CD);
	}
	
	public void addCandidateCount(Candidate CD, int num) {
		candidateList.add(CD);
	}
	
	public void showAllMember() {
		for(Candidate CD : candidateList) {
			System.out.println(CD);
		}
	}
	// 후보자 출력 합니다.
	public void showMember(int CandidateID) {
		for(int i = 0; i < candidateList.size(); i++) {
			Candidate CD = candidateList.get(i);
			int temp = CD.getCandidateID();
			if(temp == CandidateID) {
				System.out.println(CD);
			}
		}
	}
	
	// 클래스 사이즈 반환.
	public int CDSize() {
		return candidateList.size();
	}
	// 후보자 득표값 반환.
	public int CDCount(int num) {
		Candidate CD = candidateList.get(num);
		return CD.getCandidateCount(); 
	}
	// 후보자 득표값 증가.
	public void addVT(int Num) {
		for(int i = 0; i < candidateList.size(); i++) {
			Candidate CD = candidateList.get(i);
			int temp = CD.getCandidateID();
			if(temp == Num) {
				CD.setCandidateCount(CD.getCandidateCount());
			}
		}
	}
	// 제거.
	public boolean remove(int CandidateID) {
		for(int i = 0; i < candidateList.size(); i++) {
		Candidate CD = candidateList.get(i);
		int temp = CD.getCandidateID();
		if(temp == CandidateID) {
			candidateList.remove(i);
			return true;
			}
		}
		return false;
	}
	// 아이디 변환.
	public void changeId(int CandidateID) {
		for(int j = CandidateID; j < candidateList.size(); j++) {
			Candidate CD = candidateList.get(j);
			CD.changeCandidateID(CD.getCandidateID());
		}
	}
	
}
