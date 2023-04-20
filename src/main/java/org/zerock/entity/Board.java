package org.zerock.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String content;
	private String filename;
	private String filepath;
	
	@Column(name="viewcount",columnDefinition = "int default 0")
	private int viewcount;
	
	@Column(name="createddate", updatable=false)
	@CreationTimestamp
	private Timestamp createddate;
	
	@Column(name="updateddate")
	@UpdateTimestamp
	private Timestamp updateddate;
}
