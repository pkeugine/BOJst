package kr.or.connect.review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RevDAO {
	private static RevDAO instance = new RevDAO();
	private RevDAO() {};
	public static RevDAO getInstance() {
		return instance;
	}
	

	String driver = "oracle.jdbc.driver.OracleDriver";
	String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
	String dbuser = "hr";
	String dbpassword = "hr";
	
	
	
	public List<Rev> getRevs(Integer locationId) {
		List<Rev> list = new ArrayList<>();
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String select = "SELECT * FROM REVIEWS WHERE place_id=? order by DATE_CRE desc";
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
				PreparedStatement ps = conn.prepareStatement(select)) {
			ps.setInt(1,locationId);
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String place = rs.getString("place");
					String userId = rs.getString("user_id");
					int grade = rs.getInt("grade");
					String opinion = rs.getString("opinion");
					String date = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date_cre"));
					String imagePath = rs.getString("image_path");
					int placeId = rs.getInt("place_id");
					Rev rev = new Rev(place,userId,placeId,grade,opinion);
					rev.setDate(date);
					rev.setImagePath(imagePath);
					
					list.add(rev);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public int addRev(Rev rev) {
		int insertCount = 0;
		
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String insert = "INSERT INTO REVIEWS(place,user_id,grade,opinion,imagepath) VALUES ( ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
				PreparedStatement ps = conn.prepareStatement(insert)){
			ps.setString(1,rev.getPlace());
			ps.setString(2,rev.getUserId());
			ps.setInt(3,rev.getGrade());
			ps.setString(4,rev.getOpinion());
			ps.setString(5,rev.getImagePath());
			
			insertCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertCount;
	}
		

	public int deleteRev(String userId, String opinion) {
		int deletCount = 0;
		
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String delete = "DELETE FROM REVIEWS WHERE user_id=? and opinion=?";
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
				PreparedStatement ps = conn.prepareStatement(delete)){
			ps.setString(1,userId);
			ps.setString(2,opinion);
			deletCount = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deletCount;
	}
		
	

}
