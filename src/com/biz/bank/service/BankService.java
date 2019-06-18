package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.model.BankVO;

public class BankService {
	/*
	 * balance.txt 파일을 읽어서 계좌정보를 bList에 추가하는 메서드 작성
	 */
	Scanner scan = new Scanner(System.in);
	List<BankVO> bList;
	FileReader fileReader;
	BufferedReader buffer;

	public BankService(String fileName) throws FileNotFoundException {
		bList = new ArrayList<BankVO>();
		fileReader = new FileReader(fileName);
		buffer = new BufferedReader(fileReader);

	}

	public void readbList() throws IOException {
		String reader = "";
		while (true) {
			reader = buffer.readLine();
			if (reader == null)
				break;
			String[] bbList = reader.split(":");
			BankVO vo = new BankVO();
			vo.setAcc(bbList[0]);
			vo.setBalance(Integer.valueOf(bbList[1]));
			vo.setDate(bbList[2]);

			bList.add(vo);
		}
	}

	public BankVO pickAcc(String accNum) {
//		String acc = "0001";

		/*
		 * bList에서 0001인 데이터를 찾고 그 계좌의 현 잔액을 Console에 표시
		 */
		int index = 0;
		int scLen = bList.size();
		BankVO vo = null;
		for (index = 0; index < scLen; index++) {
			vo = bList.get(index);
			if (bList.get(index).getAcc().equals(accNum)) {
				return vo;

			}
		}
		return null;
	}
	
	/* 
	 * 계좌번호로 pickAcc로부터 vo값을 추출하고
	 * balance값과 money 값을 더하여
	 * vo의 balance에 저장하고
	 * Console에 보여주는 코드를 작성
	 * 
	 */ 
	public void inputMoney(String acc, int money) {
		
		BankVO vo = pickAcc(acc);
		int bal = vo.getBalance();
		
		
		int total=0;
		total = bal + money;
		
		vo.setBalance(total);
		// 현재 컴퓨터날짜값을 가져오기
		// Java 1.7 이하에서 지금도 사용하는 코드
		Date date = new Date(System.currentTimeMillis());
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sf.format(date);
		vo.setDate(curDate);
		
		// Java 8  이상에서 사용하는 새로운 날짜
		LocalDate localDate = LocalDate.now();
		vo.setDate(localDate.toString());
		
		System.out.println("================================");
		System.out.println(vo);
		System.out.println("================================");
	}
	
	public void outputMoney(String acc, int money) {
		
		BankVO vo = pickAcc(acc);
		int bal = vo.getBalance();
		
		if(bal < money) {
			System.out.println("잔액이 부족합니다.");
			return;
		}
		
		int total=0;
		total = bal - money;
		
		vo.setBalance(total);
		// 현재 컴퓨터날짜값을 가져오기
		// Java 1.7 이하에서 지금도 사용하는 코드
		Date date = new Date(System.currentTimeMillis());
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sf.format(date);
		vo.setDate(curDate);
		
		// Java 8  이상에서 사용하는 새로운 날짜
		LocalDate localDate = LocalDate.now();
		vo.setDate(localDate.toString());
		
		System.out.println("================================");
		System.out.println(vo);
		System.out.println("================================");
	}
	
	
	
	public int selectMenu() {
		System.out.println("========================");
		System.out.println("1. 입금 2. 출금 -9.종료");
		System.out.println("========================");
		System.out.println("업무선택>>");
		String strMenu = scan.nextLine();
		
		int intMenu = 0;
		try {
			intMenu = Integer.valueOf(strMenu);
			
				
		} catch (Exception e) {
			// 오류 무시	
		}
		return intMenu;
	}
}