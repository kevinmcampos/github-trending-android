package me.kevincampos.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import me.kevincampos.data.model.ProjectEntity

interface ProjectsDataSource {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

}