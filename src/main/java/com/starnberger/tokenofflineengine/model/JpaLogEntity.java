/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.db.jpa.BasicLogEventEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Roman Kaufmann
 *
 */
@Entity
@Table(name = "LogEntry")
public class JpaLogEntity extends BasicLogEventEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -350065410572663755L;

	public JpaLogEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JpaLogEntity(LogEvent wrappedEvent) {
		super(wrappedEvent);
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(generator = "le-uuid")
	@GenericGenerator(name = "le-uuid", strategy = "uuid2")
	private String id;
	@Version
	@Column(name = "version")
	private int version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
