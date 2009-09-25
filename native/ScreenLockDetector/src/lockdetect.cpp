#include <windows.h>
#include <jni.h>
#include "lockdetect.h"

/*
 * 
 */

JNIEXPORT jboolean JNICALL Java_at_thales_ScreenLockDetect_isScreenLocked (JNIEnv *env, jobject obj)
{
    HDESK desktop;    
    desktop=OpenInputDesktop(0,TRUE,READ_CONTROL);    

    if (desktop == NULL)
    {
        return (jboolean) TRUE;
    }
    else
    {
        return (jboolean) FALSE;
    }
}
