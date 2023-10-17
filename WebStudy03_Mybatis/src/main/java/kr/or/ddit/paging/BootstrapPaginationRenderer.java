package kr.or.ddit.paging;

import kr.or.ddit.vo.PaginationInfo;

public class BootstrapPaginationRenderer implements PaginationRenderer {
	private final String LITAG_START_PATTERN = "<li class='page-item %s'>";
	private final String LITAG_START_CURRENT_PATTERN = "<li class='page-item %s' aria-current='page'>";
	private final String LITAG_END = "</li>";
	private final String ATAG_PATTERN = "<a href='javascript:;' class='page-link' onclick='fn_paging(%d);'>%s</a>";
	@Override
	public String renderPagination(PaginationInfo<?> paging) {
		
		int startPage = paging.getStartPage();
		int endPage = paging.getEndPage();
		int totalPage = paging.getTotalPage();
		int currentPage = paging.getCurrentPage();
		StringBuffer html = new StringBuffer();
		
		html.append("<ul class='pagination justify-content-center'>");
		if(startPage == 1) {
			html.append(
					String.format(LITAG_START_PATTERN, "disabled")
			);
		}else {
			html.append(
					String.format(LITAG_START_PATTERN, "")
					);
		}
		html.append(String.format(ATAG_PATTERN, startPage-1 , "이전"));
		html.append(LITAG_END);
		
		for(int page = startPage ; page <= endPage ; page++) {
			// 현재 페이지일 경우
			if(page == currentPage) {
				html.append(
					String.format(LITAG_START_CURRENT_PATTERN, "active")
				);
			}else {
				// 아닐 경우
				html.append(
					String.format(LITAG_START_PATTERN, "")
				);
			}
			html.append(
					String.format(ATAG_PATTERN, page, page)
					);
			html.append(LITAG_END);
			
		}
		
		
		
		if(endPage < totalPage) {
			html.append(
				String.format(LITAG_START_PATTERN, "")
			);
		}else {
			html.append(
				String.format(LITAG_START_PATTERN, "disabled")
			);
		}
		html.append(
				String.format(ATAG_PATTERN, endPage + 1 , "다음")
				);
		html.append(LITAG_END);
		
		html.append("</ul>");
		
		return html.toString();
		
		
	}

}
