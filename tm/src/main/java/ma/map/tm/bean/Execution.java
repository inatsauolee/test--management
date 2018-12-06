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
public class Execution {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idExecution;

	@Column
	private String result;
	
	@Column
	private String notes;

	@Column
	private int duration;
	
	@Column
	private Date dateExecution;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private TestCase testCase;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User executor;
	
	public Execution() {
	}
	
	public Execution(String result, String notes, int duration, Date dateExecution, TestCase testCase, User executor) {
		this.result = result;
		this.notes = notes;
		this.duration = duration;
		this.dateExecution = dateExecution;
		this.testCase = testCase;
		this.executor = executor;
	}

	public int getIdExecution() {
		return idExecution;
	}

	public void setIdExecution(int idExecution) {
		this.idExecution = idExecution;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getDateExecution() {
		return dateExecution;
	}

	public void setDateExecution(Date dateExecution) {
		this.dateExecution = dateExecution;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public User getExecutor() {
		return executor;
	}

	public void setExecutor(User executor) {
		this.executor = executor;
	}

	@Override
	public String toString() {
		return "Execution [idExecution=" + idExecution + ", result=" + result + ", notes=" + notes + ", duration="
				+ duration + ", dateExecution=" + dateExecution + ", testCase=" + testCase + ", executor=" + executor
				+ "]";
	}
}