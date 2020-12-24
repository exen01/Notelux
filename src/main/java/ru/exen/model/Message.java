package ru.exen.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Please fill the message")
	@Length(max = 2048, message = "Message too long (more then 2kB)")
	private String text;
	@Length(max = 255, message = "Message too long (more then 255)")
	private String tag;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;

	private String filename;
	
	public Message(String text, String tag, User author) {
		this.author = author;
		this.text = text;
		this.tag = tag;
	}
	
	public String getAuthorName() {
		return author != null ? author.getUsername() : "<none>";
	}
}
