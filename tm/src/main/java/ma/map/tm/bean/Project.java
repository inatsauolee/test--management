package ma.map.tm.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProject;

	@Column
	private String name;
	
	@Column
	private String description;

	@Column
	private int prefix;

	@Column
	private Date dateCreation;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
    private List<Scenario> scenarios;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User creator;
	
	public Project() {
	}

	public Project(String name, String description, int prefix, Date date_creation, User creator) {
		this.name = name;
		this.description = description;
		this.prefix = prefix;
		this.dateCreation = date_creation;
		this.creator = creator;
	}
	
	public int getIdProject() {
		return idProject;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String title) {
		this.name = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrefix() {
		return prefix;
	}
	
	public void setPrefix(int duration) {
		this.prefix = duration;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date date_creation) {
		this.dateCreation = date_creation;
	}
	
	public List<Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String toString() {
		return "Project [idProject=" + idProject + ", title=" + name + ", description=" + description +  ", duration=" + prefix + "]";
	}

}
