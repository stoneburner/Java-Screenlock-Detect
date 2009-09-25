/*
 * @(#)LockTest.java   08/11/05 
 * Alexander Kasimir
 */

package at.agamemnon;

/**
 *
 * @author alexander.kasimir
 */
public class LockTest
{
   public LockTest()
   {
      try
      {
         Thread.sleep(10000);
         int screenstate=ScreenLockDetect.lockState();
         
         switch (screenstate)
         {
            case ScreenLockDetect.SCREEN_LOCKED:
               System.out.println("Screen Locked");
               break;
            case ScreenLockDetect.SCREEN_SAVER_ACTIVE:
               System.out.println("Screensaver running");
               break;
            case ScreenLockDetect.SCREEN_UNLOCKED:
               System.out.println("Screen unlocked");
               break;
            default:
               System.out.println("Screen State unknown..");
               
         }         
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   //~--- methods -------------------------------------------------------------

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
      new LockTest();
   }
}
