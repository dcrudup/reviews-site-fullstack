package reviewssitefullstack;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	private String author;

	@ManyToOne
	private Review review;

	private String content;

	@Id
	@GeneratedValue
	private long id;

	public Comment(String author, Review review, String content) {
		this.author = author;
		this.review = review;
		this.content = content;
	}

	public Comment() {

	}

	public long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public Review getReview() {
		return review;
	}

	public String getContent() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
