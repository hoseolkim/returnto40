package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Locale;
import java.util.Objects;

/**
 * 객체 사이에서 데이터를 주고 받는 용도로 사용
 * Layer와 Layer사이에서 값을 전달할 때 사용된다
 * VO(ValueObject), DTO(Data Transfer Object), Model, Bean ( Java Bean 규약)
 * == : reference 비교, equals : 상태 비교
 * equals메소드를 재정의하지않으면  refernce비교를한다.
 */
public class CalendarVO implements Serializable{
	// 3가지 규칙성..  프로퍼티가 있어야한다 > 프로퍼티의 타입이 있어야한다 > 데이터를 보호해야한다
	private Locale locale;
	private YearMonth targetMonth;
	private YearMonth beforeMonth;
	private YearMonth nextMonth;
	
	public Locale getLocale() {
		return locale;
	}
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public YearMonth getTargetMonth() {
		return targetMonth;
	}

	public void setTargetMonth(YearMonth targetMonth) {
		this.targetMonth = targetMonth;
	}

	public YearMonth getBeforeMonth() {
		return beforeMonth;
	}

	public void setBeforeMonth(YearMonth beforeMonth) {
		this.beforeMonth = beforeMonth;
	}

	public YearMonth getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(YearMonth nextMonth) {
		this.nextMonth = nextMonth;
	}

	@Override
	public String toString() {
		return "CalendarVO [beforeMonth=" + beforeMonth + ", locale=" + locale + ", nextMonth=" + nextMonth
				+ ", targetMonth=" + targetMonth + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(beforeMonth, locale, nextMonth, targetMonth);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalendarVO other = (CalendarVO) obj;
		return Objects.equals(beforeMonth, other.beforeMonth) && Objects.equals(locale, other.locale)
				&& Objects.equals(nextMonth, other.nextMonth) && Objects.equals(targetMonth, other.targetMonth);
	}
	
	
	
	
	
}