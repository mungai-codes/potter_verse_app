package com.mungai.intothepotter_verse

import java.io.InputStreamReader

class ResponseFileReader(val path: String) {
    var content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}