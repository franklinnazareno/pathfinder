package com.example.nazarenopathfinder

import java.util.*

class PathItem (
    var name: String,
    var source: String,
    var destination: String,
    var description: String,
    var id: UUID = UUID.randomUUID()
)