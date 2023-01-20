package com.db;

public class Dto {
	public String num;
	public String title;
	public String content;
	public String id;
	public String hits;
	public String dt;
	
//	글 리스트-객체 사용
//	()안에 순서를 바꾸면 list 화면의 순서도 바뀜
//	list.jsp에서 화면 출력하기에 순서 바꿔주면 ()안에 순서를 바꾸지 않아도 바뀜
	public Dto(String num, String title, String id,String hits,String dt) {
		super();
		this.num = num;
		this.title = title;
		this.id = id;
		this.hits = hits;
		this.dt = dt;
	}
	
	//읽기 객체 사용
	public Dto(String num, String title, String content, String id ,String hits,String dt) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.id = id;
		this.hits = hits;
		this.dt = dt;
	}

	//쓰기(writeproc에서 사용)
	public Dto(String title, String content, String id) {
		super();
		this.title = title;
		this.content = content;
		this.id = id;
	}

	public Dto(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}


	
	
	
	
}
