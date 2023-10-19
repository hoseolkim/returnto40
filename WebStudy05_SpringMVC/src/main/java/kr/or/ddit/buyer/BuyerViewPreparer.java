package kr.or.ddit.buyer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.common.streotype.Preparer;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Preparer
public class BuyerViewPreparer implements ViewPreparer {

	@Inject
	private MemberDAO dao;

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		log.info("★★★★★★★★★★★★★★★★★★★★★★view preparer★★★★★★★★★★★★★★★");
//		<a href='contextPath/buyer/form'>제조사등록</a>
		MenuVO menu1 = new MenuVO();
		menu1.setMenuText("제조사등록");
		menu1.setMenuUrl("/buyer/form");

		MenuVO menu2 = new MenuVO();
		menu2.setMenuText("제조사목록");
		menu2.setMenuUrl("/buyer");

		List<MenuVO> menuList = Arrays.asList(menu1, menu2);

		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);

		requestScope.put("menuList", menuList);

	}
}