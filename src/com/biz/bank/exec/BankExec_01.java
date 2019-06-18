package com.biz.bank.exec;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.biz.bank.model.BankVO;
import com.biz.bank.service.BankService;

public class BankExec_01 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String fileName = "src/com/biz/bank/model/balance.txt";
		BankService bs = null;

		try {
			// try내에서 다시 객체를초기화해서
			// 사용할수 있도록
			bs = new BankService(fileName);
			bs.readbList();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			int intMenu = bs.selectMenu();
			if (intMenu == -9)
				break;
			
			System.out.println("계좌번호 >> ");
			String accNum = scan.nextLine();
			BankVO vo = bs.pickAcc(accNum);
			if (vo == null) {
				System.out.println("존재하지 않는 계좌입니다.");
				continue;
		}


			if (intMenu == 1) {
				System.out.println("입금금액 >>");
				String strInputMoney = scan.nextLine();
				int intmoney = Integer.valueOf(strInputMoney);
				bs.inputMoney(accNum, intmoney);
			}
			; // 입금처리
			if (intMenu == 2) {
				System.out.println("출금금액 >>");
				String strOutputMoney = scan.nextLine();
				int intOutMoney = Integer.valueOf(strOutputMoney);
				bs.outputMoney(accNum, intOutMoney);
			}
			; // 출금처리

		
		}
	}
}
