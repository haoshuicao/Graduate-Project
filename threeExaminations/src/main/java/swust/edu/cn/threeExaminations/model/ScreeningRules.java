package swust.edu.cn.threeExaminations.model;

public class ScreeningRules {
    private Integer scruId;

    private String scruIdnumber;

    private String scruName;

    private String scruHouseholdregister;

    private String scruNowlivetown;

    private String scruNowlivevillage;

    private String scruLivestate;

    private String scruMarriagecertificateid;

    private String scruIssendbackcheck;

    public Integer getScruId() {
        return scruId;
    }

    public void setScruId(Integer scruId) {
        this.scruId = scruId;
    }

    public String getScruIdnumber() {
        return scruIdnumber;
    }

    public void setScruIdnumber(String scruIdnumber) {
        this.scruIdnumber = scruIdnumber == null ? null : scruIdnumber.trim();
    }

    public String getScruName() {
        return scruName;
    }

    public void setScruName(String scruName) {
        this.scruName = scruName == null ? null : scruName.trim();
    }

    public String getScruHouseholdregister() {
        return scruHouseholdregister;
    }

    public void setScruHouseholdregister(String scruHouseholdregister) {
        this.scruHouseholdregister = scruHouseholdregister == null ? null : scruHouseholdregister.trim();
    }

    public String getScruNowlivetown() {
        return scruNowlivetown;
    }

    public void setScruNowlivetown(String scruNowlivetown) {
        this.scruNowlivetown = scruNowlivetown == null ? null : scruNowlivetown.trim();
    }

    public String getScruNowlivevillage() {
        return scruNowlivevillage;
    }

    public void setScruNowlivevillage(String scruNowlivevillage) {
        this.scruNowlivevillage = scruNowlivevillage == null ? null : scruNowlivevillage.trim();
    }

    public String getScruLivestate() {
        return scruLivestate;
    }

    public void setScruLivestate(String scruLivestate) {
        this.scruLivestate = scruLivestate == null ? null : scruLivestate.trim();
    }

    public String getScruMarriagecertificateid() {
        return scruMarriagecertificateid;
    }

    public void setScruMarriagecertificateid(String scruMarriagecertificateid) {
        this.scruMarriagecertificateid = scruMarriagecertificateid == null ? null : scruMarriagecertificateid.trim();
    }

    public String getScruIssendbackcheck() {
        return scruIssendbackcheck;
    }

    public void setScruIssendbackcheck(String scruIssendbackcheck) {
        this.scruIssendbackcheck = scruIssendbackcheck == null ? null : scruIssendbackcheck.trim();
    }
}