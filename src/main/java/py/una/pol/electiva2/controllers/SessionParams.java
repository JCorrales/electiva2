package py.una.pol.electiva2.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8677551534437698958L;
	private Integer activePage = 0;

	public Integer getActivePage() {

		return activePage;
	}

	public void setActivePage(Integer activePage) {

		System.out.println("active page" + activePage);
		this.activePage = activePage;
	}
}
