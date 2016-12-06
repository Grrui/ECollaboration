package DAO.teacherDAO;

import bean.domain.*;

import java.sql.SQLException;

public interface TeacherDAO {

	/**
	 * 添加老师
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean addTeacher(TeacherBean teacherBean) throws SQLException;


	/**
	 * 更新老师信息
	 * @param teacherBean
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateInfo(TeacherBean teacherBean) throws SQLException;


	/**
	 * 删除老师信息
	 * @param teacherId
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean deleteById(Integer teacherId) throws SQLException;
	
	/**
	 * 获取TeacherBean，通过teacherId
	 * @param teacherId
	 * @return TeacherBean
	 * @throws SQLException
	 */
	public TeacherBean getInfoById(Integer teacherId) throws SQLException;

}
