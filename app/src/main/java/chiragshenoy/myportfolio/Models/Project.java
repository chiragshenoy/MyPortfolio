package chiragshenoy.myportfolio.Models;

/**
 * Created by Chirag Shenoy on 07-Feb-16.
 */
public class Project {

    private String projectTitle;
    private String projectSubTitle;
    private Integer projectIcon;

    public Project(String title, String subtitle, Integer icon) {
        this.projectIcon = icon;
        this.projectTitle = title;
        this.projectSubTitle = subtitle;
    }

    public Integer getProjectIcon() {
        return projectIcon;
    }

    public String getProjectSubTitle() {
        return projectSubTitle;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectIcon(Integer projectIcon) {
        this.projectIcon = projectIcon;
    }

    public void setProjectSubTitle(String projectSubTitle) {
        this.projectSubTitle = projectSubTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
}
