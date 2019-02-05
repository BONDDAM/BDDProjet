package me.abond.bddprojet

import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class CourseDb(private val dbHelper: CourseDbHelper = CourseDbHelper.instance) {

    fun requestCourse() = dbHelper.use {
        select(Tables.MobileCourseTable.NAME, Tables.MobileCourseTable.TITLE, Tables.MobileCourseTable.TIME).parseList(classParser<MobileCourse>())
    }

    fun saveCourse(course: MobileCourse) = dbHelper.use {
        insert(Tables.MobileCourseTable.NAME,Tables.MobileCourseTable.TITLE to course.title,
            Tables.MobileCourseTable.TIME to course.time)
    }

    fun saveCourses(courseList: List<MobileCourse>) {
        for (c in courseList)
            saveCourse(c)
        }
}