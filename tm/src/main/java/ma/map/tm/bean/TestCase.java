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
public class TestCase {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTestCase;

	@Column
	private String title;
	
	@Column
	private String description;

	@Column
	private String status;

	@Column
	private int duration;
	
	@Column
	private Date dateCreation;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
    private List<Execution> executions;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Scenario scenario;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User creator;
		
	public int getIdTestCase() {
		return idTestCase;
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dc) {
		this.dateCreation = dc;
	}
	
	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String toString() {
		return "TestCase [id_tc=" + idTestCase + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", duration=" + duration + "]";
	}
	
}
