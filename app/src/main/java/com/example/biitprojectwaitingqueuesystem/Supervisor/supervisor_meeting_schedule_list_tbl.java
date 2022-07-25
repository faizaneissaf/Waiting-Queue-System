package com.example.biitprojectwaitingqueuesystem.Supervisor;

public class supervisor_meeting_schedule_list_tbl {
    public int spm_mid;
    public int spm_groupno;
    public String spm_stdregno;
    public String spm_stdname;
    public String spm_stdgender;
    public String spm_stdcls;
    public String spm_stdsupervisor;
    public String spm_stdprojecttitle;
    public String spm_stdtechnology;
    public String spm_stdtime;
    public String spm_stddate;
    public String spm_stdfyp;
    public supervisor_meeting_schedule_list_tbl(){};

    public supervisor_meeting_schedule_list_tbl(int spm_mid, int spm_groupno, String spm_stdregno, String spm_stdname, String spm_stdgender, String spm_stdcls, String spm_stdsupervisor, String spm_stdprojecttitle, String spm_stdtechnology, String spm_stdtime, String spm_stddate, String spm_stdfyp) {
        this.spm_mid = spm_mid;
        this.spm_groupno = spm_groupno;
        this.spm_stdregno = spm_stdregno;
        this.spm_stdname = spm_stdname;
        this.spm_stdgender = spm_stdgender;
        this.spm_stdcls = spm_stdcls;
        this.spm_stdsupervisor = spm_stdsupervisor;
        this.spm_stdprojecttitle = spm_stdprojecttitle;
        this.spm_stdtechnology = spm_stdtechnology;
        this.spm_stdtime = spm_stdtime;
        this.spm_stddate = spm_stddate;
        this.spm_stdfyp = spm_stdfyp;
    }

    public int getSpm_mid() {
        return spm_mid;
    }

    public void setSpm_mid(int spm_mid) {
        this.spm_mid = spm_mid;
    }

    public int getSpm_groupno() {
        return spm_groupno;
    }

    public void setSpm_groupno(int spm_groupno) {
        this.spm_groupno = spm_groupno;
    }

    public String getSpm_stdregno() {
        return spm_stdregno;
    }

    public void setSpm_stdregno(String spm_stdregno) {
        this.spm_stdregno = spm_stdregno;
    }

    public String getSpm_stdname() {
        return spm_stdname;
    }

    public void setSpm_stdname(String spm_stdname) {
        this.spm_stdname = spm_stdname;
    }

    public String getSpm_stdgender() {
        return spm_stdgender;
    }

    public void setSpm_stdgender(String spm_stdgender) {
        this.spm_stdgender = spm_stdgender;
    }

    public String getSpm_stdcls() {
        return spm_stdcls;
    }

    public void setSpm_stdcls(String spm_stdcls) {
        this.spm_stdcls = spm_stdcls;
    }

    public String getSpm_stdsupervisor() {
        return spm_stdsupervisor;
    }

    public void setSpm_stdsupervisor(String spm_stdsupervisor) {
        this.spm_stdsupervisor = spm_stdsupervisor;
    }

    public String getSpm_stdprojecttitle() {
        return spm_stdprojecttitle;
    }

    public void setSpm_stdprojecttitle(String spm_stdprojecttitle) {
        this.spm_stdprojecttitle = spm_stdprojecttitle;
    }

    public String getSpm_stdtechnology() {
        return spm_stdtechnology;
    }

    public void setSpm_stdtechnology(String spm_stdtechnology) {
        this.spm_stdtechnology = spm_stdtechnology;
    }

    public String getSpm_stdtime() {
        return spm_stdtime;
    }

    public void setSpm_stdtime(String spm_stdtime) {
        this.spm_stdtime = spm_stdtime;
    }

    public String getSpm_stddate() {
        return spm_stddate;
    }

    public void setSpm_stddate(String spm_stddate) {
        this.spm_stddate = spm_stddate;
    }

    public String getSpm_stdfyp() {
        return spm_stdfyp;
    }

    public void setSpm_stdfyp(String spm_stdfyp) {
        this.spm_stdfyp = spm_stdfyp;
    }
}
