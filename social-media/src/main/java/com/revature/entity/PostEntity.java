package com.revature.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "posts")
public class PostEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String text;
	private String imageUrl;
	@OneToMany(cascade = CascadeType.ALL)
	private List<PostEntity> comments;
	@ManyToOne
	private UserEntity author;
}
