package com.river.rlog

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format: String = "yyyy-MM-dd HH:mm:ss"): String = SimpleDateFormat(format).format(this)