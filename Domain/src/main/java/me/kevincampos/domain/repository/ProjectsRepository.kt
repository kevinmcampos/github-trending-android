package me.kevincampos.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import me.kevincampos.domain.model.Project

interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun getBookmarkedProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

}