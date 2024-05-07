package sit707_week5;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.mockito.Mockito.*;
import java.util.Date;


public class WeatherControllerTest {

    private WeatherController wController;

    @Before
    public void setUp() {
        wController = WeatherController.getInstance();
    }

    @After
    public void tearDown() {
        wController.close();
    }
    @Test
    public void testStudentIdentity() {
        String studentId = "s223123562";
        Assert.assertNotNull("Student ID is s223123562", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Zainab";
        Assert.assertNotNull("Student name is Zainab", studentName);
    }


  
    //@Test
    //public void testTemperatureMin() {
    //    System.out.println("+++ testTemperatureMin +++");

    //    int nHours = wController.getTotalHours();
    //    double minTemperature = Double.MAX_VALUE;
    //    for (int i = 0; i < nHours; i++) {
    //        double temperatureVal = wController.getTemperatureForHour(i + 1);
    //        if (minTemperature > temperatureVal) {
    //            minTemperature = temperatureVal;
    //        }
    //    }

    //    Assert.assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.0);
    //}

    //@Test
    //public void testTemperatureMax() {
    //    System.out.println("+++ testTemperatureMax +++");

    //    int nHours = wController.getTotalHours();
    //    double maxTemperature = Double.MIN_VALUE;
    //    for (int i = 0; i < nHours; i++) {
    //        double temperatureVal = wController.getTemperatureForHour(i + 1);
    //        if (maxTemperature < temperatureVal) {
    //            maxTemperature = temperatureVal;
    //        }
    //    }

    //    Assert.assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.0);
    //}

    //@Test
    //public void testTemperatureAverage() {
    //    System.out.println("+++ testTemperatureAverage +++");

    //    int nHours = wController.getTotalHours();
    //    double sumTemp = 0;
    //    for (int i = 0; i < nHours; i++) {
    //        double temperatureVal = wController.getTemperatureForHour(i + 1);
    //        sumTemp += temperatureVal;
    //    }
    //    double averageTemp = sumTemp / nHours;

    //    Assert.assertEquals(averageTemp, wController.getTemperatureAverageFromCache(), 0.0);
    //}
    
    @Test
    public void testTemperaturePersist() throws ParseException {
        System.out.println("+++ testTemperaturePersist +++");

      
        WeatherController wController = WeatherController.getInstance();

       
        String persistTime = wController.persistTemperature(10, 19.5);

      
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get current time
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("Persist time: " + persistTime + ", now: " + now);

        long toleranceMillis = 3000; 

     
        Date persistDate = new SimpleDateFormat("HH:mm:ss").parse(persistTime);
        Date nowDate = new SimpleDateFormat("HH:mm:ss").parse(now);

        
        boolean withinTolerance = Math.abs(persistDate.getTime() - nowDate.getTime()) <= toleranceMillis;

    
        Assert.assertTrue("Persist time is within 3 seconds of current time", withinTolerance);

       
        wController.close();
    }

}
