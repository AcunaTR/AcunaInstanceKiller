package oldserver;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.lambda.demo.stubs.InstanceLaunchTimeStub;
import com.thomsonreuters.lambda.demo.OldServer;





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
		//fail("Not yet implemented");
	}
	
	@Test // check the calculated date values are in UTC
	public void testTodaysDateInUTC() { 
		//Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));

		InstanceLaunchTimeStub currentTime = new InstanceLaunchTimeStub();
		
		Assert.assertEquals(sdf.format(now), sdf.format((currentTime.whatTimeIsNow())));		
	}
	
	@Test
	public void testCutOffDateInputInUTC() {
	//	fail("Not yet implemented");
	}
	
	@Test
	public void testBufferDistanceBetweenCutoffAndToday() {
		int bufferHours = -24*buffer;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, bufferHours);
		Date cutOffDate = OldServer.identifyOldServers(now, buffer);
		Assert.assertEquals(cal.getTime(), cutOffDate);	
	}
	
	@Test //?same as above
	public void testCutoffTimeIsBeforeToday() {
		Date cutOffDate = OldServer.identifyOldServers(now, buffer);
		DateTime cutOffDateTime = new DateTime(cutOffDate.getTime());
		DateTime nowDateTime = new DateTime(now.getTime());
		Duration bufferDuration = Duration.ofHours(buffer);
		try {
			//Interval timeDifference = new Interval(cutOffDate.toInstant(), now.toInstant())
			//Interval timeDifference = new Interval((long) cutOffDate.toInstant(), (long) now.toInstant());
			//Interval timeDifference = new Interval(cutOffDateTime, nowDateTime); 
			//.getClass().DateTime tDateTime = new DateTime(now.getTime());
			
			Assert.assertTrue(cutOffDateTime.isBefore(nowDateTime));
		}catch (IllegalArgumentException e) {
			fail("Unexpected Illegal Agrument Exception - end date before start date - " + e.getMessage());
		}catch (Exception e) {
			fail("Unexpected exception - " + e.getMessage());
		}
	}

	@Test
	public void testBornOnCutoffTime() {
	//	fail("Not yet implemented");
	}
	
	@Test
	public void testTimeDifferenceNotNegative() {
	//	fail("Not yet implemented");
	}
	
	@Test
	public void testTimeDifferenceNotSilly() {
	//	fail("Not yet implemented");
	} 
}
