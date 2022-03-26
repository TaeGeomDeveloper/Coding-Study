package ȣ�ֽ���ǥ����;
/*
 * 				�ڹ� ����
 * 										2022.03.04
 * 														���°�
 * 										
 * 				CandidateArrayList.java
 * 
 * 				< �ĺ��� ArrayList > 
 */

import java.util.ArrayList;

public class CandidateArrayList {
	
	public static int VCount = 1000;				// �������� ����
	
	private ArrayList<Candidate> candidateList;		// ArrayList ����		
	
	public CandidateArrayList() {
		candidateList = new ArrayList<Candidate>();	// ArrayList ����
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
	// �ĺ��� ��� �մϴ�.
	public void showMember(int CandidateID) {
		for(int i = 0; i < candidateList.size(); i++) {
			Candidate CD = candidateList.get(i);
			int temp = CD.getCandidateID();
			if(temp == CandidateID) {
				System.out.println(CD);
			}
		}
	}
	
	// Ŭ���� ������ ��ȯ.
	public int CDSize() {
		return candidateList.size();
	}
	// �ĺ��� ��ǥ�� ��ȯ.
	public int CDCount(int num) {
		Candidate CD = candidateList.get(num);
		return CD.getCandidateCount(); 
	}
	// �ĺ��� ��ǥ�� ����.
	public void addVT(int Num) {
		for(int i = 0; i < candidateList.size(); i++) {
			Candidate CD = candidateList.get(i);
			int temp = CD.getCandidateID();
			if(temp == Num) {
				CD.setCandidateCount(CD.getCandidateCount());
			}
		}
	}
	// ����.
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
	// ���̵� ��ȯ.
	public void changeId(int CandidateID) {
		for(int j = CandidateID; j < candidateList.size(); j++) {
			Candidate CD = candidateList.get(j);
			CD.changeCandidateID(CD.getCandidateID());
		}
	}
	
}
