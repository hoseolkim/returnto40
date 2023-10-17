package kr.or.ddit.lang;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.jupiter.api.Test;

class PoolTest {
	public class ReaderUtil { 
	    public ReaderUtil() { 
	    } 
	 
	    /** 
	     * Dumps the contents of the {@link Reader} to a 
	     * String, closing the {@link Reader} when done. 
	     */ 
	    public String readToString(Reader in) throws IOException { 
	        StringBuffer buf = new StringBuffer(); 
	        try { 
	            for(int c = in.read(); c != -1; c = in.read()) { 
	                buf.append((char)c); 
	            } 
	            return buf.toString(); 
	        } catch(IOException e) { 
	            throw e; 
	        } finally { 
	            try { 
	                in.close(); 
	            } catch(Exception e) { 
	                // ignored 
	            } 
	        } 
	    } 
	}
	@Test
	void test() {
		
		File file = new File("");
		FileReader fileReader = new FileReader(null);
		
		
		new ReaderUtil().readToString();
	}

}
