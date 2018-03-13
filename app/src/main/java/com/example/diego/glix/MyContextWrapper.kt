package com.example.diego.glix

import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.provider.Settings


/**
 * Created by diego on 12/03/18.
 */
class MyContextWrapper(base: Context) : ContextWrapper(base) {

    val isAirplaneModeOn: Boolean
        get() = Settings.System.getInt(contentResolver,
                Settings.System.AIRPLANE_MODE_ON, 0) !== 0


    /*
     public boolean isAirplaneModeOn() {
      return Settings.System.getInt(getContentResolver(),
         Settings.System.AIRPLANE_MODE_ON, 0) != 0;
   }
     */

}