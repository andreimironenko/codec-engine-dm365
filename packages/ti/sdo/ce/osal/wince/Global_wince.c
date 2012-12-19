/* 
 * Copyright (c) 2010, Texas Instruments Incorporated
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * *  Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * *  Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * *  Neither the name of Texas Instruments Incorporated nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
/*//    WinCE.profiles["debug"].linkOpts += " -libpath:" + cesysgen + "debug";
//    WinCE.profiles["release"].linkOpts += " -libpath:" + cesysgen + "retail";

//    WinCE.includeOpts = "-I" + WinCE.rootDir + "/PUBLIC/COMMON/SDK/INC";
//    WinCE.lnkOpts.suffix += " -entry:mainCRTStartup ";


 *  ======== Global_wince.c ========
 */
#include <xdc/std.h>

#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

#include <ti/sdo/utils/trace/gt.h>

#include <ti/sdo/ce/osal/Global.h>


#define CODEC_ENGINE_REGISTRY_MAX_STR_LEN 256
#define OSAL_REG_KEY TEXT("SOFTWARE\\Texas Instruments\\ti.sdo.ce.osal")

/* list of functions to be called at exit */
typedef struct ExitFxnElem {
    Fxn                 fxn;
    struct ExitFxnElem *next;
} ExitFxnElem;

/* REMINDER: if you add an initialized static var, reinitialize it at cleanup */

static Bool         curInit          = FALSE;

static ExitFxnElem *exitFxnList      = NULL;
static Bool         doRegisterAtExit = TRUE;

static GT_Mask      curTrace = {NULL, NULL};

extern String Global_buildInfo[];  /* generated by Global.xdt */

/*
 *  ======== Global_abort ========
 */
Void Global_abort(String fmt, ...)
{
    va_list vargs;

    printf("\n======== Global_abort =============\n");

    va_start(vargs, fmt);
    vprintf(fmt, vargs);
    va_end(vargs);

    exit(1);
}

/*
 *  ======== Global_atexit ========
 */
Bool Global_atexit(Fxn fxn)
{
    ExitFxnElem *exitFxnElem;

    /* if we are called but Global has not been initialized yet, that's
     * because GT's init has called us to schedule its own
     * cleanup -- in which case, don't use trace!
     */
    if (curInit == TRUE) {
        GT_1trace(curTrace, GT_ENTER, "Global_atexit> enter (fxn=0x%x)\n",fxn);
    }

    exitFxnElem = (ExitFxnElem *)malloc( sizeof( ExitFxnElem ) );

    if (exitFxnElem == NULL) {
        return FALSE;
    }

    exitFxnElem->fxn  = fxn;
    exitFxnElem->next = exitFxnList;
    exitFxnList       = exitFxnElem;

    /* register with the real system exit only once in the lifetime */
    if (doRegisterAtExit == TRUE) {
        atexit( (void (*)(void))Global_exit );
        doRegisterAtExit = FALSE;
    }

    return TRUE;
}

/*
 *  ======== Global_exit ========
 *  calls cleanup functions for all the modules that
 *  scheduled it, then cleans up itself
 */
Void Global_exit()
{
    ExitFxnElem *old;

    if (curInit == FALSE) {
        return;
    }

    GT_0trace(curTrace, GT_ENTER, "Global_exit> enter\n" );

    while( exitFxnList != NULL ) {
        GT_1trace(curTrace, GT_2CLASS, "Global_exit> "
                "calling function *0x%x()...\n", exitFxnList->fxn );
        exitFxnList->fxn();
        old = exitFxnList;
        exitFxnList = exitFxnList->next;
        free(old);
    }

    /* if the tracing module has its exit function, it must have been
     * the last one, so don't use trace from this point on
     */

    /* do the cleanup of this module */
    curInit          = FALSE;
    exitFxnList      = NULL;  /* unnecessary, NULL already */

    /* we do NOT reset doRegisterAtExit, as we don't want endless pairs of
     * CERuntime_init/exit to keep adding this func to the process' atexit
     * table. Autoexit is a curtesy feature only for those who call
     * CERuntime_init once.
     */
    /* doRegisterAtExit = TRUE; */
}

/*
 *  ======== Global_getCpuLoad ========
 */
Int Global_getCpuLoad(Void)
{
    return (-1);
}

/*
 *  ======== Global_getenv ========
 */
String Global_getenv(String name)
{
    LONG        lStatus = 0;
    HKEY        hKey;
    TCHAR       tszData[CODEC_ENGINE_REGISTRY_MAX_STR_LEN];
    DWORD       dwcbData = CODEC_ENGINE_REGISTRY_MAX_STR_LEN;
    static char szData[CODEC_ENGINE_REGISTRY_MAX_STR_LEN];
    WCHAR       wszEnvName[256];

    GT_1trace(curTrace, GT_ENTER, "Global_getenv> enter, name [0x%0x]\n",
            name);

    /*
     *  Create the reg key. If it already exists, this function opens it.
     */
    lStatus = RegCreateKeyEx(HKEY_LOCAL_MACHINE,
                             OSAL_REG_KEY,
                             0,
                             NULL,
                             REG_OPTION_NON_VOLATILE,
                             0,
                             NULL,
                             &hKey,
                             NULL);

    if (lStatus == ERROR_SUCCESS) {
        MultiByteToWideChar(CP_ACP, 0, name, strlen(name) + 1, wszEnvName,
                sizeof(wszEnvName) / sizeof(wszEnvName[0]));
        lStatus = RegQueryValueEx(hKey, wszEnvName, NULL, NULL,
                (LPBYTE)tszData, &dwcbData);
        RegCloseKey(hKey);

        if (lStatus == ERROR_SUCCESS) {
            wcstombs(szData, tszData, CODEC_ENGINE_REGISTRY_MAX_STR_LEN);
            if (szData[0] == '\0') {
                /*
                 *  If the key is in the registry with an empty string for
                 *  the value, just return NULL.
                 */
                GT_0trace(curTrace, GT_ENTER,
                        "Global_getenv> exit, returning NULL\n");
                return (NULL);
            }
            else {
                /*
                 *  Note: we are returning a pointer to a static array.
                 *  Subsequent calls to Global_getenv() can overwrite the
                 *  values in this array! getenv() is allowed to return the
                 *  same pointer over and over again, overwriting the previous
                 *  data. This is no worse.
                 */
                GT_1trace(curTrace, GT_ENTER,
                        "Global_getenv> exit, returning 0x%x\n", szData);
                return (szData);
            }
        }
    }
    return (NULL);
}

/*
 *  ======== Global_getProcessId ========
 */
UInt32 Global_getProcessId(Void)
{
    /*
     *  For WinCE return the Process Id + a unique address. This
     *  is done since for Kernel Drivers at startup the Process Id is
     *  the same. Also add in a unique identity for
     *  this .lib inclusion - using &doRegisterAtExit.
     */
//    return (((UInt32)GetCurrentProcessId()) + (UInt32)&doRegisterAtExit);
    return ((UInt32)GetCurrentProcessId());
}

/*
 *  ======== Global_init ========
 */
Void Global_init(Void)
{
    Int i;

    if (curInit != TRUE) {
        curInit = TRUE;
        GT_create(&curTrace, Global_GTNAME);

        GT_0trace(curTrace, GT_4CLASS, "Global_init> "
            "This program was built with the following packages:\n" );
        for (i = 0; Global_buildInfo[i] != NULL; i++) {
            GT_1trace(curTrace, GT_4CLASS, "%s\n", Global_buildInfo[i]);
        }
    }
}

/*
 *  ======== Global_setenv ========
 */
Bool Global_setenv(String name, String val)
{
    LONG        lStatus = 0;
    HKEY        hKey;
    DWORD       dwcbData;
    WCHAR       wszEnvName[256];
    WCHAR       wszEnvVal[256];

    GT_2trace(curTrace, GT_ENTER, "Global_setenv> name [0x%x], val [0x%x]\n",
            name, val);

    if (name == NULL) {
        GT_0trace(curTrace, GT_7CLASS,
                "Global_setenv> name must be non-NULL\n");
        return (FALSE);
    }

    /*
     *  Create the reg key. If it already exists, this function opens it.
     */
    lStatus = RegCreateKeyEx(HKEY_LOCAL_MACHINE,
                             OSAL_REG_KEY,
                             0,
                             NULL,
                             REG_OPTION_NON_VOLATILE,
                             0,
                             NULL,
                             &hKey,
                             NULL);

    if (lStatus == ERROR_SUCCESS) {
        MultiByteToWideChar(CP_ACP, 0, name, strlen(name) + 1, wszEnvName,
                sizeof(wszEnvName) / sizeof(wszEnvName[0]));

        if (val != NULL) {
            MultiByteToWideChar(CP_ACP, 0, val, strlen(val) + 1, wszEnvVal,
                    sizeof(wszEnvVal) / sizeof(wszEnvVal[0]));
            dwcbData = (strlen(val) + 1) * sizeof(TCHAR);

            lStatus = RegSetValueEx(hKey,
                    wszEnvName,          /* the name of the registry entry */
                    0,                   /* Reserved - must be set to 0 */
                    REG_SZ,              /* data type - Unicode string */
                    (LPBYTE)wszEnvVal,   /* the data */
                    dwcbData);           /* size of the data */
        }
        else {
            /*
             *  Passing in a NULL val corresponds to unsetting the environment
             *  variable. So we'll delete the value from the registry.
             */
            lStatus = RegDeleteValue(hKey, wszEnvName);
            GT_1trace(curTrace, GT_ENTER,
                    "Global_setenv> RegDeleteValue returns [0x%x]\n",
                    lStatus);
            if (lStatus == ERROR_FILE_NOT_FOUND) {
                /* This is ok. We're unsetting a variable that wasn't set */
                lStatus = ERROR_SUCCESS;
            }
        }

        RegCloseKey(hKey);
    }
    return ((lStatus == ERROR_SUCCESS) ? TRUE : FALSE);
}

/*
 *  ======== Global_setSpecialTrace ========
 */
Void Global_setSpecialTrace( String mask )
{
}
/*
 *  @(#) ti.sdo.ce.osal.wince; 1, 0, 0,82; 12-2-2010 21:25:04; /db/atree/library/trees/ce/ce-r11x/src/ xlibrary

 */

