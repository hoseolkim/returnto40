package kr.or.ddit.memo.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import kr.or.ddit.common.enumpkg.MemoTableSchema;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemoVO;

public class AuthenticateMemoTableSchemaImpl implements AuthenticateMemoTableSchema {

	@Override
	public ServiceResult authenticateTableSchema(MemoVO memoVO) {
		ServiceResult res = ServiceResult.OK;
		try {
			MemoTableSchema[] columns = MemoTableSchema.values();
			Class<?> clazz = memoVO.getClass();
			Field[] voFields = clazz.getFields();
			for(Field fd : voFields) {
				for(MemoTableSchema ts : columns) {
					if(fd.getName().toLowerCase().equals(ts.name().toLowerCase())) {
							PropertyDescriptor pd = new PropertyDescriptor(fd.getName(), clazz);
							String fdValue = (String) pd.getReadMethod().invoke(memoVO);
						switch (ts.getDataType()) {
						case "CHAR":
								if(ts.getLength() < fdValue.length()) {
									res = ServiceResult.LARGEVALUE;
								}
							break;
	
						default:
								if( ts.getLength() < (fdValue.length() * 2) ) {
									res = ServiceResult.LARGEVALUE;
								}
							break;
						}// switch case문 끝
					} // 필드명과 칼럼명 비교하는 if문 끝
				} // 스키마 Enum 뒤지는 for문 끝
			}// 필드 뒤지는 for문 끝
			return res;
			
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
