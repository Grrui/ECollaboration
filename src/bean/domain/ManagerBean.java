package bean.domain;

public class ManagerBean {
    private Integer id;
    private Integer character;  //0：超级管理员 1：普通管理员

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCharacter() {
        return character;
    }

    public void setCharacter(Integer character) {
        this.character = character;
    }
}
