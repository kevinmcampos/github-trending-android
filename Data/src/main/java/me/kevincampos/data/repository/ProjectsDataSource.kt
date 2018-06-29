package me.kevincampos.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import me.kevincampos.data.model.ProjectEntity

interface ProjectsDataSource {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

}