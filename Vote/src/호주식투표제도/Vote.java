package 호주식투표제도;
/*
 * 				자바 과제
 * 										2022.03.04
 * 														윤태검
 * 										
 * 				Vote.java
 * 
 * 				호주식 투표 제도를 이용하여 후보자를 선출하는 프로그램 입니다.
 */
import java.util.Scanner;

class Vote {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CandidateArrayList cdiList = new CandidateArrayList();

		int CandidateNum; 										// 후보자의 수
		int VoteNum = 0;										// 투표자의 수
		String voteHistory = null; 								// 투표 내역
		String name = null; 									// 후보자의 이름
		int Error = 0;											// 오류 방지
		
		// 후보자의 수를 입력 받습니다. (최대 20명 까지)
		do {
			System.out.print("후보자 의 수 : ");
			CandidateNum = scan.nextInt();
			if (CandidateNum > 20)
				System.out.println("후보자가 너무 많습니다.");
		} while (CandidateNum > 20);
		// 후보자 이름을 입력 받습니다. (최대 이름 80글자 이하)
		name = scan.nextLine();
		for (int i = 0; i < CandidateNum; i++) {
			do {
				System.out.print("이름 : ");
				name = scan.nextLine();
				if (name.length() >= 80)
					System.out.println("이름이 너무 깁니다.");
			} while (name.length() >= 80);
			Candidate CD = new Candidate(i, name);
			cdiList.addCandidate(CD);
		}
		// 투표 처리를 합니다. / 아무것도 입력 안할때(엔터) 까지 반복
		String array[] = new String[cdiList.CDSize()];
		String strArray[][] = new String[CandidateArrayList.VCount][40];
		System.out.println("투표를 시작합니다. (엔터 종료)");
		do {
			voteHistory = scan.nextLine();
			array = voteHistory.split(" ");
			if(voteHistory != "") {
				// 후보 인원과 맞지 않는 투표를 방지 합니다.
				if (array.length != cdiList.CDSize()) {
					System.out.println("후보인원과 맞지 않습니다. ");
					continue;
				}
				// 1 ~ n(후보인원) 까지의 우선순위만 받아 들입니다.  
				for (int j = 0; j < cdiList.CDSize(); j++) {
					if (Integer.parseInt(array[j]) <= 0 || Integer.parseInt(array[j]) > CandidateNum) {
						System.out.println("투표 우선 순위 범위가 아닙니다. ");
						Error = 1;
						break;
					}
					// 우선 순위 중복을 방지 합니다.
					for( int k = j+1; k < cdiList.CDSize(); k++) {
						if (Integer.parseInt(array[j]) == Integer.parseInt(array[k])) {
							System.out.println("투표 순위 중복 입니다. ");
							Error = 1;
							break;
						}
					}
				}
				if(Error == 0) {
					strArray[VoteNum++] = array;
					CandidateArrayList.VCount--;
				}
			}
			Error = 0;
			if (CandidateArrayList.VCount == 0) 
				break;
		} while (voteHistory != ""); 
		// 투표 계표를 시작합니다.
		for (int j = 0; j < VoteNum; j++) {
			for (int k = 0; k < cdiList.CDSize(); k++) {
				if (Integer.parseInt(strArray[j][k]) == 1) {
					cdiList.addVT(k);
				}
			}
		}	
		do {
		} while (Voting(VoteNum, voteHistory, cdiList, strArray));

		scan.close();
	}
	
	static boolean Voting(int VoteNum, String str, CandidateArrayList cdiList, String strArray[][]) {
		int highVoteNum = cdiList.CDCount(0); 				// 최고 득표수
		int lowVoteNum = cdiList.CDCount(0); 				// 가장낮은 득표수
		int badCandiNum = 0; 								// 가장 적게 받은 후보자의번호
		int goodCandiNum = 0; 								// 가장 많이 받은 후보자의번호
		int CandidateNum = 0;								// 남은 후보자의 수
		int FinalCandidate = 0; 							// 당선 후보자
		int min = 20;										// 투표 최소값.
		CandidateNum = cdiList.CDSize();

		// 가장 많이 받은 득표와 가장 낮은 득표를 게산 합니다.
		for (int i = 0; i < CandidateNum; i++) {
			if (cdiList.CDCount(i) > highVoteNum) {
				highVoteNum = cdiList.CDCount(i);
			}
			if (cdiList.CDCount(i) < lowVoteNum) {
				lowVoteNum = cdiList.CDCount(i);
			}
		}
		// 최고 득표와 가장낮은 득표가 같으면 재투표를 합니다.
		if (highVoteNum == lowVoteNum) {
			System.out.println("가장 높은 표와 낮은 표가 동일합니다. ");
			return false;
		}
		// 과반수 이상의 표를 얻은 후보자를 구합니다.
		for (int j = 0; j < CandidateNum; j++) {
			if (cdiList.CDCount(j) >= ((VoteNum) / 2) && cdiList.CDCount(j) == highVoteNum) {
				goodCandiNum++;
				FinalCandidate = j;
			}
		}
		// 과반수 이상의 표를 얻은 후보자를 출력합니다.
		if (goodCandiNum < 2 && goodCandiNum > 0) {
			cdiList.showMember(FinalCandidate);
			return false;
		}
		// 탈학한 후보자를 1순위로 뽑은 표를 다시 집계 합니다.
		for (int k = 0; k < CandidateNum; k++) {
			if (cdiList.CDCount(k) == lowVoteNum) {
				badCandiNum = k;
				for (int j = 0; j < VoteNum; j++) {
					if (Integer.parseInt(strArray[j][badCandiNum]) == 1) {
						for (int h = 0; h < CandidateNum; h++) {
							if (Integer.parseInt(strArray[j][h]) > 1
									&& Integer.parseInt(strArray[j][h]) < min) {
								min = Integer.parseInt(strArray[j][h]);
							}
						}
					}
				}
				// 가장 높은 선호도를 얻는후보가 그 표를 얻습니다.
				for (int j = 0; j < VoteNum; j++) {
					if (Integer.parseInt(strArray[j][badCandiNum]) == 1) {
						for (int h = 0; h < CandidateNum; h++) {
							if (Integer.parseInt(strArray[j][h]) == min) {
								cdiList.addVT(h);
							}
						}
					}
				}	
			}
		}
		// 탈락한 후보자를 지워 줍니다.
		for (int k = CandidateNum -1; k > -1; k--) {
			if (cdiList.CDCount(k) == lowVoteNum) {
				cdiList.remove(k);
				cdiList.changeId(k);
			}
		}
		// 탈락한 후보자의 투표내용을 지워줍니다.
		for(int i = 0; i < VoteNum; i++) {
			System.arraycopy(strArray[i], badCandiNum+1, strArray[i], badCandiNum,CandidateNum-(badCandiNum+1));
		}
		return true;
	}
}
