/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.agamemnon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author alexander.kasimir
 */
public class ScreenLockDetect 
{
   
   public static final int SCREEN_LOCK_STATUS_UNKNOWN=-1;
   public static final int SCREEN_UNLOCKED=0;
   public static final int SCREEN_LOCKED=1;
   public static final int SCREEN_SAVER_ACTIVE=2;
   
   
   public static boolean isLocked()
   {
      if (lockState()==SCREEN_UNLOCKED)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   
   public static int lockState()
   {
      if (System.getProperty("os.name").startsWith("Windows"))
      {
         System.loadLibrary("lockdetect");
         if (isScreenLocked()==true)
         {
            return SCREEN_LOCKED;
         }
         else
         {
            if (isScreensaverRunningWindows() == true)
            {
               return SCREEN_SAVER_ACTIVE;
            }
            else
            {
               return SCREEN_UNLOCKED;
            }
         }
      }
      else if (System.getProperty("os.name").startsWith("Mac OS X"))
      {
         if (isScreenSaverRunningMac() == true)
         {
            return SCREEN_SAVER_ACTIVE;
         }
         else
         {
            return SCREEN_UNLOCKED;
         }
      }
      else
      {
         return SCREEN_LOCK_STATUS_UNKNOWN;
      }
   }

   private static boolean isScreenSaverRunningMac()
   {
      try
      {
         String         line;
         Process        p     = Runtime.getRuntime().exec("ps -e");
         BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

         int count=0;
         while ((line = input.readLine()) != null)
         {           
            if (line.trim().endsWith("ScreenSaverEngine")==true)
            {
               return true;
            }
         }
         input.close();
      }
      catch (Exception err)
      {
         err.printStackTrace();
      }   
      return false;
   }
   
   private static boolean isScreensaverRunningWindows()
   {
      try
      {
         String         line;
//         Process        p     = Runtime.getRuntime().exec("ps -e");
         Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
         BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

         int count=0;
         while ((line = input.readLine()) != null)
         {
            if (count > 2 && line.length() > 25)
            {
               String process=line.substring(0,25).trim();
               if (process.toLowerCase().endsWith(".scr"))
               {
                  return true;
               }
            }
            count++;
         }
         input.close();
      }
      catch (Exception err)
      {
         err.printStackTrace();
      }   
      return false;
   }
   
   private static native boolean isScreenLocked();
   
   static {System.loadLibrary("lockdetect");}   
   
   public ScreenLockDetect ()
   {
   }
   
}
