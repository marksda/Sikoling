package org.Sikoling.ejb.abstraction.entity.onlyoffice;

import java.io.Serializable;
import java.util.List;

public class CommentGroups implements Serializable {

	private static final long serialVersionUID = 7585177518207924696L;
	private final List<String> view;
    private final List<String> edit;
    private final List<String> remove;
    
    public CommentGroups() {
    	this.view = null;
		this.edit = null;
		this.remove = null;
    }
    
	public CommentGroups(List<String> view, List<String> edit, List<String> remove) {
		this.view = view;
		this.edit = edit;
		this.remove = remove;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getView() {
		return view;
	}

	public List<String> getEdit() {
		return edit;
	}

	public List<String> getRemove() {
		return remove;
	}

}
