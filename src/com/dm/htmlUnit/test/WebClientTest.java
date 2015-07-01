package com.dm.htmlUnit.test;

import java.net.URL;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class WebClientTest {
	
	@Test
	public void testWebClient(){
		final WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		try {
			final HtmlPage startPage = webClient.getPage("http://www.baidu.com");
			HtmlInput input = (HtmlInput) startPage.getElementById("kw");
			input.setValueAttribute("李德明");
			System.out.println(input.toString());
			
			//获取搜索按钮并点击
			HtmlInput btn = (HtmlInput) startPage.getElementById("su");
			HtmlPage page2 = btn.click();
			
			System.out.println(page2.asText());
			
//			List<HtmlAnchor> anchors = startPage.getAnchors();
//			for(HtmlAnchor ha:anchors){
//				System.out.println(ha.getBaseURI());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			webClient.closeAllWindows();
		}
	}
	
	@Test
	public void findElement() throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient.getPage("http://www.baidu.com");
		HtmlInput btn = (HtmlInput) page.getElementById("su");
		System.out.println(btn.getDefaultValue());
		
		webClient.closeAllWindows();
		
	}
	
	@Test
	public void getCookie() throws Exception{
		String  url="http://www.baidu.com";//想采集的网址
        String refer="http://www.cnblogs.com/";
        URL link=new URL(url); 
        WebClient wc=new WebClient();
        WebRequest request=new WebRequest(link); 
        request.setCharset("UTF-8");
        request.setProxyHost("120.120.120.x");
        request.setProxyPort(8080);
        request.setAdditionalHeader("Referer", refer);//设置请求报文头里的refer字段
        ////设置请求报文头里的User-Agent字段
        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        //wc.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        //wc.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
        //其他报文头字段可以根据需要添加
        wc.getCookieManager().setCookiesEnabled(true);//开启cookie管理
        wc.getOptions().setJavaScriptEnabled(true);//开启js解析。对于变态网页，这个是必须的
        wc.getOptions().setCssEnabled(true);//开启css解析。对于变态网页，这个是必须的。
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        wc.getOptions().setThrowExceptionOnScriptError(false);
        wc.getOptions().setTimeout(10000);
        //设置cookie。如果你有cookie，可以在这里设置
//        Set<Cookie> cookies=null;
//        Iterator<Cookie> i = cookies.iterator();
//        while (i.hasNext()) 
//        {
//            wc.getCookieManager().addCookie(i.next());
//        }
        //准备工作已经做好了
        HtmlPage page=null;
        page = wc.getPage(request);
        if(page==null)
        {
            System.out.println("采集 "+url+" 失败!!!");
            return ;
        }
        String content=page.asText();//网页内容保存在content里
        if(content==null)
        {
            System.out.println("采集 "+url+" 失败!!!");
            return ;
        }
        //搞定了
        CookieManager CM = wc.getCookieManager(); //WC = Your WebClient's name
        Set<Cookie> cookies_ret = CM.getCookies();//返回的Cookie在这里，下次请求的时候可能可以用上啦。
        for(Cookie c:cookies_ret){
        	System.out.println(c.getName());
        }
	}
	
//	public void findCookie() throws Exception{
//		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
//	    webClient.getOptions().setUseInsecureSSL(true);
//	    webClient.getOptions().setJavaScriptEnabled(false);
//            webClient.getOptions().setCssEnabled(false);
//	    HtmlPage page = null;
//            page = webClient.getPage("");
//            HtmlElement corpid = (HtmlElement) page.getElementById("id_corpid"); 
//	    HtmlElement corppw = (HtmlElement) page.getElementById("id_corppw");
//	    corpid.click();
//	    String username = ph.readValue("userName");
//	    corpid.type(username);
//	    corppw.click();
//	    String password = new String(dec.decodeBuffer(ph.readValue("password")));
//	    corppw.type(password);
//	    List<HtmlButton> loginBtn = (List<HtmlButton>) page.getByXPath("//div[@id='corp']/form/div[@class='span2 offset7 controls']/button");
//	    Page resultPage = loginBtn.get(0).click();
//	    String EHRCookie = HtmlUnitUtil.getCookieHeader(webClient);
//	    logger.info("获得openidpage cookie值： "+cookie);
//	    return cookie;
//	}
	
}
