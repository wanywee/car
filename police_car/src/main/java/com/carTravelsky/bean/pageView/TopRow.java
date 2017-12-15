package com.carTravelsky.bean.pageView;

import java.io.Serializable;

public class TopRow implements Serializable {
       /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 4683438514510026202L;
	private String seqImgPath;
    private String display;
    private String belongDepName;
    private Integer displayCount;
	public String getSeqImgPath() {
		return seqImgPath;
	}
	public void setSeqImgPath(String seqImgPath) {
		this.seqImgPath = seqImgPath;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getBelongDepName() {
		return belongDepName;
	}
	public void setBelongDepName(String belongDepName) {
		this.belongDepName = belongDepName;
	}
	public Integer getDisplayCount() {
		return displayCount;
	}
	public void setDisplayCount(Integer displayCount) {
		this.displayCount = displayCount;
	}
       
       
}
