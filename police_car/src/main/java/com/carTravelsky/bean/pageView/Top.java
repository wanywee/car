package com.carTravelsky.bean.pageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Top implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -8763043171414934528L;
	private String tabId;
	private String tabName;
	private Boolean actionFlag=false;
	private List<TopRow> rankRowList = new ArrayList<>();

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}



	public Boolean getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(Boolean actionFlag) {
		this.actionFlag = actionFlag;
	}

	public List<TopRow> getRankRowList() {
		return rankRowList;
	}

	public void setRankRowList(List<TopRow> rankRowList) {
		this.rankRowList = rankRowList;
	}

}
