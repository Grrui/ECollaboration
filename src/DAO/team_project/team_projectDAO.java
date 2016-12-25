package DAO.team_project;

/**
 * Created by geyao on 2016/12/25.
 */
public interface team_projectDAO {
	/**
	 * 添加评价，通过team_projectId, taskId, int access
	 * @param team_projectId
	 * @param taskId
	 * @param access
	 * @return
	 * @throws Exception
	 */
	public boolean addAccess(int team_projectId, int taskId, int access) throws Exception;

	/**
	 * 获取team_projectId，通过teamId，projectId
	 * @param teamId
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public Integer getIdByTeamIdProjectId(int teamId, int projectId) throws Exception;

	/**
	 * 更新评价，通过teamId，projectId，taskID，access
	 * @param teamId
	 * @param projectId
	 * @param taskId
	 * @param access
	 * @return
	 * @throws Exception
	 */
	public boolean updateAccess(int teamId, int projectId, int taskId, int access)throws Exception;

	/**
	 * 获取评价，通过teamID，projectId，taskId
	 * @param teamId
	 * @param projectId
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public Integer getAccess(int teamId, int projectId, int taskId) throws Exception;

}
