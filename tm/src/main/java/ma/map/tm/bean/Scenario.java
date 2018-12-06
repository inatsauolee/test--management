package ma.map.tm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Scenario {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idScenario;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private Date dateCreation;

	@ManyToOne(fetch = FetchType.LAZY)
    private Project project;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User creator;

	public Scenario() {
	}
	
	public Scenario(String title, String description, Date date_creation, Project project) {
		this.title = title;
		this.description = description;
		this.dateCreation = date_creation;
		this.project = project;
	}
	
	public int getIdScenario() {
		return idScenario;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date date_creation) {
		this.dateCreation = date_creation;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String toString() {
		return "Scenario [id_p=" + idScenario + ", title=" + title + ", description=" + description +  "]";
	}

}
