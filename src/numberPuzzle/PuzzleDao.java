package numberPuzzle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exam01.ImgTest;

public class PuzzleDao {
	private final String myDb = "jdbc:postgresql://localhost:5432/postgres";
	private final String userName = "postgres";
	private final String pass = "password";
	/*
	 * DBアクセス
	 */
	public void insertResult(String name,Long score,Long moveCount) throws Exception {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(myDb,userName,pass);

			stmt = conn.createStatement();
			String sql = "INSERT INTO PUZZLE_SCORES (name,score,move_count) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setLong(2, score);
			ps.setLong(3, moveCount);

			ps.executeUpdate();

		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public List <PuzzleScores> getResults(int number) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		PuzzleScores score = null;
		List <PuzzleScores> list = new ArrayList<PuzzleScores>();

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(myDb,userName,pass);

			String sql = "SELECT * FROM puzzle_scores ORDER BY score DESC  LIMIT ? OFFSET 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, number);
			rs = ps.executeQuery();

			while (rs.next()) {
				score = new PuzzleScores();
				score.setName(rs.getString(2));
				score.setScore(rs.getString(3));
				score.setMoveCount(rs.getString(4));
				list.add(score);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

	public List <ImgTest> getImageTest() throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		ImgTest img = null;
		List <ImgTest> list = new ArrayList<ImgTest>();

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(myDb,userName,pass);
			stmt = conn.createStatement();
			String sql = "SELECT * FROM img_test  LIMIT 3 OFFSET 0";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				img = new ImgTest();
				img.setFileName(rs.getString(1));
				img.setBody(rs.getBinaryStream(2));
				list.add(img);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		}
		return list;
	}

	}
