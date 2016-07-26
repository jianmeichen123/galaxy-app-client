package com.galaxyinternet.platform.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.model.user.User;

@SuppressWarnings("serial")
public class AclTag extends TagSupport
{
	private String resourceMark;

	public String getResourceMark()
	{
		return resourceMark;
	}

	public void setResourceMark(String resourceMark)
	{
		this.resourceMark = resourceMark;
	}

	@Override
	public int doStartTag() throws JspException
	{
		try
		{
			
			boolean hasResource = false;
			HttpSession session = pageContext.getSession();
			User user = (User)session.getAttribute(Constants.SESSION_USER_KEY);
			List<PlatformResource> resources = user.getAllResourceToUser();
			if(resources != null && resources.size() >0)
			{
				for(PlatformResource resource : resources)
				{
					if(resourceMark != null && resourceMark.equals(resource.getResourceMark()))
					{
						hasResource = true;
						break;
					}
				}
			}
			String start = "<div";
			if(!hasResource)
			{
				start += " style=\"display:none;\"";
			}
			start +=">";
			JspWriter out = pageContext.getOut();
			out.write(start);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException
	{
		try
		{
			JspWriter out = pageContext.getOut();
			out.write("</div>");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	
}
