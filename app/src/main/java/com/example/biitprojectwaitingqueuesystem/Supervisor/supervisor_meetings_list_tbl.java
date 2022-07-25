package com.example.biitprojectwaitingqueuesystem.Supervisor;

public class supervisor_meetings_list_tbl {
    public int sp_mid;
    public int sp_groupno;
    public String sp_stdregno;
    public String sp_stdname;
    public String sp_stdgender;
    public String sp_stdcls;
    public String sp_stdsupervisor;
    public String sp_stdprojecttitle;
    public String sp_stdtechnology;
    public String sp_stdtime;
    public String sp_stddate;
    public String sp_stdfyp;
    public  supervisor_meetings_list_tbl(){};

    public supervisor_meetings_list_tbl(int sp_mid, int sp_groupno, String sp_stdregno, String sp_stdname, String sp_stdgender, String sp_stdcls, String sp_stdsupervisor, String sp_stdprojecttitle, String sp_stdtechnology, String sp_stdtime, String sp_stddate, String sp_stdfyp) {
        this.sp_mid = sp_mid;
        this.sp_groupno = sp_groupno;
        this.sp_stdregno = sp_stdregno;
        this.sp_stdname = sp_stdname;
        this.sp_stdgender = sp_stdgender;
        this.sp_stdcls = sp_stdcls;
        this.sp_stdsupervisor = sp_stdsupervisor;
        this.sp_stdprojecttitle = sp_stdprojecttitle;
        this.sp_stdtechnology = sp_stdtechnology;
        this.sp_stdtime = sp_stdtime;
        this.sp_stddate = sp_stddate;
        this.sp_stdfyp = sp_stdfyp;
    }

    public int getSp_mid() {
        return sp_mid;
    }

    public void setSp_mid(int sp_mid) {
        this.sp_mid = sp_mid;
    }

    public int getSp_groupno() {
        return sp_groupno;
    }

    public void setSp_groupno(int sp_groupno) {
        this.sp_groupno = sp_groupno;
    }

    public String getSp_stdregno() {
        return sp_stdregno;
    }

    public void setSp_stdregno(String sp_stdregno) {
        this.sp_stdregno = sp_stdregno;
    }

    public String getSp_stdname() {
        return sp_stdname;
    }

    public void setSp_stdname(String sp_stdname) {
        this.sp_stdname = sp_stdname;
    }

    public String getSp_stdgender() {
        return sp_stdgender;
    }

    public void setSp_stdgender(String sp_stdgender) {
        this.sp_stdgender = sp_stdgender;
    }

    public String getSp_stdcls() {
        return sp_stdcls;
    }

    public void setSp_stdcls(String sp_stdcls) {
        this.sp_stdcls = sp_stdcls;
    }

    public String getSp_stdsupervisor() {
        return sp_stdsupervisor;
    }

    public void setSp_stdsupervisor(String sp_stdsupervisor) {
        this.sp_stdsupervisor = sp_stdsupervisor;
    }

    public String getSp_stdprojecttitle() {
        return sp_stdprojecttitle;
    }

    public void setSp_stdprojecttitle(String sp_stdprojecttitle) {
        this.sp_stdprojecttitle = sp_stdprojecttitle;
    }

    public String getSp_stdtechnology() {
        return sp_stdtechnology;
    }

    public void setSp_stdtechnology(String sp_stdtechnology) {
        this.sp_stdtechnology = sp_stdtechnology;
    }

    public String getSp_stdtime() {
        return sp_stdtime;
    }

    public void setSp_stdtime(String sp_stdtime) {
        this.sp_stdtime = sp_stdtime;
    }

    public String getSp_stddate() {
        return sp_stddate;
    }

    public void setSp_stddate(String sp_stddate) {
        this.sp_stddate = sp_stddate;
    }

    public String getSp_stdfyp() {
        return sp_stdfyp;
    }

    public void setSp_stdfyp(String sp_stdfyp) {
        this.sp_stdfyp = sp_stdfyp;
    }
}
