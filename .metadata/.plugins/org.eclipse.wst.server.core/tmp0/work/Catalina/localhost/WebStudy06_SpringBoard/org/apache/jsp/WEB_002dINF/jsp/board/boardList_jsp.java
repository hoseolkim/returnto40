/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.90
 * Generated at: 2023-10-22 10:29:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class boardList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/00.tools/eGovFrameDev-4.0.0-64bit/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/WebStudy06_SpringBoard/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1695857270377L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table>\r\n");
      out.write("	<thead>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<th>글번호</th>\r\n");
      out.write("			<th>제목</th>\r\n");
      out.write("			<th>작성자</th>\r\n");
      out.write("			<th>작성일</th>\r\n");
      out.write("			<th>조회수</th>\r\n");
      out.write("		</tr>\r\n");
      out.write("	</thead>\r\n");
      out.write("	<tbody id=\"listBody\">\r\n");
      out.write("	</tbody>\r\n");
      out.write("	<tfoot>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td colspan=\"5\">\r\n");
      out.write("				<span id=\"pagingArea\"></span>\r\n");
      out.write("				<div id=\"searchUI\"  class=\"row g-3 d-flex justify-content-center\">\r\n");
      out.write("					<div class=\"col-auto\">\r\n");
      out.write("						<select name=\"searchType\" class=\"form-select\">\r\n");
      out.write("							<option value>전체</option>\r\n");
      out.write("							<option label=\"제목\" value=\"title\" />\r\n");
      out.write("							<option label=\"작성자\" value=\"writer\" />\r\n");
      out.write("							<option label=\"내용\" value=\"content\" />\r\n");
      out.write("						</select>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"col-auto\">\r\n");
      out.write("						<input name=\"searchWord\" class=\"form-control\" placeholder=\"검색키워드\"/>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"col-auto\">\r\n");
      out.write("						<input type=\"button\" value=\"검색\" id=\"searchBtn\" class=\"btn btn-primary\"/>\r\n");
      out.write("						<a href=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\" class=\"btn btn-success\">신규 게시글 등록</a>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("			</td>\r\n");
      out.write("		</tr>\r\n");
      out.write("	</tfoot>\r\n");
      out.write("</table>\r\n");
      out.write("<form action=\"");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("\" id=\"searchForm\">\r\n");
      out.write("	<input type=\"text\" name=\"searchType\" readonly/>\r\n");
      out.write("	<input type=\"text\" name=\"searchWord\" readonly/>\r\n");
      out.write("	<input type=\"text\" id=\"currpage\" name=\"page\" readonly=\"readonly\" placeholder=\"page\"/>\r\n");
      out.write("</form>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	var makeTrtags = (board)=>{\r\n");
      out.write("		let cPath = $(document.body).data(\"contextPath\");\r\n");
      out.write("		let boDate = board.boDate;\r\n");
      out.write("		let dateFormat = `${boDate[0]}-${boDate[1]}-${boDate[2]}T${boDate[3]}:${boDate[4]}:${boDate[5]}`;\r\n");
      out.write("		let trCode =`\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td>${board.boNo}</td>\r\n");
      out.write("			<td><a href=\"${cPath}/board/${board.boNo}\">${board.boTitle} [${board.fileCnt}]</a></td>\r\n");
      out.write("			<td>${board.boWriter}</td>\r\n");
      out.write("			<td>${dateFormat}</td>\r\n");
      out.write("			<td>${board.boHit}</td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		`;\r\n");
      out.write("		return trCode;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	$(searchForm).on(\"submit\",function(event){\r\n");
      out.write("		event.preventDefault();\r\n");
      out.write("		let url = this.action;\r\n");
      out.write("		let data = $(this).serialize();\r\n");
      out.write("		$.getJSON(url,data)\r\n");
      out.write("			.done(function(resp){\r\n");
      out.write("				let paging = resp.paging;\r\n");
      out.write("				let dataList = paging.dataList;\r\n");
      out.write("				let code = \"\";\r\n");
      out.write("				if(dataList?.length){\r\n");
      out.write("					$.each(dataList,function(i,board){\r\n");
      out.write("						code += makeTrtags(board);\r\n");
      out.write("					})\r\n");
      out.write("				}else{\r\n");
      out.write("					code += \"<tr><td colspan='5'>조회된 게시글이 없습니다.</td></tr>\"\r\n");
      out.write("				}\r\n");
      out.write("				$(pagingArea).html(paging.pagingHTML);\r\n");
      out.write("				$(listBody).html(code);\r\n");
      out.write("			});\r\n");
      out.write("		\r\n");
      out.write("	}).submit();\r\n");
      out.write("	\r\n");
      out.write("	function fn_paging(page){\r\n");
      out.write("		searchForm.page.value = page;\r\n");
      out.write("		searchForm.requestSubmit();\r\n");
      out.write("		searchForm.page.value = \"\";\r\n");
      out.write("	}\r\n");
      out.write("	$(searchUI).on(\"click\", \"#searchBtn\", function(event){\r\n");
      out.write("		let inputs = $(this).parents(\"#searchUI\").find(\":input[name]\");\r\n");
      out.write("		$.each(inputs, function(idx, ipt){\r\n");
      out.write("			let name = ipt.name;\r\n");
      out.write("			let value = $(ipt).val();\r\n");
      out.write("			$(searchForm).find(`:input[name=${name}]`).val(value);\r\n");
      out.write("		});\r\n");
      out.write("		$(searchForm).submit();\r\n");
      out.write("	});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f0_reused = false;
    try {
      _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f0.setParent(null);
      // /WEB-INF/jsp/board/boardList.jsp(35,15) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue("/board/new");
      int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
      if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      _jspx_th_c_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f1_reused = false;
    try {
      _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f1.setParent(null);
      // /WEB-INF/jsp/board/boardList.jsp(42,14) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f1.setValue("/board/data");
      int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
      if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      _jspx_th_c_005furl_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f1, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f1_reused);
    }
    return false;
  }
}
