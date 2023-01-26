package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao {
	Connection con = null;
	Statement st = null;
	//글 리스트
	//ArrayList type 사용
	public ArrayList<Dto> list(String page) {
		//com.db 안에 있는 Dto 객체 사용
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			//시작인덱스 
			//1페이지 (1-1)*5=0 /2페이지 (2-1)*5 
			int startIndex = ((Integer.parseInt(page))-1)*Page.PER_PAGE;
			
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			//db 접속 (statement객체를 생성하기 위해서는 Connection이 먼저 연결되어야함)
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);		
			//sql 호출하는 statement 객체 생성
			st=con.createStatement();
			//db에 jsp_board 글들 모두 가져와서 sql에 저장
			//페이지 당 5개의 글을 보여주기 위해서 limit 사용 ex)limit 0,5 ㅡ> 0(start지점) 5(몇 개 가져올건지)
			String sql = String.format("select * from %s limit %s, %s", Db.JSP_BOARD,startIndex, Page.PER_PAGE);
			//잘 가져왔는지 콘솔에 찍어보기
			System.out.println("sql:"+sql);
			//executeUpdate(String sql)
			//: select, show 등(executeQuery 사용)을 제외한 insert, delete, update 처리 시 사용
			//쿼리문 처리 시 int 값 반환
			//ResultSet 객체 반환 
			//ResultSet이란 조회된 목록들의 저장된 객체를 반환
			ResultSet rs = st.executeQuery(sql);
//			rs.next();는 첫 행부터 마지막 행까지 검사 
			rs.next();
			while(rs.next()) {
//				배열 객체이므로 add해준다(num,title...은 db 테이블에서 정한 이름)
				posts.add(new Dto(
						rs.getString("num"),
						rs.getString("title"),
						rs.getString("id"),
						rs.getString("hits"),
						rs.getString("dt")
						));
			}
			//메모리 해제하기 /사용한 객체들 모두 메모리에서 해제해야함
			//최근에 사용한 것들부터 닫아주기(거꾸로!)
			st.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return posts;
	}
			
	//읽기
	public Dto read(String readNum) {
		Dto post = null;
		try {
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);	// [고정-1]
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);	// [고정-2]			
			st=con.createStatement();
			String sql = String.format("select * from %s where num = %s", Db.JSP_BOARD, readNum);
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			post = new Dto(
					rs.getString("num"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getString("id"),
					rs.getString("hits"),
					rs.getString("dt")
					);
			st.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return post;
	}
	
	
	//쓰기
	public void write(Dto w) {
		try {
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);	// [고정-1]
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);	// [고정-2]			
			st=con.createStatement();	// [고정-3]
			String sql = String.format("insert into %s (title, id, content) values('%s', '%s', '%s')", Db.JSP_BOARD,w.title,w.id,w.content);	
			st.executeUpdate(sql);
			st.close();		// [고정-4]
			con.close();	// [고정-5]
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

	//삭제
	public void del(String delNum) {
		try {
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);	// [고정-1]
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);	// [고정-2]			
			st=con.createStatement();	// [고정-3]
			String sql = String.format("delete from %s where num=%s", Db.JSP_BOARD,delNum);
			st.executeUpdate(sql);
			st.close();		// [고정-4]
			con.close();	// [고정-5]
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//수정
	public void edit(Dto d, String editNum) {
		try {
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);			
			st=con.createStatement();
			String sql = String.format("update %s set title='%s', content='%s' where num=%s", Db.JSP_BOARD, d.title, d.content,editNum);
			st.executeUpdate(sql);
			st.close();		// [고정-4]
			con.close();	// [고정-5]
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//페이징 처리
	//1.데이터 행의 총 개수
	public int getPostCount() {
		int count = 0;
		try {
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);			
			st=con.createStatement();
			String sql = String.format("select count(*) from %s", Db.JSP_BOARD);
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
			st.close();		// [고정-4]
			con.close();	// [고정-5]
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//2.총 페이지 개수	[1],[2],[3]...
	public int getTotalPageCount() {
		int totalPageCount = 0;
		//현재 jsp_board에 있는 데이터 총 개수를 count에 넣기
		int count = getPostCount();
		if(count % Page.PER_PAGE == 0) {
			totalPageCount = count / Page.PER_PAGE;
		}	else {
			totalPageCount = count / Page.PER_PAGE+1;			
		}
		return totalPageCount;
	}
}
