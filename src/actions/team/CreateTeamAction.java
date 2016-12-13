package actions.team;

import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import bean.domain.TeamBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import smallTools.Time;
import smallTools.TimeImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by geyao on 2016/12/13.
 */
public class CreateTeamAction implements ServletRequestAware, ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	//以下从请求中获取
	private String teamName;
	private String description;
	private Integer memberMax;
	private Integer creatorId;

	private TeamBean teamBean;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TeamBean getTeamBean() {
		return teamBean;
	}

	public void setTeamBean(TeamBean teamBean) {
		this.teamBean = teamBean;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMemberMax() {
		return memberMax;
	}

	public void setMemberMax(Integer memberMax) {
		this.memberMax = memberMax;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * Sets the HTTP request object in implementing classes.
	 *
	 * @param request the HTTP request.
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Sets the HTTP response object in implementing classes.
	 *
	 * @param response the HTTP response.
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}


	public String createTeam() throws Exception {
		try {
			TeamDAO teamDAO = new TeamDAOImpl();
			TeamBean teamBean = new TeamBean();
			Time time = new TimeImpl();

			teamBean.setCreateDate(time.getTime());
			if (description != null && description.equals(""))
				teamBean.setDescription(description);
			else
				teamBean.setDescription("");
			if (memberMax > 0)
				teamBean.setMemberMax(memberMax);
			else
				teamBean.setMemberMax(0);
			teamBean.setTeamName(teamName);
			teamBean.setCreatorId(creatorId);

			this.id = teamDAO.addTeam(teamBean);
			teamBean.setId(this.getId());
			setTeamBean(teamBean);
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "fail";
		}

	}

	public String appCreateTeam() throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		if (createTeam().equals("success")){

			jsonObject.put("teamBean", getTeamBean());
			jsonObject.put("result", "success");
			jsonArray.add(jsonObject);
			this.response.setCharacterEncoding("UTF-8");
			this.response.getWriter().write(jsonArray.toString());
			return "success";
		}else {
			jsonObject.put("result", "fail");
			jsonArray.add(jsonObject);
			this.response.setCharacterEncoding("UTF-8");
			this.response.getWriter().write(jsonArray.toString());
			return "fail";
		}
	}
}
