package co.olivemedia.olivemediasampleproject.holders;

import java.io.Serializable;
import java.util.List;

public class MeetingsBaseHolder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Meetings> list;

	/**
	 * @return the list
	 */
	public List<Meetings> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Meetings> list) {
		this.list = list;
	}

}
