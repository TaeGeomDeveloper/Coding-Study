package ȣ�ֽ���ǥ����;
/*
 * 				�ڹ� ����
 * 										2022.03.04
 * 														���°�
 * 										
 * 				Vote.java
 * 
 * 				ȣ�ֽ� ��ǥ ������ �̿��Ͽ� �ĺ��ڸ� �����ϴ� ���α׷� �Դϴ�.
 */
import java.util.Scanner;

class Vote {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CandidateArrayList cdiList = new CandidateArrayList();

		int CandidateNum; 										// �ĺ����� ��
		int VoteNum = 0;										// ��ǥ���� ��
		String voteHistory = null; 								// ��ǥ ����
		String name = null; 									// �ĺ����� �̸�
		int Error = 0;											// ���� ����
		
		// �ĺ����� ���� �Է� �޽��ϴ�. (�ִ� 20�� ����)
		do {
			System.out.print("�ĺ��� �� �� : ");
			CandidateNum = scan.nextInt();
			if (CandidateNum > 20)
				System.out.println("�ĺ��ڰ� �ʹ� �����ϴ�.");
		} while (CandidateNum > 20);
		// �ĺ��� �̸��� �Է� �޽��ϴ�. (�ִ� �̸� 80���� ����)
		name = scan.nextLine();
		for (int i = 0; i < CandidateNum; i++) {
			do {
				System.out.print("�̸� : ");
				name = scan.nextLine();
				if (name.length() >= 80)
					System.out.println("�̸��� �ʹ� ��ϴ�.");
			} while (name.length() >= 80);
			Candidate CD = new Candidate(i, name);
			cdiList.addCandidate(CD);
		}
		// ��ǥ ó���� �մϴ�. / �ƹ��͵� �Է� ���Ҷ�(����) ���� �ݺ�
		String array[] = new String[cdiList.CDSize()];
		String strArray[][] = new String[CandidateArrayList.VCount][40];
		System.out.println("��ǥ�� �����մϴ�. (���� ����)");
		do {
			voteHistory = scan.nextLine();
			array = voteHistory.split(" ");
			if(voteHistory != "") {
				// �ĺ� �ο��� ���� �ʴ� ��ǥ�� ���� �մϴ�.
				if (array.length != cdiList.CDSize()) {
					System.out.println("�ĺ��ο��� ���� �ʽ��ϴ�. ");
					continue;
				}
				// 1 ~ n(�ĺ��ο�) ������ �켱������ �޾� ���Դϴ�.  
				for (int j = 0; j < cdiList.CDSize(); j++) {
					if (Integer.parseInt(array[j]) <= 0 || Integer.parseInt(array[j]) > CandidateNum) {
						System.out.println("��ǥ �켱 ���� ������ �ƴմϴ�. ");
						Error = 1;
						break;
					}
					// �켱 ���� �ߺ��� ���� �մϴ�.
					for( int k = j+1; k < cdiList.CDSize(); k++) {
						if (Integer.parseInt(array[j]) == Integer.parseInt(array[k])) {
							System.out.println("��ǥ ���� �ߺ� �Դϴ�. ");
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
		// ��ǥ ��ǥ�� �����մϴ�.
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
		int highVoteNum = cdiList.CDCount(0); 				// �ְ� ��ǥ��
		int lowVoteNum = cdiList.CDCount(0); 				// ���峷�� ��ǥ��
		int badCandiNum = 0; 								// ���� ���� ���� �ĺ����ǹ�ȣ
		int goodCandiNum = 0; 								// ���� ���� ���� �ĺ����ǹ�ȣ
		int CandidateNum = 0;								// ���� �ĺ����� ��
		int FinalCandidate = 0; 							// �缱 �ĺ���
		int min = 20;										// ��ǥ �ּҰ�.
		CandidateNum = cdiList.CDSize();

		// ���� ���� ���� ��ǥ�� ���� ���� ��ǥ�� �Ի� �մϴ�.
		for (int i = 0; i < CandidateNum; i++) {
			if (cdiList.CDCount(i) > highVoteNum) {
				highVoteNum = cdiList.CDCount(i);
			}
			if (cdiList.CDCount(i) < lowVoteNum) {
				lowVoteNum = cdiList.CDCount(i);
			}
		}
		// �ְ� ��ǥ�� ���峷�� ��ǥ�� ������ ����ǥ�� �մϴ�.
		if (highVoteNum == lowVoteNum) {
			System.out.println("���� ���� ǥ�� ���� ǥ�� �����մϴ�. ");
			return false;
		}
		// ���ݼ� �̻��� ǥ�� ���� �ĺ��ڸ� ���մϴ�.
		for (int j = 0; j < CandidateNum; j++) {
			if (cdiList.CDCount(j) >= ((VoteNum) / 2) && cdiList.CDCount(j) == highVoteNum) {
				goodCandiNum++;
				FinalCandidate = j;
			}
		}
		// ���ݼ� �̻��� ǥ�� ���� �ĺ��ڸ� ����մϴ�.
		if (goodCandiNum < 2 && goodCandiNum > 0) {
			cdiList.showMember(FinalCandidate);
			return false;
		}
		// Ż���� �ĺ��ڸ� 1������ ���� ǥ�� �ٽ� ���� �մϴ�.
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
				// ���� ���� ��ȣ���� ����ĺ��� �� ǥ�� ����ϴ�.
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
		// Ż���� �ĺ��ڸ� ���� �ݴϴ�.
		for (int k = CandidateNum -1; k > -1; k--) {
			if (cdiList.CDCount(k) == lowVoteNum) {
				cdiList.remove(k);
				cdiList.changeId(k);
			}
		}
		// Ż���� �ĺ����� ��ǥ������ �����ݴϴ�.
		for(int i = 0; i < VoteNum; i++) {
			System.arraycopy(strArray[i], badCandiNum+1, strArray[i], badCandiNum,CandidateNum-(badCandiNum+1));
		}
		return true;
	}
}
