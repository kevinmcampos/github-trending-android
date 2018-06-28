package me.kevincampos.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import me.kevincampos.data.model.ProjectEntity

interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun areProjectsCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isProjectsCachedExpired(): Single<Boolean>

}