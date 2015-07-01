package com.dm.htmlUnit.test;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class WebRequestTest {
	
	@Test
	public void testWebRequest() throws Exception{
		String url = "http://news.baidu.com";
		String refer = "http://www.baidu.com";
		WebClient wc = new WebClient();
		URL link = new URL(url);
		
		WebRequest request = new WebRequest(link);
		request.setCharset("UTF-8");
		request.setProxyPort(80);
		request.setAdditionalHeader("Refer",refer);
		request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		
		wc.getCookieManager().setCookiesEnabled(true);
		
		File file = new File("k:\\files\\page.txt");
		
		HtmlPage page = wc.getPage(request);
		page.save(file);
		CookieManager CM = wc.getCookieManager();
		Set<Cookie> cookies = CM.getCookies();
		for(Cookie c:cookies){
			System.out.println(c);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~");
//		System.out.println(content);
		
//		System.out.println(request);
	}
}
