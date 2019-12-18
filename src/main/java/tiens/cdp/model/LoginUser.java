package tiens.cdp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LoginUser implements Serializable {

    private String userid;
    private String username;
    private String password;
    private String cName;

    private List<ApiUrl> apis;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public List<ApiUrl> getApis() {
        return apis;
    }

    public void setApis(List<ApiUrl> apis) {
        this.apis = apis;
    }
}
