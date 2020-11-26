package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public static boolean insert( int amount, String desc, int type) {
		PreparedStatement stmt = null;
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO ers_reimbursements(reimb_amount, reimb_description, reimb_status_id, reimb_type_id) \r\n" + 
					"			VALUES (?,?,3, ?);";
			
			
			
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setString(2, desc);
			stmt.setInt(3, type);
			
			
//			if (!stmt.execute()) {
//				return false;
//			}
		} catch (SQLException ex) {
			log.warn("Unable to insert reimbursement", ex);
			return false;
		} finally {
			// We want to close resources (in this case stmt)
			CloseStreams.close(stmt);
		}
		return true;
		
}

}
