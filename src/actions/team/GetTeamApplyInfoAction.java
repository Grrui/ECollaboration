package actions.team;

import DAO.studentDAO.StudentDAO;
import DAO.studentDAO.StudentDaoImpl;
import DAO.teamDAO.TeamDAO;
import DAO.teamDAO.TeamDAOImpl;
import bean.domain.StudentBean;
import bean.domain.TeamBean;
import bean.domain.UserBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * 获取所有的加入团队的申请
 * 参数：无
 *       从session获取
 * 返回：1.ArrayList teamApplyInfos
 *                 1.1: teamBean
 *                 1.2: ArrayList<StudentBean> studentBeans
 * Created by GR on 2016/12/24.
 * ok
 */
public class GetTeamApplyInfoAction implements SessionAware,ServletResponseAware,ServletRequestAware{

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    private ArrayList teamApplyInfos;
    private TeamBean teamBean;
    private ArrayList<StudentBean> studentBeans;

    public ArrayList<ArrayList> getTeamApplyInfos() {
        return teamApplyInfos;
    }

    public void setTeamApplyInfos(ArrayList<ArrayList> teamApplyInfos) {
        this.teamApplyInfos = teamApplyInfos;
    }

    public TeamBean getTeamBean() {
        return teamBean;
    }

    public void setTeamBean(TeamBean teamBean) {
        this.teamBean = teamBean;
    }

    public ArrayList<StudentBean> getStudentBeans() {
        return studentBeans;
    }

    public void setStudentBeans(ArrayList<StudentBean> studentBeans) {
        this.studentBeans = studentBeans;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
        this.response.setCharacterEncoding("UTF-8");
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getTeamApplyInfo(){
        StudentDAO studentDAO = new StudentDaoImpl();
        ArrayList teamApplyInfos = new ArrayList<>();
        ArrayList<StudentBean> studentBeans = new ArrayList<>();

        TeamDAO teamDAO = new TeamDAOImpl();
        UserBean userBean = (UserBean)session.get("userBean");
        System.out.println("登录的id"+userBean.getId());
        int studentId = userBean.getId();
        try{
            ArrayList<Integer> teamIds = teamDAO.getTeamIdListByStudentId(studentId);
            if(teamIds==null){
                System.out.println("空");                    //加处理程序
                return "fail";
            }
            for(int i = 0;i<teamIds.size(); i++){
                TeamBean teamBean = teamDAO.getTeamInfo(teamIds.get(i));
                teamApplyInfos.add(teamBean);
                ArrayList<Integer> studentIds = teamDAO.getApplyStudentIdByTeamId(teamIds.get(i));
                for(int j = 0; j<studentIds.size(); j++){
                    StudentBean studentBean = studentDAO.getInfoById(studentIds.get(j));
                    studentBeans.add(studentBean);
                }
                teamApplyInfos.add(studentBeans);
            }
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    public void appGetTeamApplyInfo() throws Exception{
        JSONObject jsonObject = new JSONObject();
        if (getTeamApplyInfo().equals("success")){

            jsonObject.put("teamApplyInfos", getTeamApplyInfos());
            jsonObject.put("result", "success");

            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }else {
            jsonObject.put("result", "fail");
            this.response.getWriter().write(jsonObject.toString());
            this.response.getWriter().flush();
            this.response.getWriter().close();
        }
    }

}
