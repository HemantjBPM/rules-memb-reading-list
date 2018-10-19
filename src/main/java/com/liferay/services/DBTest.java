package com.liferay.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liferay.utils.CommonUtils;
import com.liferay.utils.LiferayConstant;

public class DBTest {

	public static void main(String[] args) {
			try (Connection connection = CommonUtils.getDBConnection("com.mysql.jdbc.Driver",
					"jdbc:mysql://localhost:3306/test", "root",
					"root"); 
					PreparedStatement preparedStatement = connection.prepareStatement("select typeOfGoal AS TypeOfGoal from goals_setting where userId = ? order by createDate DESC LIMIT 1");) {
				preparedStatement.setInt(1, Integer.parseInt("20156"));
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString(LiferayConstant.TYPEOFGOAL));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
