package oldserver;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.lambda.demo.stubs.InstanceLaunchTimeStub;





public class FindDateTestClass {

	int buffer;
	Date now;
	
	@Before
	public void setUp() throws Exception {
		buffer = 5;
		now = new Date();
		
	}

	@Test
	public void testServerDateInUTC() {
		fail("Not yet implemented");
	}
	
	@Test // check the calculated date values are in UTC
	public void testTodaysDateInUTC() { 
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));

		InstanceLaunchTimeStub currentTime = new InstanceLaunchTimeStub();
		
		Assert.assertEquals(sdf.format(now), sdf.format((currentTime.whatTimeIsNow())));		
	}
	
	@Test
	public void testCutOffDateInputInUTC() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testBufferDistanceBetweenCutoffAndToday() {
		int bufferHours = -24*buffer;
		Calendar cal = Calendar.getInstance();
	//	 cutOffDate = cal.add(Calendar.HOUR, bufferHours)
		
	//	Date oldServer.getCutOffDate(now);
	//	Assert.assertEquals(expected, actual);
		
		
	//	fail("Not yet implemented");
		
		
	}
	
	@Test //?same as above
	public void testCutoffTimeIsBeforeToday() {
		fail("Not yet implemented");
	}

	@Test
	public void testBornOnCutoffTime() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTimeDifferenceNotNegative() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTimeDifferenceNotSilly() {
		fail("Not yet implemented");
	} 
}
