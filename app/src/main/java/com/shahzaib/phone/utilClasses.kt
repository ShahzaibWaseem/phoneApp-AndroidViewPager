package com.shahzaib.phone

import android.graphics.Bitmap

class Contact (var name: String, var number: String)

class CallLog (var name: String?, var number: String, var date: String, var time: String, val callType: Int, var photo: Bitmap?)