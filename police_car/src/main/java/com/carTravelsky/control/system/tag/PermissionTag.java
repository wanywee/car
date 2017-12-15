package com.carTravelsky.control.system.tag;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.carTravelsky.bean.system.CarSysUserDO;
import com.stopec.web.context.SessionContext;

public class PermissionTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String link;
	private String type;
	private Boolean reverse = Boolean.valueOf(false);

	private void otherDoStartTagOperations() {
	}

	private void otherDoEndTagOperations() {
	}

	private void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws IOException {
		bodyContent.writeOut(out);

		bodyContent.clearBody();
	}

	public int doStartTag() throws JspException {
		otherDoStartTagOperations();
		if (theBodyShouldBeEvaluated()) {
			return 2;
		}
		return 0;
	}

	public int doEndTag() throws JspException {
		otherDoEndTagOperations();
		if (shouldEvaluateRestOfPageAfterEndTag()) {
			return 6;
		}
		return 5;
	}

	public int doAfterBody() throws JspException {
		try {
			BodyContent bodyCont = getBodyContent();
			JspWriter out = bodyCont.getEnclosingWriter();

			writeTagBodyContent(out, bodyCont);
		} catch (Exception ex) {
			handleBodyContentException(ex);
		}
		if (theBodyShouldBeEvaluatedAgain()) {
			return 2;
		}
		return 0;
	}

	private void handleBodyContentException(Exception ex) throws JspException {
		throw new JspException("Error in PermissionTag tag", ex);
	}

	private boolean shouldEvaluateRestOfPageAfterEndTag() {
		return true;
	}

	private boolean theBodyShouldBeEvaluatedAgain() {
		return false;
	}

	private boolean theBodyShouldBeEvaluated() {
		CarSysUserDO userInfo = (CarSysUserDO) SessionContext.Attribute.get((HttpServletRequest) this.pageContext.getRequest(), "carSysUserDOLogin");
		boolean hasPermission = userInfo.matches(this.link, this.type);
		if ((this.reverse != null) && (this.reverse.booleanValue())) {
			return !hasPermission;
		}
		return hasPermission;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean isReverse() {
		return this.reverse;
	}

	public void setReverse(Boolean reverse) {
		this.reverse = reverse;
	}
}
