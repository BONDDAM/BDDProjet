package me.abond.bddprojet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import me.abond.bddprojet.CourseDb
import me.abond.bddprojet.CourseDbHelper
import me.abond.bddprojet.MobileCourse
import me.abond.bddprojet.R.id.display
import me.abond.bddprojet.R.id.message
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity(), AnkoLogger {

    private val courseDb by lazy { CourseDb(CourseDbHelper(applicationContext)) }
    var list = listOf<MobileCourse>()
    private var listConsole:ArrayList<String> = ArrayList<String>()
    private lateinit var listView: ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.display)

        doAsync {
            val course1 = MobileCourse("ABC Android", 120)
            courseDb.saveCourse(course1)
            list = courseDb.requestCourse()
            uiThread {
                showList()
            }
        }
        this.listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listConsole)
    }

    private fun showList() {
        var nbCours = "NB COURS : ${list.size}"
        info(nbCours)
        listConsole.add(nbCours);
        for (c in list) {
            var cours = "Voici un cours ${c.title}"
            info(cours)
            listConsole.add(cours)
            listView.invalidateViews();
        }
    }
}
