package com.orgittrak.Models;

/**
 * Created by robin on 15/05/17.
 */

public class Organisation {

    private String login;
    private Integer id;
    private String url;
    private String repos_url;
    private String events_url;
    private String hooks_url;
    private String issues_url;
    private String members_url;
    private String public_members_url;
    private String avatar_url;
    private String description;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getrepos_url() {
        return repos_url;
    }

    public void setrepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getevents_url() {
        return events_url;
    }

    public void setevents_url(String events_url) {
        this.events_url = events_url;
    }

    public String gethooks_url() {
        return hooks_url;
    }

    public void sethooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public String getissues_url() {
        return issues_url;
    }

    public void setissues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public String getmembers_url() {
        return members_url;
    }

    public void setmembers_url(String members_url) {
        this.members_url = members_url;
    }

    public String getpublic_members_url() {
        return public_members_url;
    }

    public void setpublic_members_url(String public_members_url) {
        this.public_members_url = public_members_url;
    }

    public String getavatar_url() {
        return avatar_url;
    }

    public void setavatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}