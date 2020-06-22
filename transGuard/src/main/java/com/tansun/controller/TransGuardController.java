/**
 * 
 */
package com.tansun.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tansun.util.XmlUtils;


/**
 * @author kliu
 *
 */

@Controller
@RequestMapping(value = "/guard")
public class TransGuardController {
	
	@RequestMapping(value = "receive", method = RequestMethod.POST)
	public void reciveTrans(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("请求1次");
		Document doc = XmlUtils.getDoc("httpTest.xml");
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().print(doc.asXML());
	}
	
}
