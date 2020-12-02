package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dto.UsersDto;
import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.util.CloseStreams;
import com.revature.util.ConnectionUtil;

public class ReimbursementDao {

	// create new impl
	private static Logger log = Logger.getLogger(ReimbursementDao.class);

	// insert into users

	public static int insert(int amount, String desc, int type, int name) {
		PreparedStatement stmt = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO ers_reimbursements(reimb_amount, reimb_description,reimb_author_id,reimb_resolver_id, reimb_status_id, reimb_type_id) \r\n"
					+ "			VALUES (?,?,?,1,3, ?);";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setString(2, desc);
			stmt.setInt(3, name);
			stmt.setInt(4, type);

			stmt.execute();
			log.info("reimb insert success");
			return 7;

		} catch (SQLException ex) {
			log.warn("Unable to insert reimbursement", ex);
			return 0;
		} finally {
			// We want to close resources (in this case stmt)
			CloseStreams.close(stmt);
		}

	}

	// find all reimbursements
	public static List<Reimbursement> findAll() {

		List<Reimbursement> list = new ArrayList<Reimbursement>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursements;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				int amount = rs.getInt("reimb_amount");
				LocalDateTime subDate = rs.getTimestamp("reimb_submitted").toLocalDateTime();
				LocalDateTime resDate = rs.getTimestamp("reimb_resolved").toLocalDateTime();
				String description = rs.getString("reimb_description");
				int author = rs.getInt("reimb_author_id");
				int resolver = rs.getInt("reimb_resolver_id");
				int status = rs.getInt("reimb_status_id");
				int type = rs.getInt("reimb_type_id");

				Reimbursement re = new Reimbursement(id, amount, subDate, resDate, description, author, resolver,
						status, type);
				list.add(re);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.warn("Unable to retrieve all users");
		}

		return list;
	}

}
