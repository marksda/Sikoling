package org.Sikoling.ejb.abstraction.entity.onlyoffice;

import java.io.Serializable;
import java.util.List;

public class OnlyofficeUser implements Serializable {

	private static final long serialVersionUID = -7948574642444903806L;
	private final String id;
    private final String name;
    private final String email;
    private final String group;
    private final List<String> reviewGroups;
    private final CommentGroups commentGroups;
    private final Boolean favorite;
    private final List<String> deniedPermissions;
    private final List<String> descriptions;
    private final Boolean templates;
    private final List<String> userInfoGroups;
    
	public OnlyofficeUser(String id, String name, String email, String group, List<String> reviewGroups,
			CommentGroups commentGroups, Boolean favorite, List<String> deniedPermissions, List<String> descriptions,
			Boolean templates, List<String> userInfoGroups) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.group = group;
		this.reviewGroups = reviewGroups;
		this.commentGroups = commentGroups;
		this.favorite = favorite;
		this.deniedPermissions = deniedPermissions;
		this.descriptions = descriptions;
		this.templates = templates;
		this.userInfoGroups = userInfoGroups;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getGroup() {
		return group;
	}

	public List<String> getReviewGroups() {
		return reviewGroups;
	}

	public CommentGroups getCommentGroups() {
		return commentGroups;
	}

	public Boolean getFavorite() {
		return favorite;
	}

	public List<String> getDeniedPermissions() {
		return deniedPermissions;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public Boolean getTemplates() {
		return templates;
	}

	public List<String> getUserInfoGroups() {
		return userInfoGroups;
	}

}
