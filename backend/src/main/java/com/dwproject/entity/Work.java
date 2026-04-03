package com.dwproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "works")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    private Integer score;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "is_excellent")
    private Boolean isExcellent;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "graded_at")
    private LocalDateTime gradedAt;

    @ManyToOne
    @JoinColumn(name = "graded_by")
    private User gradedBy;

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
        isExcellent = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Boolean getIsExcellent() { return isExcellent; }
    public void setIsExcellent(Boolean isExcellent) { this.isExcellent = isExcellent; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    public LocalDateTime getGradedAt() { return gradedAt; }
    public void setGradedAt(LocalDateTime gradedAt) { this.gradedAt = gradedAt; }
    public User getGradedBy() { return gradedBy; }
    public void setGradedBy(User gradedBy) { this.gradedBy = gradedBy; }
}
