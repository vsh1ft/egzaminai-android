package com.project.egzaminai2


object SelectedExamSingleton {

    var selectedExamName: String = ""
    var paperUrl = ""
    var examSelectedEvent: () -> Unit = {}

}
